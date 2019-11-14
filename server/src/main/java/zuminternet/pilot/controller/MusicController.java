package zuminternet.pilot.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.domain.MusicArticle;
import zuminternet.pilot.helper.Crawler;
import zuminternet.pilot.helper.YoutubeSearch;

import java.util.HashMap;
import java.util.List;

@RestController
public class MusicController {

  @GetMapping(value="/api/music")
  public HashMap getMusicList () {
    HashMap send = new HashMap();
    MusicArticle[] list = Crawler.getMusicList();
    // list[0].setVideoList(YoutubeSearch.execute(list[0].getTitle()));
    send.put("success", true);
    send.put("result", list);
    return send;
  }

  @GetMapping(value="/api/music-video")
  public HashMap getMusicVideoList (@RequestParam(required = true) String q) {
    HashMap send = new HashMap();
    List result = YoutubeSearch.execute(q);
    send.put("success", true);
    send.put("result", result);
    return send;
  }
}
