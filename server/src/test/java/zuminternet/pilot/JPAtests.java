package zuminternet.pilot;

import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

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

  @Test
  public void videoGroupTest () {
    String q = "황준일";
    List<Video> videoList = new ArrayList();
    videoList.add(new Video("title", "video_id", "thumbnail"));
    videoList.add(new Video("title", "video_id", "thumbnail"));
    videoList.add(new Video("title", "video_id", "thumbnail"));
    VideoGroup parent = new VideoGroup(q);
    videoGroupRepository.save(parent);
    videoRepository.saveAll(videoList);
    parent.getVideoList().addAll(videoList);
    System.out.println(videoList);
  }
}
