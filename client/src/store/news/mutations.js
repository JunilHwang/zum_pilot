import {
  FETCH_HEADLINE,
  FETCH_POPULAR,
  FETCH_ARTICLES,
  APPEND_ARTICLES,
  FETCH_CONTENT,
} from './const';

export default {
  [FETCH_HEADLINE]: (state, { article }) => {
    state.headline = article;
  },
  [FETCH_POPULAR]: (state, { articles }) => {
    state.populars = articles;
  },
  [FETCH_ARTICLES]: (state, { articles }) => {
    state.articles = articles;
  },
  [APPEND_ARTICLES]: (state, { articles }) => {
    state.articles.push(...articles);
  },
  [FETCH_CONTENT]: (state, payload) => {
    state.article = { ...payload };
  },
};
