package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.domain.MusicArticle;
import zuminternet.pilot.domain.NewsArticle;
import zuminternet.pilot.helper.Crawler;

import java.util.List;

@SpringBootTest
class CrawlerTests {

  @Test
  public void NewsList () {
    List<NewsArticle> articles = Crawler.getNewsList(1);
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }

  @Test
  public void Headline () {
    NewsArticle article = Crawler.getHeadline();
    System.out.println(article);
  }

  @Test
  public void Popular () {
    List<NewsArticle> articles = Crawler.getPopular();
    for (NewsArticle v : articles) {
      System.out.println(v);
    }
  }

  @Test
  public void ContentOfSBS () {
    NewsArticle article = Crawler.getNewsContent("http://sbsfune.sbs.co.kr/news/news_content.jsp?article_id=E10009711713");
    System.out.println(article);
  }

  @Test
  public void ContentOfBillboard () {
    NewsArticle article = Crawler.getNewsContent("http://billboard.co.kr/main/news/view/296");
    System.out.println(article);
  }

  @Test
  public void MusicChart () {
    List<MusicArticle> articles = Crawler.getMusicList();
    for (MusicArticle v : articles) {
      System.out.println(v);
    }
  }

  @Test
  public void Youtube () {
    Crawler.getYoutube("https://www.youtube.com/results?search_query=%ED%99%A9%EC%A4%80%EC%9D%BC&sp=EgIQAQ%253D%253D");
  }
}
