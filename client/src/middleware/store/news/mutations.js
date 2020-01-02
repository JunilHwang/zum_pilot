import {
  NEWS_HEADLINE_FETCH,
  NEWS_POPULAR_FETCH,
  NEWS_ARTICLES_FETCH,
  NEWS_ARTICLES_APPEND,
  NEWS_CONTENT_FETCH,
  NEWS_CONTENT_VIEW,
  NEWS_LOADING,
} from '../mutations-type';

export default {
  [NEWS_HEADLINE_FETCH]: (state, payload) => { state.headline = payload; },
  [NEWS_POPULAR_FETCH]: (state, payload) => { state.populars = payload; },
  [NEWS_ARTICLES_FETCH]: (state, payload) => { state.articles = payload; },
  [NEWS_ARTICLES_APPEND]: (state, payload) => { state.articles.push(...payload); },
  [NEWS_CONTENT_FETCH]: (state, payload) => { state.article = { ...payload }; },
  [NEWS_CONTENT_VIEW]: (state, payload) => { state.viewState = payload; },
  [NEWS_LOADING]: (state, payload) => { state.articlesLoaded = payload; },
};
