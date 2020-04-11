import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
// 需要左方向动画的路由用this.$router.to('****')

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'login',
      component: (resolve) => require(['@/pages/index/login'], resolve)

    },
    {
      path: '/register',
      name: 'register',
      component: (resolve) => require(['@/pages/index/register'], resolve)
    },
    {
      path: '/manage',
      name: 'manage',
      component: (resolve) => require(['@/pages/manage/manage'], resolve)
    },
    {
      path: '/dialog',
      name: 'dialog',
      component: (resolve) => require(['@/components/dialog'], resolve)
    }

  ]
})
