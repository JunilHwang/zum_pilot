import { IS_MEMBER } from '../const';
import { USER_SIGN_IN, USER_LOGOUT } from '../mutations-type';
import { initState } from './state';

export default {
  [USER_SIGN_IN]: (state, { result, token }) => {
    const permission = IS_MEMBER;
    Object.assign(state, { permission, ...result, token });
    sessionStorage.setItem('user', JSON.stringify(state));
  },
  [USER_LOGOUT]: (state) => {
    sessionStorage.removeItem('user');
    Object.assign(state, { ...initState });
  },
};
