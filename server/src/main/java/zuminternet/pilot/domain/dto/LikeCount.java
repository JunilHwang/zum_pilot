package zuminternet.pilot.domain.dto;

import lombok.*;

@Data
@Builder
public class LikeCount {
  private long likeCount;
  private boolean videoLike;
}
