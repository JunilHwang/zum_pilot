import {
  CHANGE_TRANSITION,
  FETCH_HEADLINE,
  FETCH_POPULAR,
  FETCH_ARTICLES,
  APPEND_ARTICLES,
  FETCH_CONTENT,
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
  [APPEND_ARTICLES]: (state, { articles }) => {
    state.newsArticles.push(...articles);
  },
  [FETCH_CONTENT]: (state, payload) => {
    state.newsArticle = { ...payload };
  },
};

export default mutations;
