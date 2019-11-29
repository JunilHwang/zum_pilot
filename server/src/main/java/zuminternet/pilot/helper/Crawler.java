package zuminternet.pilot.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import zuminternet.pilot.domain.MusicArticle;
import zuminternet.pilot.domain.NewsArticle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
  static private String SBSURL ="http://sbsfune.sbs.co.kr";

  static private Document getDoc (String url) {
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

  static public List<NewsArticle> getNewsList (int page) {
    Document doc = getDoc(SBSURL + "/news/ssports_list.jsp?code_category=SS04&pageNo=" + page);
    Elements els = doc.select(".list_news").select("li");
    List<NewsArticle> articles = new ArrayList();
    els.forEach(el -> {
      NewsArticle article = new NewsArticle();
      article.setImg(el.select("img").attr("data-src"));
      article.setTitle(el.select("a strong").text());
      article.setRegDate(el.select(">span").text());
      article.setLink(SBSURL + el.select(">a").attr("href"));
      articles.add(article);
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
  static public List<NewsArticle> getPopular () {
    String billboardURL = "http://billboard.co.kr";
    Document doc = getDoc(billboardURL + "/main/news/list");
    Elements els = doc.select("#new_area").select("li:nth-child(1), li:nth-child(2), li:nth-child(3), li:nth-child(4)");
    List<NewsArticle> articles = new ArrayList();
    els.forEach(el -> {
      NewsArticle article = new NewsArticle();
      article.setTitle(el.select(".txt span").text());
      article.setImg(billboardURL + el.select("img").attr("src"));
      article.setLink(billboardURL + el.select("a").attr("href"));
      articles.add(article);
    });
    return articles;
  }
  static public NewsArticle getNewsContent (String url) {
    NewsArticle article = new NewsArticle();
    Document doc = getDoc(url);
    Boolean bool = url.indexOf("sbsfune.sbs.co.kr") != -1;
    Elements el = doc.select(bool ? "#etv_news_content" : ".text");
    if (bool) {
      el.select("img").forEach(v -> {
        v.attr("src", v.attr("data-v_src"));
      });
    }
    article.setTitle(doc.select(bool ? "h1" : ".ttl.tac .big").text());
    String cleanContent = Jsoup.clean(el.html(), Whitelist.relaxed());
    article.setContent(cleanContent);
    return article;
  }
  static public List<MusicArticle> getMusicList (String category) {
    String baseURL = "https://www.melon.com";
    String appendURI = "/chart/";
    String genreURI = "/genre/song_list.htm?gnrCode=GN";
    switch (category) {
      case "일간" : appendURI += "day/"; break;
      case "발라드" : appendURI = genreURI + "0100";  break;
      case "댄스" : appendURI = genreURI + "0200";  break;
      case "힙합" : appendURI = genreURI + "0300";  break;
      case "R&B/Soul" : appendURI = genreURI + "0400";  break;
      case "인디" : appendURI = genreURI + "0500";  break;
      case "POP" : appendURI = genreURI + "0900";  break;
    }
    Document doc = getDoc(baseURL + appendURI);
    Elements els = doc.select("tr[class^=\"lst\"]");
    if (els.size() == 0) {
      els = doc.select("tbody tr");
    }
    List<MusicArticle> articles = new ArrayList();
    els.forEach(el -> {
      MusicArticle article = new MusicArticle();
      article.setImg(el.select("img").eq(0).attr("src"));
      article.setTitle(el.select(".ellipsis.rank01 a").text());
      article.setArtist(el.select(".ellipsis.rank02 > a").text());
      article.setAlbum(el.select(".ellipsis.rank03 a").text());
      articles.add(article);
    });
    return articles;
  }
  static public void getYoutube (String url) {
    Document doc = getDoc(url);
    Elements els = doc.select("body script");
    String word = "window[\"ytInitialData\"] = ";
    for (Element el : els) {
      String str = el.toString();
      int index = str.indexOf(word);
      if (index != -1) {
        int start = index + word.length();
        int end = str.indexOf("\n", start);
        System.out.println(str.substring(start, end));
        break;
      }
    }
    System.out.println(els.size());
  }

}
