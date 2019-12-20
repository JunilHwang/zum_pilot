package zuminternet.pilot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zuminternet.pilot.advice.exception.VideoNotFoundException;
import zuminternet.pilot.domain.dao.entity.Video;
import zuminternet.pilot.domain.dao.entity.VideoLike;
import zuminternet.pilot.domain.dto.LikeCount;
import zuminternet.pilot.domain.dto.VideoPopular;
import zuminternet.pilot.helper.YoutubeSearch;
import zuminternet.pilot.domain.dao.repository.VideoLikeRepository;
import zuminternet.pilot.domain.dao.repository.VideoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

  private final VideoRepository videoRepository;
  private final CacheManager cacheManager;
  private final VideoLikeRepository likeRepository;
  private final YoutubeSearch youtubeSearch;

  @Cacheable(cacheNames = "VideoCache", key="#q")
  public Video getBySearch (String q) throws VideoNotFoundException {
    Video video = videoRepository.findBySearchTitle(q);
    if (video == null) {
      video = Optional.ofNullable(youtubeSearch.execute(q)).orElseThrow(VideoNotFoundException::new);
      videoRepository.save(video);
    }
    return video;
  }

  @Cacheable(cacheNames = "VideoCache", key="#idx")
  public Video get (long idx) throws VideoNotFoundException {
    return Optional.ofNullable(videoRepository.findByIdx(idx)).orElseThrow(VideoNotFoundException::new);
  }

  public void videoView (long idx) throws VideoNotFoundException {
    Video video = Optional.ofNullable(get(idx)).orElseThrow(VideoNotFoundException::new);
    video.setViewCount(video.getViewCount() + 1);
    videoRepository.save(video);
    Cache cache = cacheManager.getCache("VideoCache");
    cache.evict(video.getSearchTitle());
    cache.evict(video.getIdx());
  }

  public LikeCount getLikeCount (int videoIdx, int userIdx) {
    return LikeCount.builder()
            .likeCount(likeRepository.countAllByVideoIdx(videoIdx))
            .videoLike(likeRepository.findByVideoIdxAndAndUserIdx(videoIdx, userIdx) != null)
            .build();
  }

  public void postLike (int videoIdx, int userIdx) {
    VideoLike videoLike = likeRepository.findByVideoIdxAndAndUserIdx(videoIdx, userIdx);
    if (videoLike == null) {
      likeRepository.save(
        VideoLike.builder()
          .userIdx(userIdx)
          .videoIdx(videoIdx).build()
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
}
