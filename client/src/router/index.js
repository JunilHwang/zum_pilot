import Vue from 'vue';
import VueRouter from 'vue-router';
import { Main } from '@/views';

Vue.use(VueRouter);
const routes = [
  {
    path: '/',
    alias: '/chart',
    component: Main,
  },
];
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
