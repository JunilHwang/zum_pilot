package zuminternet.pilot.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zuminternet.pilot.domain.MusicArticle;
import zuminternet.pilot.domain.NewsArticle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsCrawler extends Crawler {

  @Value("${url.sbs}")
  private String sbsURL;

  @Value("${url.billboard}")
  private String billboardURL;

  public List<NewsArticle> getNewsList (int page) {
    Document doc = getDoc(sbsURL + "/news/ssports_list.jsp?code_category=SS04&pageNo=" + page);
    Elements els = doc.select(".list_news").select("li");
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

  public NewsArticle getHeadline () {
    Elements el = getDoc(sbsURL + "/news").select(".hline_hot");
    return NewsArticle.builder()
            .img(el.select("span img").attr("src"))
            .title(el.select("strong").text())
            .content(el.select(".text_crop3").text())
            .link(el.select("a").attr("href")).build();
  }

  public List<NewsArticle> getPopular () {
    Document doc = getDoc(billboardURL + "/main/news/list");
    Elements els = doc.select("#new_area").select("li:nth-child(1), li:nth-child(2), li:nth-child(3), li:nth-child(4)");
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

  public NewsArticle getNewsContent (String url) {
    Document doc = getDoc(url);
    Boolean bool = url.indexOf(sbsURL) != -1;
    Elements el = doc.select(bool ? "#etv_news_content" : ".text");
    if (bool) {
      el.select("img").forEach(v -> {
        v.attr("src", v.attr("data-v_src"));
      });
    }
    String cleanContent = Jsoup.clean(el.html(), Whitelist.relaxed());
    return NewsArticle.builder()
            .title(doc.select(bool ? "h1" : ".ttl.tac .big").text())
            .content(cleanContent).build();
  }

}
