import { FETCH_MUSIC, FETCH_VIDEO, SELECT_VIDEO } from './const';

export default {
  [FETCH_MUSIC]: (state, { result }) => {
    state.articles = result;
  },
  [FETCH_VIDEO]: (state, { selected, result }) => {
    state.selected = selected;
    state.video = result;
  },
  [SELECT_VIDEO]: (state, payload) => {
    state.selectedVideo = payload;
  },
};
