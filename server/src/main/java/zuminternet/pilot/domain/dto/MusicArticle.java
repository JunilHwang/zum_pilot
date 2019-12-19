package zuminternet.pilot.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MusicArticle {
  private String title;
  private String artist;
  private String album;
  private String img;
  private List videoList;
}
