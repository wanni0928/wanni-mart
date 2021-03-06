package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.controller.member.JoinRequest;
import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.member.Gender;
import com.wannistudio.wannimart.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
  @Autowired
  MemberService memberService;
  JoinRequest joinRequest;

  @BeforeEach
  void testUserJoinRequest() {
    joinRequest = JoinRequest.builder()
            .principal("wanni123")
            .credential("12345")
            .name("와니")
            .email("wanni@gmail.com")
            .phoneNumber("010-1111-2222")
            .cityStreetAddress("서울")
            .residentAddress("어딘가")
            .gender(Gender.MEN.name())
            .year(2016)
            .month(4)
            .day(13)
            .termsOfUse(true)
            .personalInfoUsage(true)
            .marketingUsage(true)
            .isAdult(true)
            .build();
  }

  @Test
  void join() {
    final Member join = memberService.join(joinRequest);
    final Member byId = memberService.findById(Id.of(Member.class, join.getId())).get();
    assertThat(join).isEqualTo(byId);
  }

  @Test
  void login() {}
}