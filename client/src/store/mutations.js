import { CHANGE_TRANSITION } from './const';

const mutations = {
  [CHANGE_TRANSITION]: (state, payload) => {
    state.routerTransition = payload;
  },
};

export default mutations;
