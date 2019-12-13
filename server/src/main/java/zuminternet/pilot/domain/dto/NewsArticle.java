package zuminternet.pilot.domain.dto;

import lombok.*;

@Data
@Builder
public class NewsArticle {
  private String img;
  private String title;
  private String regDate;
  private String link;
  private String content;
}
