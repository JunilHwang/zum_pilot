package zuminternet.pilot.advice.exception;

public class AuthException extends RuntimeException {
  public AuthException(String msg, Throwable t) {
    super(msg, t);
  }

  public AuthException(String msg) {
    super(msg);
  }

  public AuthException() {
    super();
  }
}
