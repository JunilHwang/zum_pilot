package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zuminternet.pilot.helper.ResponseGenerator;
import zuminternet.pilot.service.UserService;
import zuminternet.pilot.service.VideoService;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;
  private final UserService userService;

  @GetMapping(value="/api/video")
  public HashMap getVideoList (@RequestParam(required = true) String q) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", videoService.getList(q));
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

  @PostMapping(value="/api/video-like", consumes = { "application/json" })
  public HashMap postLike (@RequestBody HashMap params) {
    int videoIdx = Integer.valueOf(params.get("idx").toString());
    HashMap send = ResponseGenerator.withAuth();
    if ((boolean)send.get("success")) {
      String userId = send.get("userId").toString();
      videoService.postLike(
        videoIdx,
        userService.get(userId).getIdx().intValue()
      );
    }
    return send;
  }

  @GetMapping(value="/api/video-popular")
  public HashMap getPopular () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", videoService.getPopular());
    return send;
  }

  @PostMapping(value="/api/video-bookmark", consumes = { "application/json" })
  public HashMap bookmarking (@RequestBody HashMap params) {
    int videoIdx = Integer.valueOf(params.get("idx").toString());
    HashMap send = ResponseGenerator.withAuth();
    if ((boolean)send.get("success")) {
      String userId = send.get("userId").toString();
      send.put("result", userService.setBookmark(userId, videoIdx));
    }
    return send;
  }

  @GetMapping(value="/api/video-bookmark")
  public HashMap getBookmark () {
    HashMap send = ResponseGenerator.withAuth();
    if ((boolean)send.get("success")) {
      String userId = send.get("userId").toString();
      send.put("result", userService.getBookmark(userId));
    }
    return send;
  }
}
