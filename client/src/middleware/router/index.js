import Vue from 'vue';
import VueRouter from 'vue-router';
import { News, Chart, Login, Join, Popular, Bookmark } from '@/views';

Vue.use(VueRouter);

const mode = 'history';
const base = process.env.BASE_URL;
const routes = [
  { path: '/', component: News, alias: '/news' },
  { path: '/chart', component: Chart },
  { path: '/sign-in', component: Login },
  { path: '/sign-up', component: Join },
  { path: '/popular', component: Popular },
  { path: '/bookmark', component: Bookmark },
];

export default new VueRouter({ mode, base, routes });
