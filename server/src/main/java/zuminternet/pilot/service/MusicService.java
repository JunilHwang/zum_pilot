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

  /**
   * 음원 차트에서 카테고리를 기준으로 크롤링을 해옴
   * 그리고 카테고리를 기준으로 캐싱 처리
   * @param category : 실시간|일간|발라드|댄스|히합|R&B/Soule|POP
   * @return 맵핑된 음원 목록. 총 100개
   */
  @Cacheable(cacheNames = "MusicCache", key = "#category")
  public List<MusicArticle> getMusic (String category) {
    return crawler.getList(category);
  }

}
