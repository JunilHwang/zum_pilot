import $http from 'axios';
import { FETCH_VIDEO } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_VIDEO]: ({ commit }, q) => {
    const cache = JSON.parse(localStorage.getItem(q));
    const call = payload => commit(FETCH_VIDEO, payload);
    if (cache === null) {
      $http
        .get(`${API_URL}/video?q=${q}`)
        .then(({ data }) => {
          call({ ...data });
          localStorage.setItem(q, JSON.stringify(data));
        });
    } else {
      call({ ...cache });
    }
  },
};
