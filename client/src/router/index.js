import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);
const routes = [
  {
    path: '/',
    component: () => import('../views/Main.vue'),
  },
  {
    path: '/news/:url',
    component: () => import('../views/news/View.vue'),
  },
  {
    path: '/chart',
    component: () => import('../views/Chart.vue'),
  },
];
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
