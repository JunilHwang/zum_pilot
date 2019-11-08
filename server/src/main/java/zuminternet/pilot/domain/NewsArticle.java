package zuminternet.pilot.domain;

import lombok.Data;

@Data
public class NewsArticle {
  private String img;
  private String title;
  private String regDate;
  private String link;
  private String content;
}
