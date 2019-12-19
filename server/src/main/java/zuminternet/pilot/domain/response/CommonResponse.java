package zuminternet.pilot.domain.response;

public enum CommonResponse {
  SUCCESS(1, "성공"),
  FAIL(0, "알 수 없는 에러가 발생했습니다."),
  AUTH_FAIL(-1, "로그인 후 이용해주세요."),
  SIGN_IN_FAIL(-2, "아이디 또는 비밀번호가 일치하지 않습니다."),
  SIGN_UP_FAIL(-3, "이미 중복된 아이디가 있습니다.");

  int code;
  String msg;

  CommonResponse(int code, String msg){
    this.code = code;
    this.msg = msg;
  }
  public int getCode() {
    return code;
  }
  public String getMsg() {
    return msg;
  }
}
