package zuminternet.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zuminternet.pilot.entity.VideoLike;

public interface VideoLikeRepository extends JpaRepository<VideoLike, Long> {
  long countAllByVideoIdx(int videoIdx);
  VideoLike findByVideoIdxAndAndUserIdx(int videoIdx, int userIdx);
}
