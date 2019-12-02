package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.helper.YoutubeSearch;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

  private final VideoRepository videoRepository;
  private final VideoGroupRepository videoGroupRepository;
  private final CacheManager cacheManager;

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

  public boolean videoView (long idx) {
    Video video = videoRepository.findByIdx(idx);
    boolean check = video != null;
    if (check) {
      video.setViewCount(video.getViewCount() + 1);
      videoRepository.save(video);
      String q = video.getVideoGroup().getSearchTitle();
      Cache cache = cacheManager.getCache("VideoCache");
      cache.evict(q);
    }
    return check;
  }
}
