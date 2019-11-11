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
    send.put("success", true);
    send.put("articles", Crawler.getNewsList(page));
    return send;
  }
  @GetMapping(value="/api/news/headline")
  public HashMap getHeadline () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("article", Crawler.getHeadline());
    return send;
  }
  @GetMapping(value="/api/news/popular")
  public HashMap getPopular () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("articles", Crawler.getPopular());
    return send;
  }
  @GetMapping(value="/api/news_content")
  public HashMap getNewsContent (@RequestParam(value="url") String url) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("article", Crawler.getNewsContent(url));
    return send;
  }
}
