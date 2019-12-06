import {
  NEWS_HEADLINE_FETCH,
  NEWS_POPULAR_FETCH,
  NEWS_ARTICLES_FETCH,
  NEWS_ARTICLES_APPEND,
  NEWS_CONTENT_FETCH,
  NEWS_CONTENT_VIEW,
} from '../mutations-type';

export default {
  [NEWS_HEADLINE_FETCH]: (state, { result }) => {
    state.headline = result;
  },
  [NEWS_POPULAR_FETCH]: (state, { result }) => {
    state.populars = result;
  },
  [NEWS_ARTICLES_FETCH]: (state, { result }) => {
    state.articles = result;
  },
  [NEWS_ARTICLES_APPEND]: (state, { result }) => {
    state.articles.push(...result);
  },
  [NEWS_CONTENT_FETCH]: (state, { result }) => {
    state.article = { ...result };
  },
  [NEWS_CONTENT_VIEW]: (state, payload) => {
    state.viewState = payload;
  },
};
