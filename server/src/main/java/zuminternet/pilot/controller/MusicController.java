package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

@RequiredArgsConstructor
@RestController
public class MusicController {

  private final MusicService musicService;

  @GetMapping(value="/api/music")
  public HashMap getMusicList (@RequestParam String category) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", musicService.getMusic(category));
    return send;
  }
}
