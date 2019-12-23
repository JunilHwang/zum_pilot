import $http from 'axios';
import { MODAL_ALERT, USER_LOGOUT } from '@/middleware/store/mutations-type';
import { store } from '@/middleware';

const API_URL = '/api';

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

export const $get = (url, config = {}) => (
  responseValid($http.get(`${API_URL}${url}`, config))
);
export const $post = (url, params, config = {}) => (
  responseValid($http.post(`${API_URL}${url}`, params, config))
);
export const $patch = (url, params, config = {}) => (
  responseValid($http.patch(`${API_URL}${url}`, params, config))
);

export default { };
