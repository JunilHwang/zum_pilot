package zuminternet.pilot.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.dao.entity.User;
import zuminternet.pilot.domain.response.CommonResult;
import zuminternet.pilot.domain.response.ListResult;
import zuminternet.pilot.domain.response.SingleResult;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ResponseService {
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

  public <T> SingleResult<T> singleResult (T data) {
    SingleResult<T> response = new SingleResult();
    response.setResult(data);
    setSuccessResult(response);
    return response;
  }

  public <T>ListResult<T> listResult (List<T> list) {
    ListResult<T> response = new ListResult();
    response.setResult(list);
    setSuccessResult(response);
    return response;
  }

  public CommonResult successResult() {
    CommonResult response = new CommonResult();
    setSuccessResult(response);
    return response;
  }

  public CommonResult defaultFail() {
    CommonResult response = new CommonResult();
    setFailResult(response, CommonResponse.FAIL);
    return response;
  }

  public CommonResult authFail() {
    CommonResult response = new CommonResult();
    setFailResult(response, CommonResponse.AUTH_FAIL);
    return response;
  }

  public CommonResult signInFail() {
    CommonResult response = new CommonResult();
    setFailResult(response, CommonResponse.SIGN_IN_FAIL);
    return response;
  }

  public CommonResult signUpFail() {
    CommonResult response = new CommonResult();
    setFailResult(response, CommonResponse.SIGN_UP_FAIL);
    return response;
  }

  private void setFailResult(CommonResult response, CommonResponse fail) {
    response.setSuccess(false);
    response.setCode(fail.getCode());
    response.setMsg(fail.getMsg());
  }

  private void setSuccessResult(CommonResult response) {
    response.setSuccess(true);
    response.setCode(CommonResponse.SUCCESS.getCode());
    response.setMsg(CommonResponse.SUCCESS.getMsg());
  }
}
