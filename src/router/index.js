import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/tag',
      name: 'tag',
      component: () => import('../views/TagView.vue')
    },
    {
      path: '/leaveMessage',
      name: 'leaveMessage',
      component: () => import('../views/LeaveMessageView.vue')
    }
  ]
})

export default router
