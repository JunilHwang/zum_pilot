package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.advice.exception.UserNotFoundException;
import zuminternet.pilot.config.security.JwtTokenProvider;
import zuminternet.pilot.domain.dao.entity.User;
import zuminternet.pilot.domain.response.CommonResponse;
import zuminternet.pilot.domain.response.CommonResult;
import zuminternet.pilot.domain.response.SingleResult;
import zuminternet.pilot.service.ResponseService;
import zuminternet.pilot.service.UserService;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;
  private final ResponseService responseService;

  /**
   * JWT 기반 Login 처리
   * @param params : { id, pw }
   * @return
   */
  @PostMapping(value="/api/sign-in", consumes = { "application/json" })
  public SingleResult<User> signIn (@RequestBody User params) {
    User user = userService.fetch(params);
    user.setToken(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
    return responseService.singleResult(user);
  }

  /**
   * 회원가입
   * @param params : { id, pw, name }
   * @return
   */
  @PostMapping(value="/api/sign-up", consumes = { "application/json" })
  public CommonResult signUp (@RequestBody User params) {
    userService.insert(params);
    return responseService.commonResult();
  }

  /**
   * Token 인증
   * @return
   */
  @GetMapping(value="/api/user")
  public SingleResult<String> getUser () {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return responseService.singleResult(auth.getName());
  }
}
