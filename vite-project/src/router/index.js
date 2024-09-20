import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../App.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/usersList',
      name: 'usersList',
      component: () => import('../components/users/list.vue')
    },
    {
      path: '/usersAdd',
      name: 'usersAdd',
      component: () => import('../components/users/add.vue')
    },
  ]
})

export default router
