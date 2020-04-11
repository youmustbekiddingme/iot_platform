<template>
    <div>
      <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1', '3']">

            <el-submenu index="1">
              <template slot="title"  >
                <div id="menu1" @click="userClick">
                  <img :src="userImgUrl" class="elImg" >用户管理
                </div>
              </template>
            </el-submenu>

            <el-submenu index="2">
              <template slot="title">
                <div id="menu2" @click="deviceClick">
                <i class="el-icon-menu"></i>设备管理
                </div>
              </template>
            </el-submenu>

            <el-submenu index="3">
              <template slot="title">
                <div id="menu3" @click="burnClick">
                <i class="el-icon-setting"></i> 程序烧录
                </div>
              </template>
            </el-submenu>

          </el-menu>
        </el-aside>

        <el-container>
          <el-header style="text-align: right; font-size: 12px">
            <!--<el-dropdown>-->
              <!--<i class="el-icon-setting" style="margin-right: 15px"></i>-->
              <!--<el-dropdown-menu slot="dropdown">-->
                <!--<el-dropdown-item>查看</el-dropdown-item>-->
                <!--<el-dropdown-item>新增</el-dropdown-item>-->
                <!--<el-dropdown-item>删除</el-dropdown-item>-->
              <!--</el-dropdown-menu>-->
            <!--</el-dropdown>-->
            <!--<span>王小虎</span>-->
          </el-header>

          <el-main>
            <el-table :data="tableData">
              <el-table-column prop="date" label="日期" width="140">
              </el-table-column>
              <el-table-column prop="name" label="姓名" width="120">
              </el-table-column>
              <el-table-column prop="address" label="地址">
              </el-table-column>
            </el-table>
          </el-main>

        </el-container>

      </el-container>
    </div>
</template>

<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        userImgUrl:require("../../assets/imgs/用户.png"),
        tableData: Array(20).fill(item)
      }
    },
    methods:{
      userClick(){
        alert("userClick")
      },
      deviceClick(){
        alert("deviceClick")

      },
      burnClick(){
        alert("burnClick")
      }

    }
  }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
  .elImg{
    width:15px;
    height:15px;
  }
</style>
