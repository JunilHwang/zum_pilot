package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.domain.dto.MusicArticle;
import zuminternet.pilot.domain.dto.NewsArticle;
import zuminternet.pilot.helper.crawler.MusicCrawler;
import zuminternet.pilot.helper.crawler.NewsCrawler;

import java.util.List;

@SpringBootTest
class CrawlerTests {

  @Autowired
  private NewsCrawler newsCrawler;

  @Autowired
  private MusicCrawler musicCrawler;

  @Test
  public void NewsList () {
    List<NewsArticle> articles = newsCrawler.getList(1);
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }

  @Test
  public void Headline () {
    NewsArticle article = newsCrawler.getHeadline();
    System.out.println(article);
  }

  @Test
  public void Popular () {
    List<NewsArticle> articles = newsCrawler.getPopular();
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }

  @Test
  public void ContentOfSBS () {
    NewsArticle article = newsCrawler.getContent("http://sbsfune.sbs.co.kr/news/news_content.jsp?article_id=E10009711713");
    System.out.println(article);
  }

  @Test
  public void ContentOfBillboard () {
    NewsArticle article = newsCrawler.getContent("http://billboard.co.kr/main/news/view/296");
    System.out.println(article);
  }

  @Test
  public void MusicChart () {
    List<MusicArticle> articles = musicCrawler.getList("realtime");
    for (MusicArticle v : articles) {
      System.out.println(v);
    }
  }
}
