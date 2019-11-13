package zuminternet.pilot.helper;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class YoutubeSearch {
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
  private static YouTube youtube;
  private static final String API_KEY = "AIzaSyB9-VhCcc2neB2aYgVxJPZLtkb_jmfNo1o";

  public List search (String q) {
    youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
      public void initialize(HttpRequest request) throws IOException {}
    }).setApplicationName("youtube-cmdline-search-sample").build();
    YouTube.Search.List search;
    SearchListResponse searchResponse;
    List result = null;
    try {
      BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
      search = youtube.search().list("snippet");
      search.setKey(API_KEY);
      search.setQ(q);
      search.setType("video");
      search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
      searchResponse = search.execute();
      result = searchResponse.getItems();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
