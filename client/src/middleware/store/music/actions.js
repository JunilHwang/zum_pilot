import $http from 'axios';
import { MUSIC_FETCH } from '../mutations-type';
import { API_URL } from '../const';
import { responseValid } from '@/helper';

export default {
  [MUSIC_FETCH]: ({ commit }, category = '실시간') => {
    $http
      .get(`${API_URL}/music?category=${encodeURIComponent(category)}`)
      .then(({ data }) => {
        responseValid(data, (result) => {
          result.forEach(v => Object.assign(v, { category }));
          commit(MUSIC_FETCH, result);
        });
      });
  },
};
