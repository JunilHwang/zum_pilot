package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.domain.dto.MusicArticle;
import zuminternet.pilot.domain.response.ListResult;
import zuminternet.pilot.domain.response.SingleResult;
import zuminternet.pilot.service.MusicService;
import zuminternet.pilot.service.ResponseService;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MusicController {

  private final MusicService musicService;
  private final ResponseService responseService;

  /**
   * 음원 크롤링 정보 가져오기
   * @param category : 실시간|일간|발라드|댄스|히합|R&B/Soule|POP
   * @return
   * @throws Exception
   */
  @GetMapping(value="/api/music")
  public ListResult<MusicArticle> getMusicList (@RequestParam String category) throws Exception {
    return responseService.listResult(
      Optional.ofNullable(musicService.getMusic(category)).orElseThrow(Exception::new)
    );
  }
}
