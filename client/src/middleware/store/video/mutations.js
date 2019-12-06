import {
  VIDEO_FETCH,
  VIDEO_SELECT,
  VIDEO_VIEW,
  VIDEO_LIKE,
} from '../mutations-type';

export default {
  [VIDEO_FETCH]: (state, { result: videoList }) => {
    state.videoList = videoList;
  },
  [VIDEO_SELECT]: (state, payload) => {
    state.selectedVideo = payload;
  },
  [VIDEO_VIEW]: ({ selectedVideo }, viewCount) => {
    Object.assign(selectedVideo, { viewCount });
  },
  [VIDEO_LIKE]: (state) => {
    const { selectedVideo } = state;
    const { userLiked, likeCount } = selectedVideo;
    state.selectedVideo = {
      ...selectedVideo,
      userLiked: !userLiked,
      likeCount: likeCount + (!userLiked ? 1 : -1),
    };
  },
};
