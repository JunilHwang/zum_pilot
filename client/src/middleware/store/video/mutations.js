import { VIDEO_FETCH, VIDEO_SELECT, VIDEO_VIEW, VIDEO_LIKE, VIDEO_LOADING } from '../mutations-type';

export default {
  [VIDEO_FETCH]: (state, videoList) => { state.videoList = videoList; },
  [VIDEO_SELECT]: (state, payload) => { state.selectedVideo = payload; },
  [VIDEO_VIEW]: (state, viewCount) => {
    const { selectedVideo } = state;
    if (selectedVideo.popularPoint !== undefined) {
      selectedVideo.popularPoint = (selectedVideo.likeCount * 2) + viewCount;
    }
    selectedVideo.viewCount = viewCount;
  },
  [VIDEO_LIKE]: (state) => {
    const { selectedVideo } = state;
    const { userLiked, likeCount } = selectedVideo;
    selectedVideo.userLiked = !userLiked;
    selectedVideo.likeCount = likeCount + (!userLiked ? 1 : -1);
    if (selectedVideo.popularPoint !== undefined) {
      const { likeCount: l, viewCount: v } = selectedVideo;
      selectedVideo.popularPoint = (l * 2) + v;
    }
    // state.selectedVideo = { ...selectedVideo };
    // const key = videoList.findIndex(v => v.idx === idx);
    // videoList[key] = selectedVideo;
  },
  [VIDEO_LOADING]: (state, payload) => { state.loaded = payload; },
};
