import $http from 'axios';
import { FETCH_USER, INSERT_USER } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_USER]: ({ commit }, payload) => {
    console.log(payload);
    $http
      .post(`${API_URL}/sign-in`, payload)
      .then(({ data }) => {
        if (data.success === false) {
          alert('아이디 또는 비밀번호가 일치하지 않습니다.');
          return;
        }
        commit(FETCH_USER, data.result);
        alert('로그인 되었습니다.');
      });
  },
  [INSERT_USER]: ({ commit }, payload) => {
    $http
      .post(`${API_URL}/sign-up`, payload)
      .then(({ data }) => {
        if (data.success === false) {
          alert('중복된 아이디가 있습니다.');
          return;
        }
        commit(INSERT_USER);
      });
  },
};
