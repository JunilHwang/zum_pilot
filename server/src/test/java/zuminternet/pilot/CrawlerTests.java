package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.domain.NewsArticle;
import zuminternet.pilot.helper.Crawler;

@SpringBootTest
class CrawlerTests {

  @Test
  public void CrawlerNewsList () {
    NewsArticle[] articles = Crawler.getNewsList(1);
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

  @Test
  public void CrawlerContentOfSBS () {
    String content = Crawler.getNewsContent("http://sbsfune.sbs.co.kr/news/news_content.jsp?article_id=E10009711713");
    System.out.println(content);
  }

  @Test
  public void CrawlerContentOfBillboard () {
    String content = Crawler.getNewsContent("http://billboard.co.kr/main/news/view/296");
    System.out.println(content);
  }
}
