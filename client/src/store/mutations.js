import { CHANGE_TRANSITION } from './const';

export default {
  [CHANGE_TRANSITION]: (state, payload) => {
    state.routerTransition = payload;
  },
};
