import { $get, $post, $patch } from '@/helper/http-wrapper';
import {
  VIDEO_FETCH,
  VIDEO_VIEW,
  VIDEO_SELECT,
  VIDEO_LIKE,
  VIDEO_POPULAR_FETCH,
  VIDEO_BOOKMARK,
  USER_VIDEO_BOOKMARK,
  VIDEO_LOADING,
} from '../mutations-type';

export default {
  // 음원 목록 선택으로 비디오를 가져올 때
  [VIDEO_FETCH]: async ({ dispatch }, query) => {
    dispatch(VIDEO_SELECT, await $get(`/video?q=${encodeURIComponent(query)}`));
  },

  // 비디오를 끝까지 재생했을 때 조회수를 증가시킨다.
  [VIDEO_VIEW]: async ({ commit, state }) => {
    const { viewCount, idx } = state.selectedVideo;
    await $patch(`/video/${idx}`);
    commit(VIDEO_VIEW, viewCount + 1);
  },

  // 선택한 Video를 Player에서 재생시킴
  [VIDEO_SELECT]: async ({ commit, rootState }, video) => {
    const { idx } = video;
    const userIdx = rootState.user.idx || 0;
    const result = await $get(`/video-like/${idx}?user_idx=${userIdx}`);
    Object.assign(video, { ...result });
    commit(VIDEO_SELECT, video);
  },

  // 좋아요 토글
  [VIDEO_LIKE]: async ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    await $post('/video-like', { idx }, { headers });
    commit(VIDEO_LIKE);
  },

  // 인기 영상 가져오기
  [VIDEO_POPULAR_FETCH]: async ({ commit }) => {
    commit(VIDEO_LOADING, false);
    const result = await $get('/video-popular');
    commit(VIDEO_FETCH, result);
    commit(VIDEO_LOADING, true);
  },

  // 즐겨찾기 가져오기
  [VIDEO_BOOKMARK]: async ({ commit, rootState }, idx) => {
    const { token } = rootState.user;
    const headers = { 'X-AUTH-TOKEN': token };
    const result = await $post('/video-bookmark', { idx }, { headers });
    commit(USER_VIDEO_BOOKMARK, result);
  },
};
