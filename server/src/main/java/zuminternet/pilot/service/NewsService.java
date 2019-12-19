package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.dto.NewsArticle;
import zuminternet.pilot.helper.crawler.NewsCrawler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

  private final NewsCrawler crawler;

  @Cacheable(cacheNames = "NewsCache", key = "#page")
  public List<NewsArticle> getList (int page) {
    return crawler.getList(page);
  }

  @Cacheable(cacheNames = "NewsCache", key = "'Headline'")
  public NewsArticle getHeadline () {
    return crawler.getHeadline();
  }

  @Cacheable(cacheNames = "NewsCache", key = "'Popular'")
  public List<NewsArticle> getPopular () {
    return crawler.getPopular();
  }

  @Cacheable(cacheNames = "NewsCache", key = "#url")
  public NewsArticle getContent (String url) {
    return crawler.getContent(url);
  }
}
