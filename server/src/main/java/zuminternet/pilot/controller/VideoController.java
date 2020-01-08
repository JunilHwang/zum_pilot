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

@RestController
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;
  private final UserService userService;
  private final ResponseService responseService;
  private final JwtTokenProvider jwtTokenProvider;

  /**
   * 검색어 기반으로 비디오 가져오기
   * @param q : 검색어. 제목+가수
   * @return
   */
  @GetMapping(value="/api/video")
  public SingleResult<Video> getVideo (@RequestParam(required = true) String q) {
    return responseService.singleResult(videoService.getBySearch(q));
  }

  /**
   * 비디오 조회수 증가
   * @param idx : 조회수가 증가될 비디오의 idx 번호
   * @return
   */
  @RequestMapping(value="/api/video/{idx}", method = RequestMethod.PATCH)
  public CommonResult incrementViewCount (@PathVariable long idx) {
    videoService.videoView(idx);
    return responseService.commonResult();
  }

  /**
   * video의 좋아요 갯수와 user의 좋아요 여부 반환
   * @param videoIdx : 비디오 idx
   * @param userIdx : 유저의 idx. 비회원은 0
   * @return
   */
  @GetMapping(value="/api/video-like/{videoIdx}")
  public SingleResult<LikeCount> getLikeCount (
    @PathVariable int videoIdx,
    @RequestParam(value = "user_idx", required = true, defaultValue = "0") int userIdx
  ) {
    return responseService.singleResult(videoService.getLikeCount(videoIdx, userIdx));
  }

  /**
   * 좋아요 토글
   * @param params : { idx }
   * @return
   */
  @PostMapping(value="/api/video-like", consumes = { "application/json" })
  public CommonResult postLike (@RequestBody Video params) {
    String userId = jwtTokenProvider.authorization();
    int videoIdx = params.getIdx().intValue();
    videoService.postLike(videoIdx, userService.get(userId).getIdx().intValue());
    return responseService.commonResult();
  }

  /**
   * 인기 영상 가져오기
   * @return
   */
  @GetMapping(value="/api/video-popular")
  public ListResult<VideoPopular> getPopular () {
    return responseService.listResult(videoService.getPopular());
  }

  /**
   * 즐겨찾기 토글
   * @param params : { idx }
   * @return
   */
  @PostMapping(value="/api/video-bookmark", consumes = { "application/json" })
  public ListResult<Video> bookmarking (@RequestBody Video params) {
    String userId = jwtTokenProvider.authorization();
    int videoIdx = params.getIdx().intValue();
    userService.setBookmark(userId, videoIdx);
    return responseService.listResult(userService.get(userId).getBookmark());
  }

  /**
   * 즐겨찾기 목록 가져오기
   * @return
   */
  @GetMapping(value="/api/video-bookmark")
  public ListResult<Video> getBookmark () {
    String userId = jwtTokenProvider.authorization();
    return responseService.listResult(userService.get(userId).getBookmark());
  }
}
