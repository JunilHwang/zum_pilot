package zuminternet.pilot.service;

import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.response.*;

import java.util.List;

@Service
public class ResponseService {

  public <T> SingleResult<T> singleResult (T data) {
    SingleResult<T> response = new SingleResult();
    response.setResult(data);
    success(response);
    return response;
  }

  public <T>ListResult<T> listResult (List<T> list) {
    ListResult<T> response = new ListResult();
    response.setResult(list);
    success(response);
    return response;
  }

  public CommonResult successResult() {
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
