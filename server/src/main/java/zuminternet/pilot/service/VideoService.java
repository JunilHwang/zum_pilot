package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.entity.Video;
import zuminternet.pilot.entity.VideoGroup;
import zuminternet.pilot.entity.VideoLike;
import zuminternet.pilot.helper.YoutubeSearch;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoLikeRepository;
import zuminternet.pilot.repository.VideoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

  private final VideoRepository videoRepository;
  private final VideoGroupRepository groupRepository;
  private final CacheManager cacheManager;
  private final VideoLikeRepository likeRepository;

  @Cacheable(cacheNames = "VideoCache", key="#q")
  public List<Video> getVideo (String q) {
    VideoGroup parent = groupRepository.findBySearchTitle(q);
    List<Video> result;
    if (parent == null) {
      VideoGroup finalParent = VideoGroup.builder().searchTitle(q).build();
      result = YoutubeSearch.execute(q);
      groupRepository.save(finalParent);
      result.forEach(video -> {
        video.setVideoGroup(finalParent);
      });
      videoRepository.saveAll(result);
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

  public HashMap getLike (int videoIdx, int userIdx) {
    long likeCount = likeRepository.countAllByVideoIdx(videoIdx);
    VideoLike videoLike = likeRepository.findByVideoIdxAndAndUserIdx(videoIdx, userIdx);
    HashMap result = new HashMap();
    result.put("likeCount", likeCount);
    result.put("userLiked", videoLike != null);
    return result;
  }
}
