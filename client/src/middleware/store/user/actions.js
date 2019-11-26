import $http from 'axios';
import { FETCH_USER, INSERT_USER } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_USER]: ({ commit }, payload) => {
    $http
      .post(`${API_URL}/user/sign-in`, payload)
      .then(({ data }) => {
        commit(FETCH_USER, data);
      });
  },
  [INSERT_USER]: ({ commit }, payload) => {
    $http
      .post(`${API_URL}/user/sign-up`, payload)
      .then(({ data }) => {
        commit(INSERT_USER, data);
      });
  },
};
