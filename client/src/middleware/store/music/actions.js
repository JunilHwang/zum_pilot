import $http from 'axios';
import { FETCH_MUSIC, FETCH_VIDEO } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_MUSIC]: ({ commit }, category = '실시간') => {
    $http
      .get(`${API_URL}/music?category=${encodeURIComponent(category)}`)
      .then(({ data }) => {
        const { result } = data;
        result.forEach(v => Object.assign(v, { category }));
        commit(FETCH_MUSIC, result);
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
