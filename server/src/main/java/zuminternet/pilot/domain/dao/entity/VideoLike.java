package zuminternet.pilot.domain.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="video_like")
public class VideoLike {
  @Id
  @GeneratedValue
  private Long idx;

  @Column(nullable = false, name="video_idx")
  private int videoIdx;

  @Column(nullable = false, name="user_idx")
  private int userIdx;
}
