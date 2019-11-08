import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import { CHANGE_TRANSITION } from './store/const';

Vue.config.productionTip = false;

router.afterEach((to, from) => {
  store.commit(CHANGE_TRANSITION, '');
});

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
