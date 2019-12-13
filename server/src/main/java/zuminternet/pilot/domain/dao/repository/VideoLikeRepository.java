package zuminternet.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuminternet.pilot.domain.entity.VideoLike;

public interface VideoLikeRepository extends JpaRepository<VideoLike, Long> {
  long countAllByVideoIdx(int videoIdx);
  VideoLike findByVideoIdxAndAndUserIdx(int videoIdx, int userIdx);
}
