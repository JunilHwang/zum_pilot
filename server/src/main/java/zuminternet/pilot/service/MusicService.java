package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.MusicArticle;
import zuminternet.pilot.helper.Crawler;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MusicService {

  @Cacheable(cacheNames = "MusicCache", key = "#category")
  public List<MusicArticle> getMusic (String category) {
    return Crawler.getMusicList(category);
  }

}
