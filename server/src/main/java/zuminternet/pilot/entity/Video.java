package zuminternet.pilot.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@ToString
@Table(name = "video")
public class Video {
  @Id
  @GeneratedValue
  private Long idx;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, name = "video_id")
  private String videoId;

  @Column(nullable = false)
  private String thumbnail;

//  @Column(nullable = false, name = "search_title")
//  private String searchTitle;

  @ManyToOne
  @JoinColumn(name = "search_title")
  private VideoGroup videoGroup;

  @Builder
  public Video (String title, String videoId, String thumbnail, VideoGroup videoGroup) {
    this.title = title;
    this.videoId = videoId;
    this.thumbnail = thumbnail;
    this.videoGroup = videoGroup;
  }
}