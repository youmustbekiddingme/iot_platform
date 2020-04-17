<template>
    <div>
      <el-main class="main-button" >
        <el-button type="primary" >开启TCP-SERVER</el-button>
        <el-button type="primary" >开启TCP-CLIENT</el-button>
        <el-button type="success" >开启UDP-SERVER</el-button>
        <el-button type="success" >开启UDP-CLIENT</el-button>
        <br>
        <br>
        <el-button type="danger" >关闭TCP-SERVER</el-button>
        <el-button type="danger" >关闭TCP-CLIENT</el-button>
        <el-button type="danger" >关闭UDP-SERVER</el-button>
        <el-button type="danger" >关闭UDP-CLIENT</el-button>

      </el-main>
    </div>
</template>

<script>
    export default {
        name: "network"
    }
</script>

<style scoped>

</style>
