package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.dto.MusicArticle;
import zuminternet.pilot.helper.crawler.MusicCrawler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

  private final MusicCrawler crawler;

  @Cacheable(cacheNames = "MusicCache", key = "#category")
  public List<MusicArticle> getMusic (String category) {
    return crawler.getList(category);
  }

}
