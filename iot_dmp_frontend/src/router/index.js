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
      component: (resolve) => require(['@/pages/index/login'], resolve),

    },
    {
      path: '/device',
      name: 'device',
      component: (resolve) => require(['@/pages/device/device'], resolve)
    },
    {
      path: '/dialog',
      name: 'dialog',
      component: (resolve) => require(['@/components/dialog'], resolve)
    }
  ]
})
