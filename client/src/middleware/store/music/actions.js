import $http from 'axios';
import { FETCH_MUSIC, FETCH_VIDEO } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_MUSIC]: ({ commit }) => {
    $http
      .get(`${API_URL}/music`)
      .then(({ data }) => {
        commit(FETCH_MUSIC, data);
      });
  },
  [FETCH_VIDEO]: ({ commit }, params) => {
    const { q, selected } = params;
    const cache = JSON.parse(localStorage.getItem(q));
    if (cache === null) {
      $http
        .get(`${API_URL}/music-video?q=${q}`)
        .then(({ data }) => {
          commit(FETCH_VIDEO, { ...data, selected });
          localStorage.setItem(q, JSON.stringify(data));
        });
    } else {
      commit(FETCH_VIDEO, { ...cache, selected });
    }
  },
};