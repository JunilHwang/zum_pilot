import $http from 'axios';
import {
  API_URL,
  FETCH_HEADLINE,
  FETCH_POPULAR,
  FETCH_ARTICLES, APPEND_ARTICLES,
} from './const';

export default {
  [FETCH_HEADLINE]: ({ commit }) => {
    $http
      .get(`${API_URL}/news/headline`)
      .then(({ data }) => {
        commit(FETCH_HEADLINE, data);
      });
  },
  [FETCH_POPULAR]: ({ commit }) => {
    $http
      .get(`${API_URL}/news/popular`)
      .then(({ data }) => {
        commit(FETCH_POPULAR, data);
      });
  },
  [FETCH_ARTICLES]: ({ commit }, page = 1) => {
    $http
      .get(`${API_URL}/news?page=${page}`)
      .then(({ data }) => {
        commit(page === 1 ? FETCH_ARTICLES : APPEND_ARTICLES, data);
      });
  },
};
