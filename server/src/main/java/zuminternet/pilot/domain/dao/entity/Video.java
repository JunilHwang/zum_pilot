package zuminternet.pilot.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

  @Column(nullable = false, name = "view_count")
  @ColumnDefault("0")
  @Setter
  private int viewCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="group_idx")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Setter
  private VideoGroup videoGroup;

  @Builder
  public Video (String title, String videoId, String thumbnail) {
    this.title = title;
    this.videoId = videoId;
    this.thumbnail = thumbnail;
  }
}