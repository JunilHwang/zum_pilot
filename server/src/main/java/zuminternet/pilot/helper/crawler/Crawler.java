package zuminternet.pilot.helper.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Crawler {
  /**
   * url에 해당하는 HTML 데이터를 가져옴
   * @param url: 크롤링할 HTML page의 URL
   * @return 크롤링한 TEXT를 DOM 형태로 반환
   */
  protected Document getDoc (String url) {
    Document doc = null;
    try {
      // Window, Chrome의 User Agent.
      String agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";

      // 사이트로 부터 DOM 데이터를 가져온다.
      doc = Jsoup.connect(url).userAgent(agent).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return doc;
  }
}
