import $http from 'axios';
import {
  VIDEO_FETCH,
  VIDEO_VIEW,
  VIDEO_SELECT,
  VIDEO_LIKE,
  VIDEO_POPULAR_FETCH,
  VIDEO_BOOKMARK,
  VIDEO_FETCH_BOOKMARK,
  USER_VIDEO_BOOKMARK,
} from '../mutations-type';
import { API_URL } from '../const';
import { responseProxyWithAuth } from '@/helper';

export default {
  [VIDEO_FETCH]: ({ commit, dispatch }, q) => {
    $http
      .get(`${API_URL}/video?q=${q}`)
      .then(({ data }) => {
        const { success, result } = data;
        if (success) {
          commit(VIDEO_FETCH, result);
          dispatch(VIDEO_SELECT, result[0]);
        }
      });
  },
  [VIDEO_VIEW]: ({ commit, state }) => {
    const { viewCount, idx } = state.selectedVideo;
    $http
      .patch(`${API_URL}/video-view/${idx}`)
      .then(({ data }) => {
        if (data.success) {
          commit(VIDEO_VIEW, viewCount + 1);
        }
      });
  },
  [VIDEO_SELECT]: ({ commit, rootState }, video) => {
    const { idx } = video;
    const userIdx = rootState.user.idx || 0;
    $http
      .get(`${API_URL}/video-like/${idx}?user_idx=${userIdx}`)
      .then(({ data }) => {
        const { success, result } = data;
        if (success) {
          Object.assign(video, { ...result });
          commit(VIDEO_SELECT, video);
        }
      });
  },
  [VIDEO_LIKE]: ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    $http
      .post(`${API_URL}/video-like`, { idx }, { headers })
      .then(({ data }) => {
        responseProxyWithAuth(commit, data, () => {
          commit(VIDEO_LIKE, idx);
        });
      });
  },
  [VIDEO_POPULAR_FETCH]: ({ commit }) => {
    $http
      .get(`${API_URL}/video-popular`)
      .then(({ data }) => {
        const { success, result } = data;
        if (success) {
          commit(VIDEO_FETCH, result);
        }
      });
  },
  [VIDEO_BOOKMARK]: ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    $http
      .post(`${API_URL}/video-bookmark`, { idx }, { headers })
      .then(({ data }) => {
        const { result } = data;
        responseProxyWithAuth(commit, data, () => {
          commit(USER_VIDEO_BOOKMARK, result);
        });
      });
  },
  [VIDEO_FETCH_BOOKMARK]: ({ commit, rootState }) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    $http
      .get(`${API_URL}/video-bookmark`, { headers })
      .then(({ data }) => {
        const { result } = data;
        responseProxyWithAuth(commit, data, () => {
          commit(VIDEO_FETCH, result);
        });
      });
  },
};
