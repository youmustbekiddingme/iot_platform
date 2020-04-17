<template>
<div>
  <el-header >
    <div class="search">
      <div>
        昵称 <el-input v-model="nicknameInput" ></el-input>
      </div>
      <div>
        手机 <el-input v-model="nicknameInput" ></el-input>
      </div>
      <div>
        注册时间 <el-input v-model="nicknameInput" ></el-input>
      </div>
      <div>
        地址 <el-input v-model="nicknameInput" ></el-input>
      </div>
    </div>

    <el-button type="primary">搜索</el-button>
    <el-button type="danger">重置</el-button>

  </el-header>

  <el-main  >
    <el-table :data="tableData">
      <el-table-column prop="realname" label="真实姓名" width="120">
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="140">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="140">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="250">
      </el-table-column>
      <el-table-column prop="regTime" label="注册时间" width="140">
      </el-table-column>
      <el-table-column prop="isOnline" label="在线状态" width="100">
      </el-table-column>
      <el-table-column prop="address" label="地址" width="500">
      </el-table-column>

    </el-table>
  </el-main>

  <el-footer>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[10, 20, 30, 40]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="100">
      </el-pagination>
    </div>
  </el-footer>
</div>
</template>

<script>
    export default {
        name: "user.vue",
        data(){
          const item = {
            realname: '王小虎',
            nickname:'laowang',
            phone:'18320966643',
            email:'545018520@qq.com',
            regTime: '2016-05-02',
            address: '上海市普陀区金沙江路 1518 弄',
            isOnline:'在线'
          };

          return{
            tableData: Array(10).fill(item),
            nicknameInput:''
          }
        }
    }
</script>

<style scoped>
  .el-header {
    color: #333;
    text-align: center;
    line-height: 10px;
  }
  .search{
    display: flex;
    flex-wrap: wrap;
    justify-content:space-around;
  }

  .el-footer {
    margin:100px  5px  100px  5px;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
.el-input{
  width:100px
}


</style>
