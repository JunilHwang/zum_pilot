package zuminternet.pilot.repository;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@ToString
public class Video {
  @Id
  @GeneratedValue
  private int idx;

  @Column private String title;
  @Column private String video_id;
  @Column private String thumbnail;
  @Column private String search_title;

  @Builder
  public Video (String title, String video_id, String thumbnail, String search_title) {
    this.title = title;
    this.video_id = video_id;
    this.thumbnail = thumbnail;
    this.search_title = search_title;
  }
}
