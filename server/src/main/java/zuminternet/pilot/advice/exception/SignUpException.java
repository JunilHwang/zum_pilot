package zuminternet.pilot.advice.exception;

public class SignUpException extends RuntimeException {
  public SignUpException(String msg, Throwable t) {
    super(msg, t);
  }

  public SignUpException(String msg) {
    super(msg);
  }

  public SignUpException() {
    super();
  }
}
