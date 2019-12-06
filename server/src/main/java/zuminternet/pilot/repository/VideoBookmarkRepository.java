package zuminternet.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoBookmark;
import zuminternet.pilot.projection.VideoPopular;

import java.util.List;

public interface VideoBookmarkRepository extends JpaRepository<VideoBookmark, Long> {

}
