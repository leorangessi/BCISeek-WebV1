import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../store/user'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/visitor'
    },
    {
      path: '/visitor',
      name: 'Visitor',
      component: () => import('../visitor/index.vue')
    },
    {
      path: '/visitor/community',
      name: 'Community',
      component: () => import('../visitor/components/CommunityPage.vue')
    },
    {
      path: '/guide',
      name: 'Guide',
      component: () => import('../visitor/components/GuidePage.vue')
    },
    {
      path: '/contact',
      name: 'Contact',
      component: () => import('../visitor/components/ContactPage.vue')
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../Login.vue')
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import('../Admin.vue'),
      meta: { requiresAdmin: true }
    }
  ]
})

router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  
  if (to.meta.requiresAdmin && !userStore.isAdmin()) {
    next('/login')
  } else {
    next()
  }
})

export default router