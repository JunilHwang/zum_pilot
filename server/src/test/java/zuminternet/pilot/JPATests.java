package zuminternet.pilot;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zuminternet.pilot.entity.User;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.entity.VideoLike;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoLikeRepository;
import zuminternet.pilot.repository.VideoRepository;
import zuminternet.pilot.service.VideoService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JPATests {

  @Autowired VideoRepository videoRepository;
  @Autowired VideoGroupRepository videoGroupRepository;
  @Autowired VideoLikeRepository videoLikeRepository;
  @Autowired VideoService videoService;

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

  @Test
  public void videoBuild () {
    Video video = Video.builder()
                       .videoId("videoId")
                       .thumbnail("thumbnail")
                       .title("title")
                       .build();
    System.out.println(video);
  }

  @Test
  public void videoLikeBuild () {
    VideoLike like = VideoLike.builder().videoIdx(1).userIdx(1).build();
    System.out.println(like);
  }

  @Test
  public void userBuild () {
    User user = User.builder().id("test").pw("test").name("test").build();
    System.out.println(user);
  }

  @Test
  public void countLike () {
    long cnt1 = videoLikeRepository.countAllByVideoIdx(1057);
    System.out.println(cnt1);
  }

  @Test
  public void getPopularVideo () {
    List<Video> result = videoRepository.findAll();
    System.out.println(result);
    System.out.println(result.size());
  }
}
