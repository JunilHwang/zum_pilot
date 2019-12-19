package zuminternet.pilot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuminternet.pilot.domain.dto.NewsArticle;
import zuminternet.pilot.domain.response.ListResult;
import zuminternet.pilot.domain.response.SingleResult;
import zuminternet.pilot.service.NewsService;
import zuminternet.pilot.service.ResponseService;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class NewsController {

  private final NewsService newsService;
  private final ResponseService responseService;

  @GetMapping(value="/api/news")
  public ListResult<NewsArticle> getNews (@RequestParam(value = "page", defaultValue = "1") int page) {
    return responseService.listResult(newsService.getList(page));
  }

  @GetMapping(value="/api/news/headline")
  public SingleResult<NewsArticle> getHeadline () {
    return responseService.singleResult(newsService.getHeadline());
  }

  @GetMapping(value="/api/news/popular")
  public ListResult<NewsArticle> getPopular () {
    return responseService.listResult(newsService.getPopular());
  }

  @GetMapping(value="/api/news_content")
  public SingleResult<NewsArticle> getNewsContent (@RequestParam(value="url") String url) {
    return responseService.singleResult(newsService.getContent(url));
  }
}
