import { IS_MEMBER } from '../const';
import { USER_SIGN_IN, USER_LOGOUT, USER_VIDEO_BOOKMARK } from '../mutations-type';
import { initState } from './state';

const save = state => sessionStorage.setItem('user', JSON.stringify(state));
const remove = () => sessionStorage.removeItem('user');

export default {
  [USER_SIGN_IN]: (state, result) => {
    const permission = IS_MEMBER;
    Object.assign(state, { permission, ...result });
    save(state);
  },
  [USER_LOGOUT]: (state) => {
    remove();
    Object.assign(state, { ...initState });
  },
  [USER_VIDEO_BOOKMARK]: (state, payload) => {
    state.bookmark = payload;
    save(state);
  },
};
