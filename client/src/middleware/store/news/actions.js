import $http from 'axios';
import {
  NEWS_HEADLINE_FETCH,
  NEWS_POPULAR_FETCH,
  NEWS_ARTICLES_FETCH,
  NEWS_ARTICLES_APPEND,
  NEWS_CONTENT_FETCH,
} from '../mutations-type';
import { API_URL } from '../const';

export default {
  [NEWS_HEADLINE_FETCH]: ({ commit }) => {
    $http
      .get(`${API_URL}/news/headline`)
      .then(({ data }) => {
        commit(NEWS_HEADLINE_FETCH, data);
      });
  },
  [NEWS_POPULAR_FETCH]: ({ commit }) => {
    $http
      .get(`${API_URL}/news/popular`)
      .then(({ data }) => {
        commit(NEWS_POPULAR_FETCH, data);
      });
  },
  [NEWS_ARTICLES_FETCH]: ({ commit }, page = 1) => {
    $http
      .get(`${API_URL}/news?page=${page}`)
      .then(({ data }) => {
        commit(page === 1 ? NEWS_ARTICLES_FETCH : NEWS_ARTICLES_APPEND, data);
      });
  },
  [NEWS_CONTENT_FETCH]: ({ commit }, url) => {
    $http
      .get(`${API_URL}/news_content?url=${encodeURIComponent(url)}`)
      .then(({ data }) => {
        commit(NEWS_CONTENT_FETCH, data);
      });
  },
};
