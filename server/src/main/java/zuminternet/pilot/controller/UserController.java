package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping(value="/api/user")
  public HashMap getUser () {
    HashMap send = new HashMap();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    send.put("success", true);
    send.put("result", auth);
    return send;
  }
}
