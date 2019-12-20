import $http from 'axios';
import {
  VIDEO_FETCH,
  VIDEO_VIEW,
  VIDEO_SELECT,
  VIDEO_LIKE,
  VIDEO_POPULAR_FETCH,
  VIDEO_BOOKMARK,
  USER_VIDEO_BOOKMARK,
} from '../mutations-type';
import { API_URL } from '../const';
import { responseValid } from '@/helper';

export default {
  [VIDEO_FETCH]: ({ dispatch }, q) => {
    $http
      .get(`${API_URL}/video?q=${q}`)
      .then(({ data }) => {
        responseValid(data, result => dispatch(VIDEO_SELECT, result));
      });
  },
  [VIDEO_VIEW]: ({ commit, state }) => {
    const { viewCount, idx } = state.selectedVideo;
    $http
      .patch(`${API_URL}/video-view/${idx}`)
      .then(({ data }) => {
        responseValid(data, () => commit(VIDEO_VIEW, viewCount + 1));
      });
  },
  [VIDEO_SELECT]: ({ commit, rootState }, video) => {
    const { idx } = video;
    const userIdx = rootState.user.idx || 0;
    $http
      .get(`${API_URL}/video-like/${idx}?user_idx=${userIdx}`)
      .then(({ data }) => {
        responseValid(data, (result) => {
          Object.assign(video, { ...result });
          commit(VIDEO_SELECT, video);
        });
      });
  },
  [VIDEO_LIKE]: ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    $http
      .post(`${API_URL}/video-like`, { idx }, { headers })
      .then(({ data }) => {
        responseValid(data, () => commit(VIDEO_LIKE));
      });
  },
  [VIDEO_POPULAR_FETCH]: ({ commit }) => {
    $http
      .get(`${API_URL}/video-popular`)
      .then(({ data }) => {
        responseValid(data, result => commit(VIDEO_FETCH, result));
      });
  },
  [VIDEO_BOOKMARK]: ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    $http
      .post(`${API_URL}/video-bookmark`, { idx }, { headers })
      .then(({ data }) => {
        responseValid(data, result => commit(USER_VIDEO_BOOKMARK, result));
      });
  },
};
