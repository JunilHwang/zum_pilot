import Vue from 'vue';
import VueRouter from 'vue-router';
import { Main, Chart } from '@/views';

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
