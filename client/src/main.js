import VueYoutube from 'vue-youtube';
import Vue from 'vue';
import router from './middleware/router';
import store from './middleware/store';
import App from './App.vue';
import './icon';

Vue.config.productionTip = false;
Vue.use(VueYoutube);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
