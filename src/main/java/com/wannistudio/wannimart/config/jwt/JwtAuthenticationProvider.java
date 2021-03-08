package com.wannistudio.wannimart.config.jwt;

import com.wannistudio.wannimart.controller.AuthenticationRequest;
import com.wannistudio.wannimart.controller.AuthenticationResult;
import com.wannistudio.wannimart.domain.member.Member;
import com.wannistudio.wannimart.domain.member.Role;
import com.wannistudio.wannimart.exception.NotFoundException;
import com.wannistudio.wannimart.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final Jwt jwt;
  private final MemberService memberService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
    return processUserAuthentication(authenticationToken.authenticationRequest());
  }

  private Authentication processUserAuthentication(AuthenticationRequest authenticationRequest) {
    try {
      Member member = memberService.login(authenticationRequest.getPrincipal(), authenticationRequest.getCredentials());
      JwtAuthenticationToken authenticated =
              new JwtAuthenticationToken(
                      new JwtAuthentication(
                              member.getId(),
                              member.getName(),
                              member.getEmail(),
                              member.getAccount()),
                      null,
                      createAuthorityList(Role.USER.value())
              );
      String apiToken = member.newApiToken(jwt, new String[]{Role.USER.value()});
      authenticated.setDetails(new AuthenticationResult(apiToken, member));

      return authenticated;
    } catch (NotFoundException e) {
      throw new UsernameNotFoundException(e.getMessage());
    } catch (IllegalArgumentException e) {
      throw new BadCredentialsException(e.getMessage());
    } catch (DataAccessException e) {
      throw new AuthenticationServiceException(e.getMessage(), e);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return isAssignable(JwtAuthenticationToken.class, authentication);
  }
}

