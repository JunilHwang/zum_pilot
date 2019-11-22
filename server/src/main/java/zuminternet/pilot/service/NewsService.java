package zuminternet.pilot.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.NewsArticle;
import zuminternet.pilot.helper.Crawler;

import java.util.List;

@Service
public class NewsService {

  @Cacheable(cacheNames = "NewsCache", key = "#page")
  public List<NewsArticle> getList (int page) {
    return Crawler.getNewsList(page);
  }

  @Cacheable(cacheNames = "NewsCache", key = "'Headline'")
  public NewsArticle getHeadline () {
    return Crawler.getHeadline();
  }

  @Cacheable(cacheNames = "NewsCache", key = "'Popular'")
  public List<NewsArticle> getPopular () {
    return Crawler.getPopular();
  }

  @Cacheable(cacheNames = "NewsCache", key = "#url")
  public NewsArticle getContent (String url) {
    return Crawler.getNewsContent(url);
  }
}
