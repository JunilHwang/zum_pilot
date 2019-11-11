package zuminternet.pilot.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.helper.Crawler;

import java.util.HashMap;

@RestController
public class MusicController {
  @Cacheable(value="musicList")
  @GetMapping(value="/api/music")
  public HashMap getMusicList () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("articles", Crawler.getMusicList());
    return send;
  }
}
