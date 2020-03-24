import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/index/Index'
import Device from '@/components/device/Device'
Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', name: 'Index', component: Index},
    {path: '/Device',
      name: 'Device',
      component: Device}
  ]
})
