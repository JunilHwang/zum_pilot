import $http from 'axios';
import {
  USER_SIGN_IN,
  USER_SIGN_UP,
  USER_AUTH,
  USER_LOGOUT,
} from '../mutations-type';
import { API_URL } from '../const';

export default {
  [USER_SIGN_IN]: ({ commit }, payload) => {
    $http
      .post(`${API_URL}/sign-in`, payload)
      .then(({ data }) => {
        if (data.success === false) {
          alert('아이디 또는 비밀번호가 일치하지 않습니다.');
          return;
        }
        commit(USER_SIGN_IN, data);
        alert('로그인 되었습니다.');
      });
  },
  [USER_SIGN_UP]: (context, payload) => $http.post(`${API_URL}/sign-up`, payload),
  [USER_AUTH]: ({ state, commit }) => {
    const headers = { 'X-AUTH-TOKEN': state.token };
    $http
      .get(`${API_URL}/user`, { headers })
      .then(({ data }) => {
        const { success, result } = data;
        if (!success || result === 'anonymousUser') {
          commit(USER_LOGOUT);
        }
      });
  },
};
