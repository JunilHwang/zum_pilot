package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.config.security.JwtTokenProvider;
import zuminternet.pilot.entity.User;
import zuminternet.pilot.service.UserService;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @PostMapping(value="/api/sign-in", consumes = { "application/json" })
  public HashMap signIn (@RequestBody HashMap params) {
    HashMap send = new HashMap();
    User user = userService.fetch(params);
    boolean success = user != null;
    send.put("success", success);
    if (success) {
      send.put("result", user);
      send.put("token", jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
    }
    return send;
  }

  @PostMapping(value="/api/sign-up", consumes = { "application/json" })
  public HashMap signUp (@RequestBody HashMap params) {
    HashMap send = new HashMap();
    send.put("success", userService.insert(params));
    return send;
  }
}
