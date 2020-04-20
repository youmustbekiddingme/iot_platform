<template>
<div>
  <el-main  >
    <el-table :data="tableData">
      <el-table-column prop="deviceName" label="设备名称" width="120">
      </el-table-column>
      <el-table-column prop="deviceId" label="设备编码" width="140">
      </el-table-column>
      <el-table-column prop="deviceType" label="设备类型" width="140">
      </el-table-column>
      <el-table-column prop="deviceAddress" label="设备地址" width="250">
      </el-table-column>
      <el-table-column prop="regTime" label="注册时间" width="140">
      </el-table-column>
      <el-table-column prop="isOnline" label="在线状态" width="100">

      </el-table-column>

    </el-table>
  </el-main>
</div>
</template>

<script>
    export default {
        name: "device_vue",
      data(){
          return {
            tableData:[]
          }
      }

    }
</script>

<style scoped>

</style>
