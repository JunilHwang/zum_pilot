import {
  FETCH_HEADLINE, FETCH_POPULAR, FETCH_ARTICLES, APPEND_ARTICLES,
  FETCH_CONTENT, VIEW_CONTENT,
} from './const';

export default {
  [FETCH_HEADLINE]: (state, { result }) => {
    state.headline = result;
  },
  [FETCH_POPULAR]: (state, { result }) => {
    state.populars = result;
  },
  [FETCH_ARTICLES]: (state, { result }) => {
    state.articles = result;
  },
  [APPEND_ARTICLES]: (state, { result }) => {
    state.articles.push(...result);
  },
  [FETCH_CONTENT]: (state, { result }) => {
    state.article = { ...result };
  },
  [VIEW_CONTENT]: (state, payload) => {
    state.viewState = payload;
  },
};
