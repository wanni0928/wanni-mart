package com.wannistudio.wannimart.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthenticationRequest {

  @ApiModelProperty(value = "로그인할 계정", required = true, example = "testUserAccount")
  private String principal;

  @ApiModelProperty(value = "로그인할 비밀번호", required = true, example = "1234")
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
