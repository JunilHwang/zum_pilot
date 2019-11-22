import VueYoutube from 'vue-youtube';
import Vue from 'vue';
import router from './router';
import store from './store';
import helper from './helper';
import App from './App.vue';
import './icon';

Vue.config.productionTip = false;
Vue.prototype.helper = helper;
Vue.use(VueYoutube);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
