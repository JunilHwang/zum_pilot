import Vue from 'vue';
import VueRouter from 'vue-router';

const Main = () => import('../views/Main.vue');
const Chart = () => import('../views/Chart.vue');

Vue.use(VueRouter);
const routes = [
  {
    path: '/',
    component: Main,
  },
  {
    path: '/chart',
    component: Chart,
  },
];
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
