package com.wannistudio.wannimart.controller.authentication;

import com.wannistudio.wannimart.config.jwt.JwtAuthenticationToken;
import com.wannistudio.wannimart.controller.AuthenticationRequest;
import com.wannistudio.wannimart.controller.AuthenticationResult;
import com.wannistudio.wannimart.controller.api.ApiResult;
import com.wannistudio.wannimart.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wannistudio.wannimart.controller.api.ApiResult.OK;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {
  private final AuthenticationManager authenticationManager;

  @PostMapping
  public ApiResult<AuthenticationResultDto> authentication(@RequestBody AuthenticationRequest authenticationRequest) throws UnauthorizedException {

    try {
      JwtAuthenticationToken authToken = new JwtAuthenticationToken(authenticationRequest.getPrincipal(), authenticationRequest.getCredentials());
      Authentication authentication = authenticationManager.authenticate(authToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      return OK(
              new AuthenticationResultDto((AuthenticationResult) authentication.getDetails())
      );
    } catch (AuthenticationException e) {
      System.out.println(e.getMessage());
      throw new UnauthorizedException(e.getMessage());
    }
  }

}
