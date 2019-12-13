package zuminternet.pilot.domain.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuminternet.pilot.domain.dao.entity.VideoGroup;

public interface VideoGroupRepository extends JpaRepository<VideoGroup, Long> {
  VideoGroup findBySearchTitle (String searchTitle);
}
