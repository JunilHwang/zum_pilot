import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import helper from './helper';
import 'video.js/dist/video-js.min.css';

require('./icon');

Vue.config.productionTip = false;
Vue.prototype.helper = helper;

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
