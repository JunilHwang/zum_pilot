package zuminternet.pilot.advice.exception;

public class VideoNotFoundException extends RuntimeException {
  public VideoNotFoundException(String msg, Throwable t) {
    super(msg, t);
  }

  public VideoNotFoundException(String msg) {
    super(msg);
  }

  public VideoNotFoundException() {
    super();
  }
}
