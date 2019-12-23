import $http from 'axios';
import { MODAL_ALERT, USER_LOGOUT } from '@/middleware/store/mutations-type';
import { store } from '@/middleware';

// Proxy로 처리할 API의 URL을 지정
const API_URL = '/api';

/**
 * response를 받아온 후 error에 대해 처리한 다음 뒷 일은 resolve에게 위임한다.
 * @param response
 * @returns {Promise<unknown>}
 */
const responseValid = response => new Promise(resolve => (
  response.then(({ data }) => {
    const { success, code, msg, result } = data;
    if (success) {
      resolve(result);
      return;
    }
    const { commit } = store;
    commit(MODAL_ALERT, msg);
    if (code === -1) commit(USER_LOGOUT);
  })));

// http get
export const $get = (url, config = {}) => (
  responseValid($http.get(`${API_URL}${url}`, config))
);

// http post
export const $post = (url, params, config = {}) => (
  responseValid($http.post(`${API_URL}${url}`, params, config))
);

// http patch
export const $patch = (url, params, config = {}) => (
  responseValid($http.patch(`${API_URL}${url}`, params, config))
);

export default { };
