package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.service.NewsService;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class NewsController {

  private final NewsService newsService;

  @GetMapping(value="/api/news")
  public HashMap getNews (@RequestParam(value = "page", defaultValue = "1") int page) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", newsService.getList(page));
    return send;
  }

  @GetMapping(value="/api/news/headline")
  public HashMap getHeadline () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", newsService.getHeadline());
    return send;
  }

  @GetMapping(value="/api/news/popular")
  public HashMap getPopular () {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", newsService.getPopular());
    return send;
  }

  @GetMapping(value="/api/news_content")
  public HashMap getNewsContent (@RequestParam(value="url") String url) {
    HashMap send = new HashMap();
    send.put("success", true);
    send.put("result", newsService.getContent(url));
    return send;
  }
}
