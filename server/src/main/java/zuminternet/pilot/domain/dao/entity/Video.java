package zuminternet.pilot.domain.dao.entity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

  @Column(nullable = false, name = "search_title")
  private String searchTitle;

  @Column(nullable = false, name = "view_count")
  @ColumnDefault("0")
  @Setter
  private int viewCount;
}
