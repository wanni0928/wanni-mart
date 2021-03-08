package com.wannistudio.wannimart.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wannistudio.wannimart.controller.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wannistudio.wannimart.controller.api.ApiResult.ERROR;

@Component
@RequiredArgsConstructor
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
  static ApiResult<?> E401 = ERROR("Authentication error (cause: unauthorized)", HttpStatus.UNAUTHORIZED);

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
          throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setHeader("content-type", "application/json");
    response.getWriter().write(objectMapper.writeValueAsString(E401));
    response.getWriter().flush();
    response.getWriter().close();
  }
}