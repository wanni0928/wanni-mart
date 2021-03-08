package com.wannistudio.wannimart.controller.authentication;

import com.wannistudio.wannimart.domain.member.Email;
import com.wannistudio.wannimart.domain.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class MemberDto {
  private Long id;
  private String name;
  private Email email;
  private int loginCount;
  private LocalDateTime lastLoginAt;
  private LocalDateTime createAt;

  public MemberDto(Member source) {
    copyProperties(source, this);
    this.lastLoginAt = source.getLastLoginAt().orElse(null);
  }
}
