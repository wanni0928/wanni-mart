package com.wannistudio.wannimart.controller.api;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
  private final String message;
  private final int status;

  ApiError(Throwable throwable, HttpStatus status) {
    this(throwable.getMessage(), status);
  }

  ApiError(String message, HttpStatus status) {
    this.message = message;
    this.status = status.value();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("message", message)
            .append("status", status)
            .toString();
  }
}