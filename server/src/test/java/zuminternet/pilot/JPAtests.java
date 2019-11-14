package zuminternet.pilot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoRepository;

@SpringBootTest
public class JPAtests {
  @Autowired
  VideoRepository videoRepository;

  @Autowired
  VideoGroupRepository videoGroupRepository;

  @Test
  public void videoTest () {
    Video video = new Video("title", "video_id", "thumbnail");
    videoRepository.save(video);
    System.out.println(videoRepository.findById(Long.valueOf(1)));
  }
}
