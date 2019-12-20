package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zuminternet.pilot.config.security.JwtTokenProvider;
import zuminternet.pilot.domain.dao.entity.Video;
import zuminternet.pilot.domain.dto.LikeCount;
import zuminternet.pilot.domain.dto.VideoPopular;
import zuminternet.pilot.domain.response.CommonResult;
import zuminternet.pilot.domain.response.ListResult;
import zuminternet.pilot.domain.response.SingleResult;
import zuminternet.pilot.service.ResponseService;
import zuminternet.pilot.service.UserService;
import zuminternet.pilot.service.VideoService;

import javax.security.auth.message.AuthException;

@RestController
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;
  private final UserService userService;
  private final ResponseService responseService;
  private final JwtTokenProvider jwtTokenProvider;

  @GetMapping(value="/api/video")
  public SingleResult<Video> getVideo (@RequestParam(required = true) String q) {
    return responseService.singleResult(videoService.getBySearch(q));
  }

  @RequestMapping(value="/api/video-view/{idx}", method = RequestMethod.PATCH)
  public CommonResult incrementViewCount (@PathVariable long idx) {
    videoService.videoView(idx);
    return responseService.commonResult();
  }

  @GetMapping(value="/api/video-like/{videoIdx}")
  public SingleResult<LikeCount> getLikeCount (@PathVariable int videoIdx, @RequestParam(value = "user_idx", required = true) int userIdx) {
    return responseService.singleResult(videoService.getLikeCount(videoIdx, userIdx));
  }

  @PostMapping(value="/api/video-like", consumes = { "application/json" })
  public CommonResult postLike (@RequestBody Video params) {
    String userId = jwtTokenProvider.authorization();
    int videoIdx = params.getIdx().intValue();
    videoService.postLike(videoIdx, userService.get(userId).getIdx().intValue());
    return responseService.commonResult();
  }

  @GetMapping(value="/api/video-popular")
  public ListResult<VideoPopular> getPopular () {
    return responseService.listResult(videoService.getPopular());
  }

  @PostMapping(value="/api/video-bookmark", consumes = { "application/json" })
  public ListResult<Video> bookmarking (@RequestBody Video params) {
    String userId = jwtTokenProvider.authorization();
    int videoIdx = params.getIdx().intValue();
    userService.setBookmark(userId, videoIdx);
    return responseService.listResult(userService.get(userId).getBookmark());
  }

  @GetMapping(value="/api/video-bookmark")
  public ListResult<Video> getBookmark () {
    String userId = jwtTokenProvider.authorization();
    return responseService.listResult(userService.get(userId).getBookmark());
  }
}
