import Vue from 'vue';
import Vuex from 'vuex';

import state from './state';
import mutations from './mutations';
import actions from './actions';
import modules from './modules';

Vue.use(Vuex);

console.log(state);

export default new Vuex.Store({
  state,
  mutations,
  actions,
  modules,
});
