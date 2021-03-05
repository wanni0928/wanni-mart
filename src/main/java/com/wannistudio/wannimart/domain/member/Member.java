package com.wannistudio.wannimart.domain.member;

import com.wannistudio.wannimart.controller.member.JoinRequest;
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

  public static Member of(JoinRequest joinRequest) {
    return Member.builder()
            .account(joinRequest.getPrincipal())
            .password(joinRequest.getCredential())
            .name(joinRequest.getName())
            .email(new Email(joinRequest.getEmail()))
            .phoneNumber(joinRequest.getPhoneNumber())
            .address(new Address(joinRequest.getCityStreetAddress(), joinRequest.getResidentAddress()))
            .gender(Gender.MEN)
            .birth(new Birth(joinRequest.getYear(), joinRequest.getMonth(), joinRequest.getDay()))
            .agreement(new Agreement(joinRequest.isTermsOfUse(), joinRequest.isPersonalInfoUsage(), joinRequest.isMarketingUsage(), joinRequest.isAdult()))
            .createAt(LocalDateTime.now())
            .build();
  }

  // jwt 인증토큰 생성.
  public String newApiToken() {
    return "test";
  }

  public void login(String account, String password) {
    System.out.println("try login");
    System.out.println("account : " + account);
    System.out.println("password : " + password);
  }

  public void afterLoginSuccess() {
    loginCount++;
    lastLoginAt = now();
  }
  public Optional<LocalDateTime> getLastLoginAt() {
    return ofNullable(lastLoginAt);
  }
}
