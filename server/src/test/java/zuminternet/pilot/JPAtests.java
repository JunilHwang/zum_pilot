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
    VideoGroup videoGroup = videoGroupRepository.findBySearchTitle("황준일");
    Video video = new Video("title", "video_id", "thumbnail", videoGroup);
    videoRepository.save(video);
    System.out.println(videoRepository.findById(Long.valueOf(1)));
  }
}
