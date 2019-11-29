import $http from 'axios';
import { FETCH_MUSIC } from './const';
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
};
