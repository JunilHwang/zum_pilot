package zuminternet.pilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(value={"/", "/chart", "/popular"})
  public String home () {
    return "index";
  }
}
