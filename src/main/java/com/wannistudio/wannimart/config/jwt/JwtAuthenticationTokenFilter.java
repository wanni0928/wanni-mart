package com.wannistudio.wannimart.config.jwt;

import com.wannistudio.wannimart.domain.member.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

  private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final String headerKey;
  private final Jwt jwt;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      String authorizationToken = obtainAuthorizationToken(request);
      if (authorizationToken != null) {
        try {
          Jwt.Claims claims = verify(authorizationToken);
          logger.debug("Jwt parse result: {}", claims);
          // 만료 10분 전

          long exp = claims.exp();
          long remain = System.currentTimeMillis() - exp;

          if (canRefresh(claims, 6000 * 10)) {
            String refreshToken = jwt.refreshToken(authorizationToken);
            response.setHeader(headerKey, refreshToken);
          }

          Long userKey = claims.userKey;
          String name = claims.name;
          Email email = claims.email;
          String account = claims.account;

          List<GrantedAuthority> authorities = obtainAuthorities(claims);
          if (nonNull(userKey) && isNotEmpty(name) && nonNull(account) && authorities.size() > 0) {
            JwtAuthenticationToken authentication =
                    new JwtAuthenticationToken(new JwtAuthentication(userKey, name, email, account), null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        } catch (Exception e) {
          logger.warn("Jwt processing failed: {}", e.getMessage());
        }
      }
    }  else {
      logger.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'",
              SecurityContextHolder.getContext().getAuthentication());
    }

    filterChain.doFilter(request, response);
  }

  private List<GrantedAuthority> obtainAuthorities(Jwt.Claims claims) {
    String[] roles = claims.roles;
    return roles == null || roles.length == 0 ?
            Collections.emptyList() :
            Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList())
            ;
  }

  private boolean canRefresh(Jwt.Claims claims, int refreshedRangedMillis) {
    long exp = claims.exp();
    if (exp > 0) {
      long remain = exp - System.currentTimeMillis();
      return remain < refreshedRangedMillis;
    }
    return false;
  }

  private String obtainAuthorizationToken(HttpServletRequest request) {
    String token = request.getHeader(headerKey);
    if(token != null) {
      if (logger.isDebugEnabled())
        logger.debug("Jwt authorization api detected: {}", token);
      try {
        token = URLDecoder.decode(token, "UTF-8");
        String[] parts = token.split(" ");
        if (parts.length == 2) {
          String scheme = parts[0];
          String credentials = parts[1];
          return BEARER.matcher(scheme).matches() ? credentials : null;
        }
      } catch (UnsupportedEncodingException e) {
        logger.error(e.getMessage(), e);
      }
    }

    return null;
  }

  private Jwt.Claims verify(String token) {
    return jwt.verify(token);
  }
}
