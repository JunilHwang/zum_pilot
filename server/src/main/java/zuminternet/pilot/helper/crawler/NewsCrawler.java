package zuminternet.pilot.helper.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zuminternet.pilot.domain.dto.NewsArticle;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsCrawler extends Crawler { // Crawler Class를 Extends함 --> getDoc Method 사용 가능

  @Value("${url.sbs}")
  private String sbsURL; // application.yml의 url.sbs를 가져옴(SBS K-POP NEWS URL)

  @Value("${url.billboard}")
  private String billboardURL; // applicatino.yml의 url.billboard를 가져옴

  /**
   * SBS K-POP NEWS의 목록을 가져온다.
   * @param page pagination number. 한 페이지에 20개의 기사가 존재한다.
   * @return 크롤링 해온 데이터를 NewsArticle에 Mapping 후 List 형태로 반환
   */
  public List<NewsArticle> getList (int page) {
    // SBS NEWS Page에서 DOM 정보를 가져온다.
    Document doc = getDoc(sbsURL + "/news/ssports_list.jsp?code_category=SS04&pageNo=" + page);

    // 기사 목록들에 대한 태그를 선택
    Elements els = doc.select(".list_news").select("li");

    // mapping
    List<NewsArticle> articles = new ArrayList();
    els.forEach(el -> {
      articles.add(
        NewsArticle.builder()
          .img(el.select("img").attr("data-src"))
          .title(el.select("a strong").text())
          .regDate(el.select(">span").text())
          .link(sbsURL + el.select(">a").attr("href")).build()
      );
    });
    return articles;
  }

  /**
   * SBS NEWS의 Headline 기사를 가져온다.
   * @return NewsArticle에 Mapping 후 반환
   */
  public NewsArticle getHeadline () {
    Elements el = getDoc(sbsURL + "/news").select(".hline_hot");
    return NewsArticle.builder()
            .img(el.select("span img").attr("src"))
            .title(el.select("strong").text())
            .content(el.select(".text_crop3").text())
            .link(el.select("a").attr("href")).build();
  }

  /**
   * Billboard k-pop news의 상단 4개의 기사를 가져온다.
   * @return NewArticle에 mapping후 List 형태로 반환한다.
   */
  public List<NewsArticle> getPopular () {
    // DOM 정보를 가져옴
    Document doc = getDoc(billboardURL + "/main/news/list");

    // 상단 4개의 기사 선택
    Elements els = doc.select("#new_area").select("li:nth-child(1), li:nth-child(2), li:nth-child(3), li:nth-child(4)");

    // 맵핑
    List<NewsArticle> articles = new ArrayList();
    els.forEach(el -> {
      articles.add(
        NewsArticle.builder()
          .title(el.select(".txt span").text())
          .img(billboardURL + el.select("img").attr("src"))
          .link(billboardURL + el.select("a").attr("href"))
          .build()
      );
    });
    return articles;
  }

  /**
   * URL에 해당하는 News Content 정보를 가져온다.
   * @param url : 가져올 Content의 URL
   * @return : SBS NEWS와 Billboard NEWS를 판별하여 필요없는 태그를 지운 후 NewsArticle에 mapping 하여 반환한다.
   */
  public NewsArticle getContent (String url) {
    // DOM 정보를 가져옴
    Document doc = getDoc(url);

    // SBS NEWS -> true, Billboard News -> false
    Boolean bool = url.indexOf(sbsURL) != -1;

    // bool의 값을 통하여 어떤 tag의 내용을 가져올지 선택
    Elements el = doc.select(bool ? "#etv_news_content" : ".text");

    // sbs news의 경우 data-v_src를 src에 맵핑
    // sbs news는 초기 로딩을 빠르게 하기 위하여 이미지의 src를 뒤늦게 load하는 형태
    if (bool) {
      el.select("img").forEach(v -> {
        v.attr("src", v.attr("data-v_src"));
      });
    }

    /*
      참고 : https://offbyone.tistory.com/375
      Contnet의 내용을 Jsoup 에서 제공하는 whitelist 방식으로 tag를 검열한다.
      허용하는 tag는 다음과 같다.
      a, b, blockquote, br, caption, cite, code, col, colgroup, dd, div, dl, dt, em, h1, h2, h3, h4, h5, h6,
      i, img, li, ol, p, pre, q, small, span, strike, strong, sub, sup,
      table, tbody, td, tfoot, th, thead, tr, u, ul
    */
    String cleanContent = Jsoup.clean(el.html(), Whitelist.relaxed());

    // 제목과 컨텐츠만 맵핑하여 반환한다.
    return NewsArticle.builder()
            .title(doc.select(bool ? "h1" : ".ttl.tac .big").text())
            .content(cleanContent).build();
  }

}
