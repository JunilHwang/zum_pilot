import { $get } from '@/helper/http-wrapper';
import { MUSIC_FETCH, MUSIC_LOADING } from '../mutations-type';

export default {
  [MUSIC_FETCH]: async ({ commit }, category = '실시간') => {
    commit(MUSIC_LOADING, false);
    const result = await $get(`/music?category=${encodeURIComponent(category)}`);
    result.forEach(v => Object.assign(v, { category }));
    commit(MUSIC_FETCH, result);
    commit(MUSIC_LOADING, true);
  },
};
