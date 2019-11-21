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
    const cache = localStorage.getItem(params.q);
    const call = payload => commit(FETCH_VIDEO, payload);
    if (cache === null) {
      $http
        .get(`${API_URL}/music-video`, { params })
        .then(({ data }) => {
          call({ ...data, selected: params.selected });
          localStorage.setItem(params.q, JSON.stringify(data));
        });
    } else {
      call({ ...params, ...JSON.parse(cache) });
    }
  },
};
