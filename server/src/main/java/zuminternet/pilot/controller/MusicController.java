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

import java.util.HashMap;
import java.util.List;

@RestController
public class MusicController {

  @Autowired VideoGroupRepository videoGroupRepository;
  @Autowired VideoRepository videoRepository;

  @GetMapping(value="/api/music")
  public HashMap getMusicList () {
    HashMap send = new HashMap();
    MusicArticle[] list = Crawler.getMusicList();
    send.put("success", true);
    send.put("result", list);
    return send;
  }

  @GetMapping(value="/api/music-video")
  public HashMap getMusicVideoList (@RequestParam(required = true) String q) {
    HashMap send = new HashMap();
    VideoGroup parent = videoGroupRepository.findBySearchTitle(q);
    List result;
    if (parent == null) {
      parent = new VideoGroup(q);
      result = YoutubeSearch.execute(q);
      videoRepository.saveAll(result);
      parent.getVideoList().addAll(result);
      videoGroupRepository.save(parent);
    } else {
      result = parent.getVideoList();
    }
    send.put("success", true);
    send.put("result", result);
    return send;
  }
}
