import $http from 'axios';
import { FETCH_MUSIC } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_MUSIC]: ({ commit }) => {
    $http
      .get(`${API_URL}/music`)
      .then(({ data }) => {
        commit(FETCH_MUSIC, data);
      });
  },
};
