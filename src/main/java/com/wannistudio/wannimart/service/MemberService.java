package com.wannistudio.wannimart.service;

import com.wannistudio.wannimart.controller.member.JoinRequest;
import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.member.Email;
import com.wannistudio.wannimart.domain.member.Member;
import com.wannistudio.wannimart.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
  private final MemberRepository memberRepository;

  @Transactional
  public Member join(JoinRequest joinRequest) {
    checkArgument(isNotEmpty(joinRequest.getCredential()), "password must be provided");
    checkArgument(
            joinRequest.getCredential().length() >= 4 && joinRequest.getCredential().length() <= 15,
            "password length must be between 4 and 15 characters."
    );

    Member member = Member.of(joinRequest);

    return memberRepository.save(member);
  }

  @Transactional
  public Member login(String account, String password) {
    checkNotNull(password, "password must be provided.");

    Member member = findByAccount(account).get();
    member.login(account, password);
    member.afterLoginSuccess();
    return member;
  }

  public Optional<Member> findByAccount(String account) {
    checkNotNull(account, "account must be provided.");
    return memberRepository.findByAccount(account);
  }

  public Optional<Member> findById(Id<Member, Long> memberId) {
    checkNotNull(memberId, "memberId must be provided.");
    return memberRepository.findById(memberId.value());
  }

  public Optional<Member> findByEmail(Email email) {
    checkNotNull(email, "email must be provided.");
    return memberRepository.findByEmail(email);
  }
}
