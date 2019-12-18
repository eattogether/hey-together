import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/404',
    name: '404',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/404.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../components/Login.vue')
  },
  {
    path: '/write',
    name: 'write',
    component: () => import('../components/ArticleForm.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
