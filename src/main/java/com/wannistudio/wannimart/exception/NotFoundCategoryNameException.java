package com.wannistudio.wannimart.exception;

public class NotFoundCategoryNameException extends RuntimeException  {
  public NotFoundCategoryNameException() {
    super();
  }

  public NotFoundCategoryNameException(String message) {
    super(message);
  }

  public NotFoundCategoryNameException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundCategoryNameException(Throwable cause) {
    super(cause);
  }

  protected NotFoundCategoryNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
