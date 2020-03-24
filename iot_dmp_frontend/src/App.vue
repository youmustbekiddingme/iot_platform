<template>
  <div id="app">
    <Index></Index>

    <!--<router-view/>-->
  </div>
</template>

<script>
  import Index from './components/index/Index.vue'
export default {
  name: 'App',
  components:{
    'Index':Index
  }
}
</script>

<style>
#app {
  width:100%;
  height:100%;
  background-color: #ffffff;
}
html, body {
  height: 100%;
  margin: 0px;
  padding: 0px;
}
</style>
