package zuminternet.pilot.helper.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Crawler {
  protected Document getDoc (String url) {
    Document doc = null;
    try {
      doc = Jsoup.connect(url)
                 .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36")
                 .get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return doc;
  }
}
