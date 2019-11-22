package zuminternet.pilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.domain.MusicArticle;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.helper.Crawler;
import zuminternet.pilot.helper.YoutubeSearch;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoRepository;
import zuminternet.pilot.service.MusicService;

import java.util.HashMap;
import java.util.List;

@RestController
public class MusicController {

  @Autowired
  private MusicService musicService;

  @GetMapping(value="/api/music")
  public HashMap getMusicList () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", musicService.getMusic());
    return send;
  }

  @GetMapping(value="/api/music-video")
  public HashMap getMusicVideoList (@RequestParam(required = true) String q) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", musicService.getVideo(q));
    return send;
  }
}
