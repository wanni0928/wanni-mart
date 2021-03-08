package com.wannistudio.wannimart.config.jwt;

import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.member.Email;
import com.wannistudio.wannimart.domain.member.Member;
import lombok.ToString;

import static com.google.common.base.Preconditions.checkNotNull;

@ToString
public class JwtAuthentication {
  public final Id<Member, Long> id;
  public final String name;
  public final Email email;
  public final String account;

  public JwtAuthentication(Long id, String name, Email email, String account) {
    checkNotNull(id, "id must be provided.");
    checkNotNull(name, "name must be provided.");
    checkNotNull(email, "email must be provided.");
    checkNotNull(account, "account must be provided.");

    this.id = Id.of(Member.class, id);
    this.name = name;
    this.email = email;
    this.account = account;
  }
}
