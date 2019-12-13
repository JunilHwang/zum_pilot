package zuminternet.pilot.projection;

import org.springframework.beans.factory.annotation.Value;

public interface VideoPopular {
  Long getIdx();
  String getThumbnail();
  String getTitle();
  @Value("#{target.video_id}")
  String getVideoId();
  @Value("#{target.view_count}")
  int getViewCount();
  @Value("#{target.like_count}")
  int getLikeCount();
  @Value("#{target.view_count + target.like_count * 2}")
  int getPopularPoint();
}
