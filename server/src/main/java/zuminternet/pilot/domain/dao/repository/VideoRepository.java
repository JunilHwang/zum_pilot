package zuminternet.pilot.domain.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zuminternet.pilot.domain.dto.VideoPopular;
import zuminternet.pilot.domain.dao.entity.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
  Video findByIdx(Long idx);
  Video findBySearchTitle(String searchTitle);

  @Query(
    value = "SELECT v.*, count(vl.USER_IDX) as like_count " +
            "FROM video v left " +
            "JOIN video_like vl ON v.idx = vl.video_idx " +
            "group by v.idx",
    nativeQuery = true
  )
  List<VideoPopular> findAllByPopular();
}
