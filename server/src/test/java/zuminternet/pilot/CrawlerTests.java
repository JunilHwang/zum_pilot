package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.domain.NewsArticle;
import zuminternet.pilot.helper.Crawler;

@SpringBootTest
class CrawlerTests {

  @Test
  public void CrawlerNewsList () {
    NewsArticle[] articles = Crawler.getNewsList();
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }

  @Test
  public void CrawlerHeadline () {
    NewsArticle article = Crawler.getHeadline();
    System.out.println(article);
  }

  @Test
  public void CrawlerPopular () {
    NewsArticle[] articles = Crawler.getPopular();
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }
}
