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
    // 새로고침할 때 마다 token 유효성 검사
    store.dispatch(USER_AUTH);
  },
}).$mount('#app');
