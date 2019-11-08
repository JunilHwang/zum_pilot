package zuminternet.pilot.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import zuminternet.pilot.domain.NewsArticle;

import java.io.IOException;
import java.util.HashMap;

public class Crawler {
  public HashMap getNewsList () {
    HashMap result = new HashMap();
    try {
      String siteURL ="http://sbsfune.sbs.co.kr/";
      String connURI = siteURL + "/news/ssports_list.jsp?code_category=SS04";
      Document doc = Jsoup.connect(connURI).get();
      Elements els = doc.select(".list_news").select("li");
      NewsArticle[] articles = new NewsArticle[els.size()];
      els.forEach(el -> {
        NewsArticle article = new NewsArticle();
        article.setImg(el.select("img").attr("data-src"));
        article.setTitle(el.select("a strong").text());
        article.setRegDate(el.select(">span").text());
        article.setLink(siteURL + el.select(">a").attr("href"));
        articles[el.elementSiblingIndex()] = article;
      });
      result.put("success", true);
      result.put("articles", articles);
    } catch (IOException e) {
      result.put("success", false);
      result.put("error", e.toString());
    }
    return result;
  }
}
