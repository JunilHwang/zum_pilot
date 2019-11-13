import { FETCH_MUSIC } from './const';

export default {
  [FETCH_MUSIC]: (state, { result }) => {
    state.articles = result;
  },
};
