package zuminternet.pilot.advice.exception;

public class UserIdNotFoundException extends RuntimeException {
  public UserIdNotFoundException(String msg, Throwable t) {
    super(msg, t);
  }

  public UserIdNotFoundException(String msg) {
    super(msg);
  }

  public UserIdNotFoundException() {
    super();
  }
}
