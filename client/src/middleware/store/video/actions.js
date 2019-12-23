import { $get, $post, $patch } from '@/helper/http-wrapper';
import {
  VIDEO_FETCH,
  VIDEO_VIEW,
  VIDEO_SELECT,
  VIDEO_LIKE,
  VIDEO_POPULAR_FETCH,
  VIDEO_BOOKMARK,
  USER_VIDEO_BOOKMARK,
} from '../mutations-type';

export default {
  [VIDEO_FETCH]: async ({ dispatch }, q) => {
    const result = await $get(`/video?q=${q}`);
    dispatch(VIDEO_SELECT, result);
  },
  [VIDEO_VIEW]: async ({ commit, state }) => {
    const { viewCount, idx } = state.selectedVideo;
    await $patch(`/video-view/${idx}`);
    commit(VIDEO_VIEW, viewCount + 1);
  },
  [VIDEO_SELECT]: async ({ commit, rootState }, video) => {
    const { idx } = video;
    const userIdx = rootState.user.idx || 0;
    const result = await $get(`/video-like/${idx}?user_idx=${userIdx}`);
    Object.assign(video, { ...result });
    commit(VIDEO_SELECT, video);
  },
  [VIDEO_LIKE]: async ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    await $post('/video-like', { idx }, { headers });
    commit(VIDEO_LIKE);
  },
  [VIDEO_POPULAR_FETCH]: async ({ commit }) => {
    const result = await $get('/video-popular');
    commit(VIDEO_FETCH, result);
  },
  [VIDEO_BOOKMARK]: async ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    const result = await $post('/video-bookmark', { idx }, { headers });
    commit(USER_VIDEO_BOOKMARK, result);
  },
};
