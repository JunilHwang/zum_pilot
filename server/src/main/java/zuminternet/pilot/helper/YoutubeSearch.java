package zuminternet.pilot.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class YoutubeSearch {
  public String search(String q) {
    String result = "";
    try {
      String apiurl = "https://www.googleapis.com/youtube/v3/search"
        + "?key=AIzaSyB9-VhCcc2neB2aYgVxJPZLtkb_jmfNo1o"
        + "&part=snippet&q=" + URLEncoder.encode(q, "UTF-8");
      URL url = new URL(apiurl);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");

      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        response.append(inputLine);
      }
      br.close();
      result = response.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
