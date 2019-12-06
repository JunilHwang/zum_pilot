package zuminternet.pilot.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@ToString
@Table(name = "video_bookmark")
public class VideoBookmark {
  @Id
  @GeneratedValue
  private Long idx;

  @OneToOne
  @JoinColumn(name="idx")
  private User user;

  @OneToOne
  @JoinColumn(name="idx")
  private Video video;

  @Builder
  public VideoBookmark (User user, Video video) {
    this.video = video;
    this.user = user;
  }
}
