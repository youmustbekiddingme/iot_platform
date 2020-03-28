import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)
const debug = true
/* eslint-disable */
export default new Vuex.Store({
  modules: {

  },
  plugins: debug ? [createLogger()] : []   // 是否开启vuex的debug模式
})
