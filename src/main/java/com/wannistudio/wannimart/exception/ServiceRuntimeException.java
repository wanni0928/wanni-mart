package com.wannistudio.wannimart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ServiceRuntimeException extends RuntimeException {
  private final String messageKey;
  private final String detailKey;
  private final Object[] params;
}
