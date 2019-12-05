import {
  FETCH_VIDEO,
  SELECT_VIDEO,
  VIEW_VIDEO,
  LIKE_VIDEO,
} from './const';

export default {
  [FETCH_VIDEO]: (state, { result: videoList }) => {
    state.videoList = videoList;
  },
  [SELECT_VIDEO]: (state, payload) => {
    state.selectedVideo = payload;
  },
  [VIEW_VIDEO]: ({ selectedVideo }, viewCount) => {
    Object.assign(selectedVideo, { viewCount });
  },
  [LIKE_VIDEO]: (state) => {
    const { selectedVideo } = state;
    const { userLiked, likeCount } = selectedVideo;
    state.selectedVideo = {
      ...selectedVideo,
      userLiked: !userLiked,
      likeCount: likeCount + (!userLiked ? 1 : -1),
    };
  },
};
