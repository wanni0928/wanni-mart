package com.wannistudio.wannimart.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wannistudio.wannimart.config.jwt.Jwt;
import com.wannistudio.wannimart.controller.member.JoinRequest;
import com.wannistudio.wannimart.domain.order.Order;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@Entity
@Builder
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

  public Member(String account, String password, String name, Email email, String phoneNumber, Address address, Gender gender, Birth birth, Agreement agreement) {
    this(null, account, password, name, email, phoneNumber, address, gender, birth, agreement, 0, null, null, null);
  }

  public Member(Long id, String account, String password, String name, Email email, String phoneNumber, Address address, Gender gender, Birth birth, Agreement agreement, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt, List<Order> orders) {
    checkArgument(isNotEmpty(name), "name must be provided.");
    checkArgument(
            name.length() >= 1 && name.length() <= 10,
            "name length must be between 1 and 10 characters."
    );
    checkNotNull(email, "email must be provided.");
    checkNotNull(password, "password must be provided.");

    this.id = id;
    this.account = account;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.gender = gender;
    this.birth = birth;
    this.agreement = agreement;
    this.loginCount = loginCount;
    this.lastLoginAt = lastLoginAt;
    this.createAt = createAt;
    this.orders = orders;
  }

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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("account", account)
            .append("password", "[PROTECTED]")
            .append("name", name)
            .append("email", email)
            .append("phoneNumber", phoneNumber)
            .append("address", address)
            .append("gender", gender)
            .append("birth", birth)
            .append("agreement", agreement)
            .append("loginCount", loginCount)
            .append("createAt", createAt)
            .append("orders", orders)
            .toString();
  }
}
