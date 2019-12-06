package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zuminternet.pilot.entity.User;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.service.UserService;
import zuminternet.pilot.service.VideoService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;
  private final UserService userService;

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

  @PostMapping(value="/api/video-like", consumes = { "application/json" })
  public HashMap postLike (@RequestBody HashMap params) {
    int videoIdx = Integer.valueOf(params.get("idx").toString());
    HashMap send = new HashMap();
    boolean success = false;
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String userId = auth.getName();
      if (userId == "anonymousUser") {
        send.put("error", "Token Error");
        send.put("errorMessage", "로그인 후 이용해주세요");
      } else {
        int userIdx = userService.getUserIdx(userId);
        videoService.postLike(videoIdx, userIdx);
        success = true;
      }
    } catch (Exception e) {
      send.put("error", "Server Error");
      send.put("errorMessage", "알 수 없는 에러가 발생했습니다");
    }
    send.put("success", success);
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
  public HashMap bookmark (@RequestBody HashMap params) {
    HashMap send = new HashMap();
    send.put("success", true);
    return send;
  }
}
