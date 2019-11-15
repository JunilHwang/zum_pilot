import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import helper from './helper';
import VueYoutubeEmbed from 'vue-youtube-embed';
import VueCarousel from 'vue-carousel';

require('./icon');

Vue.config.productionTip = false;
Vue.prototype.helper = helper;
Vue.use(VueYoutubeEmbed);
Vue.use(VueCarousel);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
