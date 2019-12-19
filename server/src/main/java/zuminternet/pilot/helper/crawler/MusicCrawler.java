package zuminternet.pilot.helper.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zuminternet.pilot.domain.dto.MusicArticle;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicCrawler extends Crawler { // Crawler Class를 Extends함 --> getDoc Method 사용 가능

  @Value("${url.melon}")
  private String melonURL; // application.yml에 정의한 url.melon

  @Value("${uri.melon.genre}")
  private String melonGenreURI; // application.yml에 정의한 uri.melon.genre

  /**
   * 멜론에서 음원 차트 데이터를 가져옴
   * @param category: 실시간|일간|발라드|댄스|히합|R&B/Soule|POP 등의 카테고리 데이터
   * @return 음원 차트를 Music Article에 mapping해서 List로 반환
   */
  public List<MusicArticle> getList (String category) {
    // 기본으로 사용할 URI 정보
    String appendURI = "/chart/";

    // Category에 따라서 URI를 선택
    switch (category) {
      case "일간" : appendURI += "day/"; break;
      case "발라드" : appendURI = melonGenreURI + "0100";  break;
      case "댄스" : appendURI = melonGenreURI + "0200";  break;
      case "힙합" : appendURI = melonGenreURI + "0300";  break;
      case "R&B/Soul" : appendURI = melonGenreURI + "0400";  break;
      case "POP" : appendURI = melonGenreURI + "0900";  break;
    }

    // DOM Data를 가져온 후
    Document doc = getDoc(melonURL + appendURI);

    /*
      DOM에서 음원 목록에 해당하는 부분을 선택한다.
      lst class는 실시간 top 100에만 존재한다.
      lst class가 없다면 그냥 tbody의 tr을 가져오면 된다.
     */
    Elements els = doc.select("tr[class^=\"lst\"]");
    if (els.size() == 0) {
      els = doc.select("tbody tr");
    }

    // 크롤링한 데이터를 MusicArticle에 Mapping 후 List 형태로 반환한다.
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
