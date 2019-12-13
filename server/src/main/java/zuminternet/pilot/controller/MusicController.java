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

  @GetMapping(value="/api/music")
  public ListResult<MusicArticle> getMusicList (@RequestParam String category) throws Exception {
    return responseService.listResult(musicService.getMusic(category));
  }
}
