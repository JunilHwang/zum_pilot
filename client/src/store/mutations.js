const mutations = {
  changeRouterTransition(state, payload) {
    console.log(payload);
    state.routerTransition = payload;
  },
};

export default mutations;
