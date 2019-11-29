import Vue from 'vue';
import VueRouter from 'vue-router';
import {
  News,
  Chart,
  Login,
  Join,
  Popular,
} from '@/views';

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
  {
    path: '/sign-in',
    component: Login,
  },
  {
    path: '/sign-up',
    component: Join,
  },
  {
    path: '/popular',
    component: Popular,
  },
];
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
