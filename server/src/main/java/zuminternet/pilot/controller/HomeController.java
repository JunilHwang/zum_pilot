package zuminternet.pilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  /**
   * front-end routing 처리
   * @return /static/index.html
   */
  @GetMapping(value={"/", "/chart", "/popular", "/sign-in", "/sign-up", "/bookmark"})
  public String home () {
    return "index";
  }
}
