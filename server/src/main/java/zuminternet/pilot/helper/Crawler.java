package zuminternet.pilot.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import zuminternet.pilot.domain.NewsArticle;

import java.io.IOException;

public class Crawler {
  static private String SBSURL ="http://sbsfune.sbs.co.kr/";

  static private Document getDoc (String url) {
    Document doc = null;
    String connURI = url;
    try {
      doc = Jsoup.connect(connURI).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return doc;
  }
  static public NewsArticle[] getNewsList () {
    Document doc = getDoc(SBSURL + "/news/ssports_list.jsp?code_category=SS04");
    Elements els = doc.select(".list_news").select("li");
    NewsArticle[] articles = new NewsArticle[els.size()];
    els.forEach(el -> {
      NewsArticle article = new NewsArticle();
      article.setImg(el.select("img").attr("data-src"));
      article.setTitle(el.select("a strong").text());
      article.setRegDate(el.select(">span").text());
      article.setLink(SBSURL + el.select(">a").attr("href"));
      articles[el.elementSiblingIndex()] = article;
    });
    return articles;
  }
  static public NewsArticle getHeadline () {
    Document doc = getDoc(SBSURL + "/news");
    NewsArticle article = new NewsArticle();
    Elements el = doc.select(".hline_hot");
    article.setImg(el.select("span img").attr("src"));
    article.setTitle(el.select("strong").text());
    article.setContent(el.select(".text_crop3").text());
    article.setLink(el.select("a").attr("href"));
    return article;
  }
  static public NewsArticle[] getPopular () {
    String billboardURL = "http://billboard.co.kr";
    Document doc = getDoc(billboardURL + "/main/news/list");
    Elements els = doc.select("#new_area").select("li:nth-child(1), li:nth-child(2), li:nth-child(3), li:nth-child(4)");
    NewsArticle[] articles = new NewsArticle[els.size()];
    els.forEach(el -> {
      NewsArticle article = new NewsArticle();
      article.setTitle(el.select(".txt span").text());
      article.setImg(billboardURL + el.select("img").attr("src"));
      article.setLink(billboardURL + el.select("a").attr("href"));
      articles[el.elementSiblingIndex()] = article;
    });
    return articles;
  }
}
