package com.wannistudio.wannimart.controller.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class ApiResult<T> {
  private final boolean success;
  private final T response;
  private final ApiError error;
  public static <T> ApiResult<T> OK(T response) {
    return new ApiResult<>(true, response, null);
  }

  public static ApiResult<?> ERROR(Throwable throwable, HttpStatus status) {
    return new ApiResult<>(false, null, new ApiError(throwable, status));
  }

  public static ApiResult<?> ERROR(String errorMessage, HttpStatus status) {
    return new ApiResult<>(false, null, new ApiError(errorMessage, status));
  }

  public boolean isSuccess() {
    return success;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("success", success)
            .append("response", response)
            .append("error", error)
            .toString();
  }
}
