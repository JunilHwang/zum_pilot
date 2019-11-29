import {
  FETCH_MUSIC,
  FETCH_VIDEO,
  SELECT_VIDEO,
} from './const';

export default {
  [FETCH_MUSIC]: (state, payload) => {
    state.articles = payload;
  },
  [FETCH_VIDEO]: (state, { selected, result: video }) => {
    Object.assign(state, {
      selected,
      video,
      selectedVideo: video[0],
    });
  },
  [SELECT_VIDEO]: (state, payload) => {
    state.selectedVideo = payload;
  },
};
