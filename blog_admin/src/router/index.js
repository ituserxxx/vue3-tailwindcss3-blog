import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('../components/login/login.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../components/home/home.vue')
    },
    /*---------------tags ----------------*/
    {
      path: '/tagsList',
      name: 'tagsList',
      component: () => import('../components/tags/list.vue')
    },
    {
      path: '/tagsAdd',
      name: 'tagsAdd',
      component: () => import('../components/tags/add.vue')
    },
    {
      path: '/tagsEdit',
      name: 'tagsEdit',
      component: () => import('../components/tags/edit.vue')
    },
    /*---------------user ----------------*/
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
    {
      path: '/usersEdit',
      name: 'usersEdit',
      component: () => import('../components/users/edit.vue')
    },
    /*---------------leaveMessage ----------------*/
    {
      path: '/leaveMessageList',
      name: 'tagsLleaveMessageListist',
      component: () => import('../components/leaveMessage/list.vue')
    },
    {
      path: '/leaveMessageAdd',
      name: 'leaveMessageAdd',
      component: () => import('../components/leaveMessage/add.vue')
    },
    /*---------------articles ----------------*/
    {
      path: '/articlesList',
      name: 'articlesList',
      component: () => import('../components/articles/list.vue')
    },
    {
      path: '/articlesAdd',
      name: 'articlesAdd',
      component: () => import('../components/articles/add.vue')
    },
    {
      path: '/articlesEdit',
      name: 'articlesEdit',
      component: () => import('../components/articles/edit.vue')
    },
  ]
})

export default router
