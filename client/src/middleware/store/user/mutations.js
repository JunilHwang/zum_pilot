import { IS_MEMBER } from '../const';
import { USER_SIGN_IN, USER_LOGOUT, USER_VIDEO_BOOKMARK } from '../mutations-type';
import { initState } from './state';

export default {
  [USER_SIGN_IN]: (state, { result }) => {
    const permission = IS_MEMBER;
    Object.assign(state, { permission, ...result });
    sessionStorage.setItem('user', JSON.stringify(state));
  },
  [USER_LOGOUT]: (state) => {
    sessionStorage.removeItem('user');
    Object.assign(state, { ...initState });
  },
  [USER_VIDEO_BOOKMARK]: (state, payload) => {
    state.bookmark = payload;
  },
};
