package zuminternet.pilot.helper;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResultSnippet;
import zuminternet.pilot.entity.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YoutubeSearch {
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
  private static YouTube youtube = null;
  private static final String API_KEY1 = "AIzaSyB9-VhCcc2neB2aYgVxJPZLtkb_jmfNo1o"; // zum_pilot
  private static final String API_KEY2 = "AIzaSyAivIxmsxd4b1FA_C67gYKph56F4RseQeY"; // myself

  static public List<Video> execute (String q) {
    if (youtube == null) {
      youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
        public void initialize(HttpRequest request) throws IOException { }
      }).setApplicationName("youtube-cmdline-search-sample").build();
    }
    YouTube.Search.List search;
    List<Video> result = new ArrayList();
    try {
      search = youtube.search().list("snippet");
      search
        .setKey(API_KEY2)
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
              .thumbnail(snippet.getThumbnails().getDefault().getUrl())
              .build()
          );
        });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
