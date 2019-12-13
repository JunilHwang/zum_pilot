package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.service.MusicService;

import java.util.HashMap;

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
