import $http from 'axios';
import { FETCH_VIDEO, VIEW_VIDEO } from './const';
import { API_URL } from '../const';

export default {
  [FETCH_VIDEO]: ({ commit }, q) => {
    const cache = JSON.parse(localStorage.getItem(q));
    const call = payload => commit(FETCH_VIDEO, payload);
    if (cache === null) {
      $http
        .get(`${API_URL}/video?q=${q}`)
        .then(({ data }) => {
          call({ ...data });
          localStorage.setItem(q, JSON.stringify(data));
        });
    } else {
      call({ ...cache });
    }
  },
  [VIEW_VIDEO]: ({ commit, state }, idx) => {
    const viewCount = state.selectedVideo.viewCount + 1;
    $http
      .patch(`${API_URL}/video-view/${idx}`, { viewCount })
      .then(({ data }) => {
        const { success } = data;
        if (success) {
          commit(VIEW_VIDEO, viewCount);
        }
      });
  },
};
