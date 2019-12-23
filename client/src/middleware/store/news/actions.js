import { $get } from '@/helper/http-wrapper';
import { NEWS_HEADLINE_FETCH, NEWS_POPULAR_FETCH, NEWS_ARTICLES_FETCH, NEWS_ARTICLES_APPEND, NEWS_CONTENT_FETCH } from '../mutations-type';

export default {
  [NEWS_HEADLINE_FETCH]: async ({ commit }) => {
    const result = await $get('/news/headline');
    commit(NEWS_HEADLINE_FETCH, result);
  },
  [NEWS_POPULAR_FETCH]: async ({ commit }) => {
    const result = await $get('/news/popular');
    commit(NEWS_POPULAR_FETCH, result);
  },
  [NEWS_ARTICLES_FETCH]: async ({ commit }, page = 1) => {
    const result = await $get(`/news?page=${page}`);
    const method = page === 1 ? NEWS_ARTICLES_FETCH : NEWS_ARTICLES_APPEND;
    commit(method, result);
  },
  [NEWS_CONTENT_FETCH]: async ({ commit }, url) => {
    const result = await $get(`/news_content?url=${encodeURIComponent(url)}`);
    commit(NEWS_CONTENT_FETCH, result);
  },
};
