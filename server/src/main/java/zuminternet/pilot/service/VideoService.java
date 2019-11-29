package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.helper.YoutubeSearch;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

  private final VideoRepository videoRepository;
  private final VideoGroupRepository videoGroupRepository;

  @Cacheable(cacheNames = "VideoCache", key="#q")
  public List<Video> getVideo (String q) {
    VideoGroup parent = videoGroupRepository.findBySearchTitle(q);
    List<Video> result;
    if (parent == null) {
      parent = VideoGroup.builder().searchTitle(q).build();
      result = YoutubeSearch.execute(q);
      videoRepository.saveAll(result);
      parent.getVideoList().addAll(result);
      videoGroupRepository.save(parent);
    } else {
      result = parent.getVideoList();
    }
    return result;
  }

}
