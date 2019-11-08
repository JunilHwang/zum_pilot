package zuminternet.pilot.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class NewsArticle {
  private String img;
  private String title;
  private String regDate;
  private String link;
  private String content;
}
