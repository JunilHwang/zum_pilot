package zuminternet.pilot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class NewsController {
  @GetMapping(value="/news")
  public HashMap getNews () {
    HashMap send = new HashMap();
    send.put("success", true);
    return send;
  }
}
