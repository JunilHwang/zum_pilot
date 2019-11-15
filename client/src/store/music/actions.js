import $http from 'axios';
import {
  FETCH_MUSIC,
  FETCH_VIDEO,
} from './const';
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
    const cache = localStorage.getItem(params.q);
    const call = payload => commit(FETCH_VIDEO, payload);
    if (cache === null) {
      $http
        .get(`${API_URL}/music-video`, { params })
        .then(({ data }) => {
          const payload = { ...data, ...params };
          call(payload);
          localStorage.setItem(params.q, JSON.stringify(payload));
        });
    } else {
      call(JSON.parse(cache));
    }
  },
};
