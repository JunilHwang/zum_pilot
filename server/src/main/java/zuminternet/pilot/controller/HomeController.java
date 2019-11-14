package zuminternet.pilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoRepository;

import java.util.HashMap;

@RestController
public class HomeController {

  @Autowired  VideoRepository videoRepository;
  @Autowired  VideoGroupRepository videoGroupRepository;

  @GetMapping("/")
  public HashMap home () {
    HashMap sendData = new HashMap();
    sendData.put("success", true);
    return sendData;
  }

  @PostMapping("/api/video")
  public HashMap setVideo (String title, String search_title, String thumbnail, String video_id) {
    HashMap send = new HashMap();
    VideoGroup videoGroup = videoGroupRepository.findBySearchTitle(search_title);
    videoRepository.save(new Video(title, video_id, thumbnail, videoGroup));
    send.put("success", true);
    return send;
  }

  @GetMapping("/api/video")
  public HashMap getVideo () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", videoRepository.findAll());
    return send;
  }

  @PostMapping("/api/video-group")
  public HashMap setVideoGroup (String search_title) {
    HashMap send = new HashMap();
    videoGroupRepository.save(new VideoGroup(search_title));
    send.put("success", true);
    return send;
  }

  @GetMapping("/api/video-group")
  public HashMap getVideoGroup (@RequestParam(required = true) String search_title) {
    HashMap send = new HashMap();
    VideoGroup row = videoGroupRepository.findBySearchTitle(search_title);
    send.put("success", true);
    send.put("result", row);
    return send;
  }
}
