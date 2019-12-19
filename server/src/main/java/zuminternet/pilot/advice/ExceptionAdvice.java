package zuminternet.pilot.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zuminternet.pilot.advice.exception.AuthException;
import zuminternet.pilot.advice.exception.UserNotFoundException;
import zuminternet.pilot.domain.response.CommonResponse;
import zuminternet.pilot.domain.response.CommonResult;
import zuminternet.pilot.service.ResponseService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
  private final ResponseService responseService;

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected CommonResult defaultException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.FAIL);
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult userNotFoundException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.SIGN_IN_FAIL);
  }

  @ExceptionHandler(AuthException.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult authException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.AUTH_FAIL);
  }
}
