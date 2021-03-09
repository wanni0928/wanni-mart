package com.wannistudio.wannimart.controller.authentication;

import com.wannistudio.wannimart.controller.AuthenticationResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class AuthenticationResultDto {

  @ApiModelProperty(value = "API 토큰", required = true)
  private String apiToken;

  @ApiModelProperty(value = "회원 정보", required = true)
  private MemberDto member;

  public AuthenticationResultDto(AuthenticationResult source) {
    copyProperties(source, this);
    this.member = new MemberDto(source.getMember());
  }
}
