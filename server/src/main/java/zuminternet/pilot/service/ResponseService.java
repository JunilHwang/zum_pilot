package zuminternet.pilot.service;

import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.response.*;

import java.util.List;

@Service
public class ResponseService {

  /**
   * 단일 데이터를 받아온 후 Response를 반환
   * @param result : 반환할 단일 데이터 정보
   * @param <T> : 단일 데이터의 Type
   * @return { msg, code, success, result }
   */
  public <T> SingleResult<T> singleResult (T result) {
    SingleResult<T> response = new SingleResult();
    response.setResult(result);
    success(response);
    return response;
  }

  /**
   * 다중 데이터를 받아온 후 Response를 반한
   * @param result : 반환할 다중 데이터 정보
   * @param <T> : 각 데이터의 Type
   * @return { msg, code, success, result }
   */
  public <T>ListResult<T> listResult (List<T> result) {
    ListResult<T> response = new ListResult();
    response.setResult(result);
    success(response);
    return response;
  }

  /**
   * 기본적인 Response를 반환
   * @return { msg, code, success }
   */
  public CommonResult commonResult() {
    CommonResult response = new CommonResult();
    success(response);
    return response;
  }

  /**
   * Fail Response를 반환
   * @param commonResponse : Fail에 대한 정보. FAIL|AUTH_FAIL|SIGN_IN_FAIL|SIGN_UP_FAIL 등
   * @return { msg, code, success }
   */
  public CommonResult failResult(CommonResponse commonResponse) {
    CommonResult response = new CommonResult();
    response.setSuccess(false);
    response.setCode(commonResponse.getCode());
    response.setMsg(commonResponse.getMsg());
    return response;
  }

  /**
   * response에 기본적인 성공 코드를 삽입
   * @param response
   */
  private void success(CommonResult response) {
    response.setSuccess(true);
    response.setCode(CommonResponse.SUCCESS.getCode());
    response.setMsg(CommonResponse.SUCCESS.getMsg());
  }
}
