import { MUSIC_FETCH, MUSIC_SELECT } from '../mutations-type';

export default {
  [MUSIC_FETCH]: (state, payload) => { state.articles = payload; },
  [MUSIC_SELECT]: (state, payload) => { state.selectedMusic = payload; },
};
