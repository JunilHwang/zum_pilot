import { VIDEO_FETCH, VIDEO_SELECT, VIDEO_VIEW, VIDEO_LIKE } from '../mutations-type';

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
  [VIDEO_LIKE]: (state) => {
    const { videoList, selectedVideo } = state;
    const { userLiked, likeCount, idx } = selectedVideo;
    selectedVideo.userLiked = !userLiked;
    selectedVideo.likeCount = likeCount + (!userLiked ? 1 : -1);
    state.selectedVideo = { ...selectedVideo };
    const key = videoList.findIndex(v => v.idx === idx);
    videoList[key] = selectedVideo;
  },
};
