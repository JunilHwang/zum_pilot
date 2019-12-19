package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.domain.dto.NewsArticle;
import zuminternet.pilot.helper.crawler.NewsCrawler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

  private final NewsCrawler crawler;

  /**
   * http://sbsfune.sbs.co.kr/news/ssports_list.jsp?code_category=SS04&pageNo={#page}
   * 위의 주소에 있는 내용을 긁어옴
   * @param page : pagination number
   * @return 맵핑된 뉴스 아티클 목록
   */
  @Cacheable(cacheNames = "NewsCache", key = "#page")
  public List<NewsArticle> getList (int page) {
    return crawler.getList(page);
  }

  /**
   * http://sbsfune.sbs.co.kr/ 의 headline 기사를 가져옴
   * @return 맵핀된 뉴스 아티클
   */
  @Cacheable(cacheNames = "NewsCache", key = "'Headline'")
  public NewsArticle getHeadline () {
    return crawler.getHeadline();
  }

  /**
   * http://billboard.co.kr/main/news/list
   * 상위 4개의 기사를 가져옴
   * @return 맵핑된 뉴스 아티클 목록
   */
  @Cacheable(cacheNames = "NewsCache", key = "'Popular'")
  public List<NewsArticle> getPopular () {
    return crawler.getPopular();
  }

  /**
   * News의 컨텐츠를 가져옴
   * @param url : 가져올 컨텐츠의 URL
   * @return { title, content } 만 존재하는 뉴스 아티클
   */
  @Cacheable(cacheNames = "NewsCache", key = "#url")
  public NewsArticle getContent (String url) {
    return crawler.getContent(url);
  }
}
