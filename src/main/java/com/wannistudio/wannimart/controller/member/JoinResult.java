package com.wannistudio.wannimart.controller.member;

import com.wannistudio.wannimart.domain.member.Member;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class JoinResult {
  private final String apiToken;
  private final Member member;

  public JoinResult(String apiToken, Member member) {
    checkNotNull(apiToken, "apiToken must be provided.");
    checkNotNull(member, "user must be provided.");

    this.apiToken = apiToken;
    this.member = member;
  }
}
