import VueYoutube from 'vue-youtube';
import VueFlicking from '@egjs/vue-flicking';
import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import helper from './helper';

require('./icon');

Vue.config.productionTip = false;
Vue.prototype.helper = helper;
Vue.use(VueYoutube);
Vue.use(VueFlicking);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
