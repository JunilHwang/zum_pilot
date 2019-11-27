import { FETCH_USER, IS_MEMBER } from './const';

export default {
  [FETCH_USER]: (state, result) => {
    const permission = IS_MEMBER;
    Object.assign(state, { permission, ...result });
  },
};
