import Vue from 'vue';
import Vuex from 'vuex';
import news from './news';
import music from './music';
import user from './user';
import video from './video';
import modal from './modal';

const state = {};
const mutations = {};
const actions = {};
const modules = { news, music, user, video, modal };

Vue.use(Vuex);

export default new Vuex.Store({ state, mutations, actions, modules });
