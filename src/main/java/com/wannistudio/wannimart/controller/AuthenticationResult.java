package com.wannistudio.wannimart.controller;

import com.wannistudio.wannimart.domain.member.Member;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class AuthenticationResult {
  private final String apiToken;
  private final Member member;

  public AuthenticationResult(String apiToken, Member member) {
    checkNotNull(apiToken, "apiToken must be provided.");
    checkNotNull(member, "member must be provided.");
    this.apiToken = apiToken;
    this.member = member;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("apiToken", apiToken)
            .append("member", member)
            .toString();
  }
}
