package zuminternet.pilot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.domain.NewsArticle;
import zuminternet.pilot.helper.Crawler;

import java.util.HashMap;

@RestController
public class NewsController {
  @GetMapping(value="/api/news")
  public HashMap getNews (@RequestParam(value = "page", defaultValue = "1") int page) {
    HashMap send = new HashMap();
    NewsArticle[] articles = Crawler.getNewsList(page);
    send.put("success", true);
    send.put("articles", articles);
    return send;
  }
  @GetMapping(value="/api/news/headline")
  public HashMap getHeadline () {
    HashMap send = new HashMap();
    NewsArticle article = Crawler.getHeadline();
    send.put("success", true);
    send.put("article", article);
    return send;
  }
  @GetMapping(value="/api/news/popular")
  public HashMap getPopular () {
    HashMap send = new HashMap();
    NewsArticle[] articles = Crawler.getPopular();
    send.put("success", true);
    send.put("articles", articles);
    return send;
  }
  @GetMapping(value="/api/news_content")
  public HashMap getNewsContent (@RequestParam(value="url") String url) {
    HashMap send = new HashMap();
    NewsArticle article = Crawler.getNewsContent(url);
    send.put("success", true);
    send.put("article", article);
    return send;
  }
}
