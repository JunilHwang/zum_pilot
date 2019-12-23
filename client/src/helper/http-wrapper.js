import $http from 'axios';
import { API_URL } from '@/middleware/store/const';
import responseValid from '@/helper/responseValid';

export const $get = (url, headers = {}) => new Promise((resolve) => {
  $http
    .get(`${API_URL}${url}`, headers)
    .then(({ data }) => responseValid(data, result => resolve(result)));
});

export const $post = (url, params, config = {}) => new Promise((resolve) => {
  $http
    .post(`${API_URL}${url}`, params, config)
    .then(({ data }) => responseValid(data, result => resolve(result)));
});

export const $patch = (url, params, config = {}) => new Promise((resolve) => {
  $http
    .patch(`${API_URL}${url}`, params, config)
    .then(({ data }) => responseValid(data, result => resolve(result)));
});

export default { get: $get, post: $post, patch: $patch };
