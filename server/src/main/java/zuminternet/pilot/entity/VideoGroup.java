package zuminternet.pilot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@ToString
@Table(name="video_group")
public class VideoGroup {
  @Id
  @GeneratedValue
  private Long idx;

  @Column(nullable = false, name = "search_title") private String searchTitle;

  @OneToMany
  @JoinColumn(name = "gid")
  private List<Video> VideoList = new ArrayList<Video>();

  @Builder
  public VideoGroup(String searchTitle) {
    this.searchTitle = searchTitle;
  }
}
