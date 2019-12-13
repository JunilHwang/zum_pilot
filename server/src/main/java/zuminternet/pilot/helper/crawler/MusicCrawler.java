package zuminternet.pilot.helper.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zuminternet.pilot.domain.dto.MusicArticle;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicCrawler extends Crawler {

  @Value("${url.melon}")
  private String melonURL;

  @Value("${uri.melon.genre}")
  private String melonGenreURI;

  public List<MusicArticle> getList (String category) {
    String appendURI = "/chart/";
    switch (category) {
      case "일간" : appendURI += "day/"; break;
      case "발라드" : appendURI = melonGenreURI + "0100";  break;
      case "댄스" : appendURI = melonGenreURI + "0200";  break;
      case "힙합" : appendURI = melonGenreURI + "0300";  break;
      case "R&B/Soul" : appendURI = melonGenreURI + "0400";  break;
      case "POP" : appendURI = melonGenreURI + "0900";  break;
    }
    Document doc = this.getDoc(melonURL + appendURI);
    Elements els = doc.select("tr[class^=\"lst\"]");
    if (els.size() == 0) {
      els = doc.select("tbody tr");
    }
    List<MusicArticle> articles = new ArrayList();
    els.forEach(el -> {
      articles.add(
        MusicArticle.builder()
          .img(el.select("img").eq(0).attr("src"))
          .title(el.select(".ellipsis.rank01 a").text())
          .artist(el.select(".ellipsis.rank02 > a").text())
          .album(el.select(".ellipsis.rank03 a").text()).build()
      );
    });
    return articles;
  }
}
