package zuminternet.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuminternet.pilot.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> { }
