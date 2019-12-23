import $http from 'axios';
import { USER_SIGN_IN, USER_SIGN_UP, USER_AUTH, USER_LOGOUT, MODAL_ALERT } from '../mutations-type';
import { API_URL } from '../const';
import { responseValid } from '@/helper';
import { router } from '@/middleware';

export default {
  [USER_SIGN_IN]: ({ commit }, payload) => {
    $http
      .post(`${API_URL}/sign-in`, payload)
      .then(({ data }) => {
        responseValid(data, () => commit(USER_SIGN_IN, data));
      });
  },
  [USER_SIGN_UP]: ({ commit }, payload) => {
    $http
      .post(`${API_URL}/sign-up`, payload)
      .then(({ data }) => {
        responseValid(data, () => {
          commit(MODAL_ALERT, '회원가입이 완료되었습니다.');
          router.push('/sign-in');
        });
      });
  },
  [USER_AUTH]: ({ state, commit }) => {
    const headers = { 'X-AUTH-TOKEN': state.token };
    $http
      .get(`${API_URL}/user`, { headers })
      .then(({ data }) => {
        responseValid(data, () => {
          if (data.result === 'anonymousUser') {
            commit(USER_LOGOUT);
          }
        });
      });
  },
};
