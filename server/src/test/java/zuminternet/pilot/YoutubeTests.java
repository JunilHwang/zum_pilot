package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.helper.YoutubeSearch;

@SpringBootTest
public class YoutubeTests {

  @Autowired
  private YoutubeSearch youtubeSearch;

  @Test
  public void SearchTest () {
    System.out.println(youtubeSearch.execute("황준일"));
  }
}
