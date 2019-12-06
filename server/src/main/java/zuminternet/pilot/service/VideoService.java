package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.entity.*;
import zuminternet.pilot.projection.VideoPopular;
import zuminternet.pilot.helper.YoutubeSearch;
import zuminternet.pilot.repository.VideoBookmarkRepository;
import zuminternet.pilot.repository.VideoGroupRepository;
import zuminternet.pilot.repository.VideoLikeRepository;
import zuminternet.pilot.repository.VideoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

  private final VideoRepository videoRepository;
  private final VideoGroupRepository groupRepository;
  private final CacheManager cacheManager;
  private final VideoLikeRepository likeRepository;
  private final VideoBookmarkRepository bookmarkRepository;

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

  public void postLike (int videoIdx, int userIdx) {
    VideoLike videoLike = likeRepository.findByVideoIdxAndAndUserIdx(videoIdx, userIdx);
    boolean result = videoLike == null;
    if (result) {
      likeRepository.save(
        VideoLike
          .builder()
          .userIdx(userIdx)
          .videoIdx(videoIdx)
          .build()
      );
    } else {
      likeRepository.delete(videoLike);
    }
  }

  public List<VideoPopular> getPopular () {
    return videoRepository.findAllByPopular()
            .stream()
            .filter(video -> video.getPopularPoint() > 0)
            .sorted((a, b) -> a.getPopularPoint() < b.getPopularPoint() ? 1 : -1)
            .collect(Collectors.toList());
  }

  public void setBookmark (User user, long videoIdx) {
    bookmarkRepository.save(
      VideoBookmark
        .builder()
        .user(user)
        .video(videoRepository.findByIdx(videoIdx))
        .build()
    );
  }
}
