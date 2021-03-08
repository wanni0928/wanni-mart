package com.wannistudio.wannimart.config.security;

import com.wannistudio.wannimart.config.jwt.Jwt;
import com.wannistudio.wannimart.config.jwt.JwtAuthenticationProvider;
import com.wannistudio.wannimart.config.jwt.JwtAuthenticationTokenFilter;
import com.wannistudio.wannimart.config.jwt.JwtTokenConfigure;
import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.member.Member;
import com.wannistudio.wannimart.domain.member.Role;
import com.wannistudio.wannimart.handler.EntryPointUnauthorizedHandler;
import com.wannistudio.wannimart.handler.JwtAccessDeniedHandler;
import com.wannistudio.wannimart.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.math.NumberUtils.toLong;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {
  private final Jwt jwt;
  private final JwtTokenConfigure jwtTokenConfigure;
  private final JwtAccessDeniedHandler accessDeniedHandler;
  private final EntryPointUnauthorizedHandler unauthorizedHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/swagger-resources", "/webjars/**", "/static/**", "/templates/**", "/h2/**");
  }

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder builder, JwtAuthenticationProvider authenticationProvider) {
    builder.authenticationProvider(authenticationProvider);
  }

  @Bean
  public JwtAuthenticationProvider jwtAuthenticationProvider(Jwt jwt, MemberService memberService) {
    return new JwtAuthenticationProvider(jwt, memberService);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf()
            .disable()
            .headers()
            .disable()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/_hcheck").permitAll()
            .antMatchers("/api/auth").permitAll()
            .antMatchers("/api/user/join").permitAll()
            .antMatchers("/api/user/exists").permitAll()
            .antMatchers("/api/**").hasRole(Role.USER.name())
            .accessDecisionManager(accessDecisionManager())
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .disable()
    ;

    http
            .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter(jwtTokenConfigure.getHeader(), jwt);
  }

  @Bean
  public AccessDecisionManager accessDecisionManager() {
    List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
    decisionVoters.add(new WebExpressionVoter());
    decisionVoters.add(connectionBasedVoter());
    // 모든 voter가 승인해야 해야한다.
    return new UnanimousBased(decisionVoters);
  }

  @Bean
  public ConnectionBasedVoter connectionBasedVoter() {
    final String regex = "^/api/user/(\\d+)/post/.*$";
    Pattern pattern = Pattern.compile(regex);
    RequestMatcher requiresAuthorizationRequestMatcher = new RegexRequestMatcher(pattern.pattern(), null);
    return new ConnectionBasedVoter(
            requiresAuthorizationRequestMatcher,
            (String url) -> {
              /* url에서 targetId를 추출하기 위해 정규식 처리 */
              Matcher matcher = pattern.matcher(url);
              long id = matcher.find() ? toLong(matcher.group(1), -1) : -1;
              return Id.of(Member.class, id);
            }
    );
  }
}
