package com.wannistudio.wannimart.domain.member;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  private String account;

  private String password;

  private String name;

  @Embedded
  private Email email;

  private String phoneNumber;

  @Embedded
  private Address address;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Embedded
  private Birth birth;

  @Embedded
  private Agreement agreement;

  private int loginCount;

  private LocalDateTime lastLoginAt;

  private LocalDateTime createAt;

  // jwt 인증토큰 생성.
  public String newApiToken() {
    return "test";
  }

  public void login() {
    System.out.println("try login");
  }

  public void afterLoginSuccess() {
    loginCount++;
    lastLoginAt = now();
  }
  public Optional<LocalDateTime> getLastLoginAt() {
    return ofNullable(lastLoginAt);
  }
}
