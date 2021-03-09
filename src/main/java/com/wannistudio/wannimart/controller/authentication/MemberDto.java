package com.wannistudio.wannimart.controller.authentication;

import com.wannistudio.wannimart.domain.member.Email;
import com.wannistudio.wannimart.domain.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class MemberDto {

  @ApiModelProperty(value = "PK", required = true)
  private Long id;

  @ApiModelProperty(value = "이름", required = true)
  private String name;

  @ApiModelProperty(value = "이메일", required = true)
  private Email email;

  @ApiModelProperty(value = "로그인 횟수", required = true)
  private int loginCount;

  @ApiModelProperty(value = "최근 로그인 일시", required = true)
  private LocalDateTime lastLoginAt;

  @ApiModelProperty(value = "생성일시", required = true)
  private LocalDateTime createAt;

  public MemberDto(Member source) {
    copyProperties(source, this);
    this.lastLoginAt = source.getLastLoginAt().orElse(null);
  }
}
