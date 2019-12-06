import Vue from 'vue';
import { router, store } from './middleware';
import App from './App.vue';
import { USER_AUTH } from '@/middleware/store/mutations-type';

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App),
  mounted() {
    store.dispatch(USER_AUTH);
  },
}).$mount('#app');
