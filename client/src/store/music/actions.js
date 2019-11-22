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
    $http
      .get(`${API_URL}/music-video`, { params })
      .then(({ data }) => {
        commit(FETCH_VIDEO, { ...data, selected: params.selected });
      });
  },
};
