package com.wannistudio.wannimart.controller.authentication;

import com.wannistudio.wannimart.controller.AuthenticationResult;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class AuthenticationResultDto {
  private String apiToken;
  private MemberDto member;

  public AuthenticationResultDto(AuthenticationResult source) {
    copyProperties(source, this);
    this.member = new MemberDto(source.getMember());
  }
}
