import { SIGN_IN, IS_MEMBER, LOGOUT } from './const';
import { initState } from './state';

export default {
  [SIGN_IN]: (state, { result, token }) => {
    const permission = IS_MEMBER;
    Object.assign(state, { permission, ...result, token });
    sessionStorage.setItem('user', JSON.stringify(state));
  },
  [LOGOUT]: (state) => {
    sessionStorage.removeItem('user');
    Object.assign(state, { ...initState });
  },
};
