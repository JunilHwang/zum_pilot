import Vue from 'vue';
import VueRouter from 'vue-router';
import { News, Chart } from '@/views';

Vue.use(VueRouter);
const routes = [
  {
    path: '/',
    alias: '/news',
    component: News,
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
