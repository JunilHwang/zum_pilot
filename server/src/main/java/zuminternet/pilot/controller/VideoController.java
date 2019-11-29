package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.service.VideoService;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;

  @GetMapping(value="/api/video")
  public HashMap getMusicVideoList (@RequestParam(required = true) String q) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", videoService.getVideo(q));
    return send;
  }

}
