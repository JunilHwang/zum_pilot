import $http from 'axios';
import { FETCH_VIDEO, VIEW_VIDEO } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_VIDEO]: ({ commit }, q) => {
    $http
      .get(`${API_URL}/video?q=${q}`)
      .then(({ data }) => {
        commit(FETCH_VIDEO, { ...data });
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
};
