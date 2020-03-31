<template>
  <div id="app">
      <!--访问index.html 绑定了App.vue ，根路由 绑定的index.vue ，index1.vue 里面又有子路由story，home，footprints，day。home又是子路由的默认首页显示路由-->
      <router-view ></router-view>

  </div>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {

    }
  }
}
</script>

<style lang="less">

</style>
