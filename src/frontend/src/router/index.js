import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/articles/:articleId/waiting',
    name: 'waitingRoom',
    component: () => import('../views/WaitingRoom.vue')
  },
  {
    path: '/article/:articleId',
    name: 'articleInfo',
    component: () => import('../views/ShowArticle.vue'),
    props: true
  },
  {
    path: '/404',
    name: '404',
    component: () => import('../views/404.vue')
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
