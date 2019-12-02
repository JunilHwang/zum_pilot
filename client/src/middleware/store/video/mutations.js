import { FETCH_VIDEO, SELECT_VIDEO, VIEW_VIDEO } from './const';

export default {
  [FETCH_VIDEO]: (state, { result: videoList }) => {
    Object.assign(state, {
      videoList,
      selectedVideo: videoList[0],
    });
  },
  [SELECT_VIDEO]: (state, payload) => {
    state.selectedVideo = payload;
  },
  [VIEW_VIDEO]: ({ selectedVideo }, viewCount) => {
    Object.assign(selectedVideo, { viewCount });
  },
};
