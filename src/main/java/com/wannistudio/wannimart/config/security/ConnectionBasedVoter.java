package com.wannistudio.wannimart.config.security;

import com.wannistudio.wannimart.config.jwt.JwtAuthentication;
import com.wannistudio.wannimart.config.jwt.JwtAuthenticationToken;
import com.wannistudio.wannimart.domain.common.Id;
import com.wannistudio.wannimart.domain.member.Member;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ClassUtils.isAssignable;

public class ConnectionBasedVoter implements AccessDecisionVoter<FilterInvocation> {

  private final RequestMatcher requiresAuthorizationRequestMatcher;

  private final Function<String, Id<Member, Long>> idExtractor;

  public ConnectionBasedVoter(RequestMatcher requiresAuthorizationRequestMatcher, Function<String, Id<Member, Long>> idExtractor) {
    checkNotNull(requiresAuthorizationRequestMatcher, "requiresAuthorizationRequestMatcher must be provided.");
    checkNotNull(idExtractor, "idExtractor must be provided.");

    this.requiresAuthorizationRequestMatcher = requiresAuthorizationRequestMatcher;
    this.idExtractor = idExtractor;
  }

  @Override
  public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
    HttpServletRequest request = fi.getRequest();


    if (!requiresAuthorization(request)) {
      System.out.println("ConnectionBasedVoter.vote.!requiresAuthorization(request)");
      return ACCESS_GRANTED;
    }

    if (!isAssignable(JwtAuthenticationToken.class, authentication.getClass())) {
      System.out.println("ConnectionBasedVoter.vote.!isAssignable(JwtAuthenticationToken.class, authentication.getClass())");
      return ACCESS_ABSTAIN;
    }

    JwtAuthentication jwtAuth = (JwtAuthentication) authentication.getPrincipal();
    Id<Member, Long> targetId = obtainTargetId(request);

    // 본인 자신
    if (jwtAuth.id.equals(targetId)) {
      return ACCESS_GRANTED;
    }

    return ACCESS_DENIED;
  }

  private boolean requiresAuthorization(HttpServletRequest request) {
    return requiresAuthorizationRequestMatcher.matches(request);
  }

  private Id<Member, Long> obtainTargetId(HttpServletRequest request) {
    return idExtractor.apply(request.getRequestURI());
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return isAssignable(FilterInvocation.class, clazz);
  }
}
