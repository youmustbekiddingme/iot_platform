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
      path: '/dialog',
      name: 'dialog',
      component: (resolve) => require(['@/components/dialog'], resolve)
    },
    {
      path: '/manage',
      name: 'manage',
      component: (resolve) => require(['@/pages/manage/manage'], resolve),
      redirect:'/user',
      children:[
        {
          path: '/user',
          name: 'user',
          component: (resolve) => require(['@/pages/manage/user/user'], resolve),
        },
        {
          path: '/device',
          name: 'device',
          component: (resolve) => require(['@/pages/manage/device/device'], resolve),
        },
        {
          path: '/network',
          name: 'network',
          component: (resolve) => require(['@/pages/manage/network/network'], resolve),
        },
        {
          path: '/burn',
          name: 'burn',
          component: (resolve) => require(['@/pages/manage/burn/burn'], resolve),
        }
      ]
    }

  ]
})
