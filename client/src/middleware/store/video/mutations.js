import {
  VIDEO_FETCH,
  VIDEO_SELECT,
  VIDEO_VIEW,
  VIDEO_LIKE,
} from '../mutations-type';

export default {
  [VIDEO_FETCH]: (state, videoList) => {
    state.videoList = videoList;
  },
  [VIDEO_SELECT]: (state, payload) => {
    state.selectedVideo = payload;
  },
  [VIDEO_VIEW]: ({ selectedVideo }, viewCount) => {
    Object.assign(selectedVideo, { viewCount });
  },
  [VIDEO_LIKE]: (state, idx) => {
    const { videoList } = state;
    const key = videoList.findIndex(v => v.idx === idx);
    const target = videoList[key];
    const { userLiked, likeCount } = target;
    target.userLiked = !userLiked;
    target.likeCount = likeCount + (!userLiked ? 1 : -1);
    if (target.idx === state.selectedVideo.idx) {
      state.selectedVideo = target;
    }
  },
};
