package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.helper.YoutubeSearch;

@SpringBootTest
public class YoutubeTests {
  @Test
  public void SearchTest () {
    YoutubeSearch youtubeSearch = new YoutubeSearch();
    System.out.println(youtubeSearch.search("황준일"));
  }
}
