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
  @Cacheable(value="musicList")
  @GetMapping(value="/api/music")
  public HashMap getMusicList () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("articles", Crawler.getMusicList());
    return send;
  }

  @GetMapping(value="/api/music-video")
  public HashMap getMusicVideoList (@RequestParam(required = true) String q) {
    HashMap send = new HashMap();
    YoutubeSearch youtubeSearch = new YoutubeSearch();
    List result = youtubeSearch.search(q);
    send.put("success", true);
    send.put("result", result);
    return send;
  }
}
