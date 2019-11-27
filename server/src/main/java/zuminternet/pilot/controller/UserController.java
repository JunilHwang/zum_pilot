package zuminternet.pilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.entity.User;
import zuminternet.pilot.service.UserService;

import java.util.HashMap;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(value="/api/sign-in", consumes = { "application/json" })
  public HashMap signIn (@RequestBody HashMap params) {
    HashMap send = new HashMap();
    User user = userService.fetch(params);
    boolean success = user != null;
    send.put("success", success);
    if (success) {
      send.put("result", user);
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
