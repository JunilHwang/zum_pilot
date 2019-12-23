import { $get } from '@/helper/http-wrapper';
import { MUSIC_FETCH } from '../mutations-type';

export default {
  [MUSIC_FETCH]: async ({ commit }, category = '실시간') => {
    const result = await $get(`/music?category=${encodeURIComponent(category)}`);
    result.forEach(v => Object.assign(v, { category }));
    commit(MUSIC_FETCH, result);
  },
};
