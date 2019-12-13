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
  @Value("${youtube.number_of_return}")
  private long NUMBER_OF_VIDEOS_RETURNED;

  @Value("${youtube.api_key.myself}")
  private String API_KEY; // myself

  private YouTube youtube = null;
  private HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private JsonFactory JSON_FACTORY = new JacksonFactory();

  public List<Video> execute (String q) {
    youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
      public void initialize(HttpRequest request) throws IOException { }
    }).setApplicationName("youtube-cmdline-search-sample").build();
    YouTube.Search.List search;
    List<Video> result = new ArrayList();
    try {
      search = youtube.search().list("snippet");
      search
        .setKey(API_KEY)
        .setQ(q)
        .setType("video")
        .setMaxResults(NUMBER_OF_VIDEOS_RETURNED)
        .setFields("items(id/videoId,snippet(title,thumbnails/default/url))")
        .execute()
        .getItems()
        .forEach(v -> {
          SearchResultSnippet snippet = v.getSnippet();
          result.add(
            Video.builder()
              .title(snippet.getTitle())
              .videoId(v.getId().getVideoId())
              .thumbnail(snippet.getThumbnails().getDefault().getUrl()).build()
          );
        });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
