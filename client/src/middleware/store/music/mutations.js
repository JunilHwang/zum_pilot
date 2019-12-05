import { FETCH_MUSIC, SELECT_MUSIC } from './const';

export default {
  [FETCH_MUSIC]: (state, payload) => {
    state.articles = payload;
  },
  [SELECT_MUSIC]: (state, payload) => {
    state.selectedMusic = payload;
  },
};
