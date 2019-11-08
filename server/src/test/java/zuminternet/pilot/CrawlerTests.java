package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.domain.NewsArticle;
import zuminternet.pilot.helper.Crawler;

@SpringBootTest
class CrawlerTests {

  @Test
  public void CrawlerNewsList () {
    Crawler crawler = new Crawler();
    NewsArticle[] articles = crawler.getNewsList();
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }

  @Test
  public void CrawlerHeadline () {
    Crawler crawler = new Crawler();
    NewsArticle article = crawler.getHeadline();
    System.out.println(article);
  }

  @Test
  public void CrawlerPopular () {
    Crawler crawler = new Crawler();
    // crawler.getPopular();
    NewsArticle[] articles = crawler.getPopular();
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }
}
