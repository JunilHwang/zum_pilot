package zuminternet.pilot.domain;

import lombok.Data;

import java.util.List;

@Data
public class MusicArticle {
  private String title;
  private String artist;
  private String album;
  private String img;
  private List videoList;
}
