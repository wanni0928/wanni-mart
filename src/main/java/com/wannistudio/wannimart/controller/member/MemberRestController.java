package com.wannistudio.wannimart.controller.member;

import com.wannistudio.wannimart.config.jwt.Jwt;
import com.wannistudio.wannimart.config.jwt.JwtAuthentication;
import com.wannistudio.wannimart.controller.api.ApiResult;
import com.wannistudio.wannimart.controller.authentication.MemberDto;
import com.wannistudio.wannimart.domain.member.Member;
import com.wannistudio.wannimart.domain.member.Role;
import com.wannistudio.wannimart.exception.NotFoundException;
import com.wannistudio.wannimart.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.wannistudio.wannimart.controller.api.ApiResult.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberRestController {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final Jwt jwt;
  private final MemberService memberService;

  @PostMapping(path = "user/join")
  public ApiResult<JoinResult> join(@RequestBody JoinRequest joinRequest) {
    final Member join = memberService.join(joinRequest);
    final String apiToken = join.newApiToken(jwt, new String[]{Role.USER.value()});
    return OK(
            new JoinResult(apiToken, join)
    );
  }

  @GetMapping(path = "user/me")
  public ApiResult<MemberDto> me(@AuthenticationPrincipal JwtAuthentication authentication) {
    return OK(
            memberService.findById(authentication.id)
                    .map(MemberDto::new)
                    .orElseThrow(() -> new NotFoundException(Member.class, authentication.id))
    );
  }
}
