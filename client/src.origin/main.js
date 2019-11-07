import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

Vue.config.productionTip = false;

router.afterEach((to, from) => {
  store.commit('changeRouterTransition', '');
});

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
