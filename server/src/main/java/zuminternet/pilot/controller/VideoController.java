package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zuminternet.pilot.service.VideoService;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;

  @GetMapping(value="/api/video")
  public HashMap getVideoList (@RequestParam(required = true) String q) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", videoService.getVideo(q));
    return send;
  }

  @RequestMapping(value="/api/video-view/{idx}", method = RequestMethod.PATCH)
  public HashMap incrementViewCount (@PathVariable long idx) {
    HashMap send = new HashMap();
    send.put("success", videoService.videoView(idx));
    return send;
  }

  @GetMapping(value="/api/video-like/{videoIdx}")
  public HashMap getLike (
    @PathVariable int videoIdx,
    @RequestParam(value = "user_idx", required = true) int userIdx
  ) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", videoService.getLike(videoIdx, userIdx));
    return send;
  }

}
