import {
  CHANGE_TRANSITION,
  FETCH_HEADLINE,
  FETCH_POPULAR,
  FETCH_ARTICLES,
} from './const';

const mutations = {
  [CHANGE_TRANSITION]: (state, payload) => {
    state.routerTransition = payload;
  },
  [FETCH_HEADLINE]: (state, { article }) => {
    state.newsHeadline = article;
  },
  [FETCH_POPULAR]: (state, { articles }) => {
    state.newsPopulars = articles;
  },
  [FETCH_ARTICLES]: (state, { articles }) => {
    state.newsArticles = articles;
  },
};

export default mutations;
