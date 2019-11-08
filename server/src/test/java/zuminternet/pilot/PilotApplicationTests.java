package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.helper.Crawler;

@SpringBootTest
class PilotApplicationTests {
  @Test
  void contextLoads() {
    System.out.println("this is contextLoads");
  }

  @Test
  public void CrawlerTest () {
    Crawler crawler = new Crawler();
    crawler.getNewsList();
  }
}
