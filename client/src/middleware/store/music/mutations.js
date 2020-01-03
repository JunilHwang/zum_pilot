import { MUSIC_FETCH, MUSIC_SELECT, MUSIC_LOADING } from '../mutations-type';

export default {
  [MUSIC_FETCH]: (state, payload) => { state.articles = payload; },
  [MUSIC_SELECT]: (state, payload) => { state.selectedMusic = payload; },
  [MUSIC_LOADING]: (state, payload) => { state.loaded = payload; },
};
