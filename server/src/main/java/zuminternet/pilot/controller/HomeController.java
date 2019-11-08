package zuminternet.pilot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HomeController {

  @GetMapping("/")
  public HashMap home () {
    HashMap sendData = new HashMap();
    sendData.put("success", true);
    return sendData;
  }
}
