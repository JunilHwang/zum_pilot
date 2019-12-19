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
   * @return success 정보들 + 단일 데이터
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
   * @return success 정보들 + 다중 데이터
   */
  public <T>ListResult<T> listResult (List<T> result) {
    ListResult<T> response = new ListResult();
    response.setResult(result);
    success(response);
    return response;
  }

  public CommonResult commonResult() {
    CommonResult response = new CommonResult();
    success(response);
    return response;
  }

  public CommonResult failResult(CommonResponse commonResponse) {
    CommonResult response = new CommonResult();
    response.setSuccess(false);
    response.setCode(commonResponse.getCode());
    response.setMsg(commonResponse.getMsg());
    return response;
  }

  private void success(CommonResult response) {
    response.setSuccess(true);
    response.setCode(CommonResponse.SUCCESS.getCode());
    response.setMsg(CommonResponse.SUCCESS.getMsg());
  }
}
