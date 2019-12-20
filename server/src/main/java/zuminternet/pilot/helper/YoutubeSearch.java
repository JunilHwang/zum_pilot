package zuminternet.pilot.helper;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResultSnippet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zuminternet.pilot.domain.dao.entity.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class YoutubeSearch {

  // 검색을 통해 가져올 Video Data의 갯수
  @Value("${youtube.number_of_return}")
  private long NUMBER_OF_VIDEOS_RETURNED;

  // 유튜브 검색에 사용 될 API KEY.
  // application.yml에 정의되어 있음
  @Value("${youtube.api_key.myself}")
  private String API_KEY;

  // { NetHttpTransprot, JacksonFactory } --> 구글에서 제공하는 Client API
  // NetHttpTransport: java.net 패키지 기반의 Thread-safe http low-level Transport
  // JacksonFactory: Jackson2 기반의 low-level JSON library
  private HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private JsonFactory JSON_FACTORY = new JacksonFactory();

  /**
   * Youtube Search 후 결과 값을 Video Entity로 반환한다.
   * @param q: Youtube Search 검색어. 제목+가수 형태의 값이 넘어온다.
   * @return 검색 결과를 Video에 맵핑하여 반환
   */
  public Video execute (String q) {
    /*
     API Reference: https://developers.google.com/youtube/v3/docs/search/list?hl=ko
     Sample Source: https://developers.google.com/youtube/v3/code_samples/java?hl=ko#search_by_keyword
     YouTube Instance: Youtube Search API 뿐만 아니라 모든 Youtube API 요청을 수행하는 데 사용
     HttpRequestInitializer: 현재 코드에서 필요 없으나 필수 매개변수임
    */
    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
      public void initialize(HttpRequest request) throws IOException { }
    }).setApplicationName("youtube-cmdline-search-sample").build();

    List<Video> result = new ArrayList();
    try {
      // 검색의 part값을 설정해야 한다. snippet 혹은 id 둘 중 하나만 사용 가능
      YouTube.Search.List search = youtube.search().list("snippet");

      search
        // 검색에 사용할 API KEY
        .setKey(API_KEY)

        // 검색어. 제목+가수 형태의 문자열을 넘김
        .setQ(q)

        // 기본값: chnnel,playlist,video. 현재 필요한 것은 video
        .setType("video")

        // 검색 결과 갯수. 기본값: 5, 허용값: 0 ~ 50
        .setMaxResults(NUMBER_OF_VIDEOS_RETURNED)

        // 결과로 가져올 필드
        .setFields("items(id/videoId,snippet(title,thumbnails/default/url))")

        // Search 실행 후 결과를 Video List에 Mapping
        .execute()
        .getItems()

        // 한 개의 비디오에 대해 실행된다.
        .forEach(v -> {
          SearchResultSnippet snippet = v.getSnippet();
          result.add(
            Video.builder()
              .title(snippet.getTitle())
              .videoId(v.getId().getVideoId())
              .thumbnail(snippet.getThumbnails().getDefault().getUrl())
              .build()
          );
        });

      // 맵핑된 비디오를 반환
      return result.get(0);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
