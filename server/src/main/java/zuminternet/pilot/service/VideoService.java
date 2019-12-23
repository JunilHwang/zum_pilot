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

  /**
   * 검색어(제목+가수) 기반으로 Video 정보를 가져옴
   * @param q : 검색어(제목+가수)
   * @return : Video Entity
   * @throws VideoNotFoundException : 유튜브에서 비디오를 검색하는 과정에 오류가 발생했을 때 예외 처리
   */
  @Cacheable(cacheNames = "VideoCache", key="#q")
  public Video getBySearch (String q) throws VideoNotFoundException {
    // 일단 DB에 video가 있는지 탐색
    Video video = Optional.ofNullable(videoRepository.findBySearchTitle(q)).orElseGet(() -> {
      // DB에 없다면 Youtube Search
      Video v = Optional.ofNullable(youtubeSearch.execute(q)).orElseThrow(VideoNotFoundException::new);
      videoRepository.save(v);
      return v;
    });

    // 탐색해온 Video 정보 반환
    return video;
  }

  /**
   * index key 기반으로 Video Data 가져오기
   * @param idx
   * @return Video Entity
   * @throws VideoNotFoundException : Video Null 예외 처리
   */
  @Cacheable(cacheNames = "VideoCache", key="#idx")
  public Video get (long idx) throws VideoNotFoundException {
    return Optional.ofNullable(videoRepository.findByIdx(idx)).orElseThrow(VideoNotFoundException::new);
  }

  /**
   * 조회수 증가
   * @param idx : 조회수를 증가시킬 Video의 idx 값
   * @throws VideoNotFoundException : Video 탐색에 실패할 경우 예외 처리
   */
  public void videoView (long idx) throws VideoNotFoundException {
    Video video = Optional.ofNullable(get(idx)).orElseThrow(VideoNotFoundException::new);
    video.setViewCount(video.getViewCount() + 1);
    videoRepository.save(video);
    Cache cache = cacheManager.getCache("VideoCache");
    cache.evict(video.getSearchTitle());
    cache.evict(video.getIdx());
  }

  /**
   * video의 좋아요에 대한 정보 반환
   * @param videoIdx : video의 idx값
   * @param userIdx : user의 idx 값
   * @return
   */
  public LikeCount getLikeCount (int videoIdx, int userIdx) {
    return LikeCount.builder()
            .likeCount(likeRepository.countAllByVideoIdx(videoIdx)) // 좋아요 갯수
            .userLiked(likeRepository.findByVideoIdxAndAndUserIdx(videoIdx, userIdx) != null) // 유저가 비디오에 좋아요를 눌렀는지 여부
            .build();
  }

  /**
   * 좋아요 토글
   * @param videoIdx : 토글 시킬 비디오의 idx 값
   * @param userIdx : 좋아요를 누른 user의 idx값
   */
  public void postLike (int videoIdx, int userIdx) {
    // 좋아요 정보를 가져오기
    VideoLike videoLike = likeRepository.findByVideoIdxAndAndUserIdx(videoIdx, userIdx);

    // 좋아요 토글
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

  /**
   * 인기 영상 가져오기
   * @return
   */
  public List<VideoPopular> getPopular () {
    return videoRepository.findAllByPopular() // 인기 영상 목록을 가져온 다음
            .stream() // 스트림으로 변환
            .filter(video -> video.getPopularPoint() > 0) // view count + like*2가 0 이상인 video만 선택
            .sorted((a, b) -> a.getPopularPoint() < b.getPopularPoint() ? 1 : -1) // 내림차순으로 정렬
            .collect(Collectors.toList()); // 다시 list로 만든 후 반환
  }
}
