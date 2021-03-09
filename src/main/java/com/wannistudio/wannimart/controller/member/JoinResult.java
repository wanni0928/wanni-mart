package com.wannistudio.wannimart.controller.member;

import com.wannistudio.wannimart.domain.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class JoinResult {

  @ApiModelProperty(value = "API 토큰", required = true)
  private final String apiToken;

  @ApiModelProperty(value = "회원정보", required = true)
  private final Member member;

  public JoinResult(String apiToken, Member member) {
    checkNotNull(apiToken, "apiToken must be provided.");
    checkNotNull(member, "user must be provided.");

    this.apiToken = apiToken;
    this.member = member;
  }
}
