import { FETCH_MUSIC } from './const';

export default {
  [FETCH_MUSIC]: (state, { articles }) => {
    state.articles = articles;
  },
};
