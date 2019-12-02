import $http from 'axios';
import { FETCH_VIDEO, VIEW_VIDEO, SELECT_VIDEO } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_VIDEO]: ({ commit, dispatch }, q) => {
    $http
      .get(`${API_URL}/video?q=${q}`)
      .then(({ data }) => {
        if (data.success) {
          commit(FETCH_VIDEO, { ...data });
          dispatch(SELECT_VIDEO, data.result[0]);
        }
      });
  },
  [VIEW_VIDEO]: ({ commit, state }) => {
    const { viewCount, idx } = state.selectedVideo;
    $http
      .patch(`${API_URL}/video-view/${idx}`)
      .then(({ data }) => {
        if (data.success) {
          commit(VIEW_VIDEO, viewCount + 1);
        }
      });
  },
  [SELECT_VIDEO]: ({ commit, rootState }, video) => {
    const { idx } = video;
    const userIdx = rootState.user.idx || 0;
    $http
      .get(`${API_URL}/video-like/${idx}?user_idx=${userIdx}`)
      .then(({ data }) => {
        if (data.success) {
          Object.assign(video, { ...data.result });
          commit(SELECT_VIDEO, video);
        }
      });
  },
};
