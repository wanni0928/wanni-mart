package com.wannistudio.wannimart.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.config.jwt.Jwt;
import com.wannistudio.wannimart.controller.member.JoinRequest;
import com.wannistudio.wannimart.domain.order.Order;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  @Column(unique = true, nullable = false)
  private String account;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Embedded
  @Column(nullable = false)
  private Email email;

  @Column(nullable = false)
  private String phoneNumber;

  @Embedded
  @Column(nullable = false)
  private Address address;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;

  @Embedded
  @Column(nullable = false)
  private Birth birth;

  @Embedded
  @Column(nullable = false)
  private Agreement agreement;

  @Column(nullable = false)
  private int loginCount;

  private LocalDateTime lastLoginAt;

  private LocalDateTime createAt;

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

  public static Member of(PasswordEncoder passwordEncoder, JoinRequest joinRequest) {
    return Member.builder()
            .account(joinRequest.getPrincipal())
            .password(passwordEncoder.encode(joinRequest.getCredential()))
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
  public String newApiToken(Jwt jwt, String[] roles) {
    Jwt.Claims claims = Jwt.Claims.of(id, name, account, email, roles);
    return jwt.newToken(claims);
  }

  public void login(PasswordEncoder passwordEncoder, String credentials) {
    if(!passwordEncoder.matches(credentials, password))
      throw new IllegalArgumentException("Bad credentials");
  }

  public void afterLoginSuccess() {
    loginCount++;
    lastLoginAt = now();
  }
  public Optional<LocalDateTime> getLastLoginAt() {
    return ofNullable(lastLoginAt);
  }
}
