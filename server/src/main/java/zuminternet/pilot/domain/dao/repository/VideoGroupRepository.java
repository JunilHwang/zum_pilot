package zuminternet.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuminternet.pilot.domain.entity.VideoGroup;

public interface VideoGroupRepository extends JpaRepository<VideoGroup, Long> {
  VideoGroup findBySearchTitle (String searchTitle);
}
