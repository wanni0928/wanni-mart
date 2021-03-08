package com.wannistudio.wannimart.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthenticationRequest {
  private String principal;
  private String credentials;

  public AuthenticationRequest(String principal, String credentials) {
    this.principal = principal;
    this.credentials = credentials;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("principal", principal)
            .append("credentials", credentials)
            .toString();
  }
}
