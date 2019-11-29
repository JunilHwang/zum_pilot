import $http from 'axios';
import { SIGN_IN, SIGN_UP, FETCH_USER } from './const';
import { API_URL } from '../const';

export default {
  [SIGN_IN]: ({ commit }, payload) => {
    $http
      .post(`${API_URL}/sign-in`, payload)
      .then(({ data }) => {
        if (data.success === false) {
          alert('아이디 또는 비밀번호가 일치하지 않습니다.');
          return;
        }
        commit(SIGN_IN, data);
        alert('로그인 되었습니다.');
      });
  },
  [SIGN_UP]: (context, payload) => $http.post(`${API_URL}/sign-up`, payload),
  [FETCH_USER]: ({ state }) => $http.get(`${API_URL}/user`, {
    headers: { 'X-AUTH-TOKEN': state.token },
  }),
};
