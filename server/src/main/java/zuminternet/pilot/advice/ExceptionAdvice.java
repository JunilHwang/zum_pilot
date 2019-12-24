package zuminternet.pilot.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zuminternet.pilot.advice.exception.*;
import zuminternet.pilot.domain.response.*;
import zuminternet.pilot.service.ResponseService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
  private final ResponseService responseService;

  /**
   * 기본 예외 처리에 대한 response 반환
   * @param request
   * @param e
   * @return FAIL
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult defaultException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.FAIL);
  }

  /**
   * 로그인 오류에 대한 response 예외처리
   * @param request
   * @param e
   * @return SIGN_IN_FAIL
   */
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult userNotFoundException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.SIGN_IN_FAIL);
  }

  /**
   * 회원가입 오류에 대한 response 예외 처리
   * @param request
   * @param e
   * @return SIGN_UP_FAIL
   */
  @ExceptionHandler(SignUpException.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult signUpException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.SIGN_UP_FAIL);
  }

  /**
   * 인증에 대한 response 예외 처리
   * @param request
   * @param e
   * @return AUTH_FAIL
   */
  @ExceptionHandler(AuthException.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult authException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.AUTH_FAIL);
  }

  /**
   * User select 에 대한 response 예외 처리
   * @param request
   * @param e
   * @return USER_FAIL
   */
  @ExceptionHandler(UserIdNotFoundException.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult userIdNotFoundException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.USER_FAIL);
  }

  /**
   * Video Select 에 대한 Response 예외 처리
   * @param request
   * @param e
   * @return VIDEO_FAIL
   */
  @ExceptionHandler(VideoNotFoundException.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult videoNotFoundException(HttpServletRequest request, Exception e) {
    return responseService.failResult(CommonResponse.VIDEO_FAIL);
  }
}
