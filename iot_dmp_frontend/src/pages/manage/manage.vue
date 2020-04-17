<template>
    <div>
      <el-container style="height: 800px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1', '4']">

            <el-submenu index="1">
              <template slot="title"  >
                <router-link to="/user">
                <div id="menu1" >
                  <img :src="userImgUrl" class="elImg" >  用户管理
                </div>
                </router-link>
              </template>
            </el-submenu>


            <el-submenu index="2">
              <template slot="title">
                <router-link to="/device">
                <div id="menu2" >
                  <img :src="deviceUrl" class="elImg" >  设备管理
                </div>
                </router-link>

              </template>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <router-link to="/network">
                  <div id="menu3" >
                    <img :src="wifiImgUrl" class="elImg" >  网络测试
                  </div>
                </router-link>

              </template>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">
                <router-link to="/burn">
                  <div id="menu4" >
                    <img :src="settingUrl" class="elImg" >  程序烧录
                  </div>
                </router-link>

              </template>
            </el-submenu>



          </el-menu>
        </el-aside>

        <el-container>

          <router-view></router-view>
        </el-container>

      </el-container>
    </div>
</template>

<script>
  export default {
    data() {
      const item = {
        realname: '王小虎',
        nickname:'laowang',
        phone:'18320966643',
        email:'545018520@qq.com',
        regTime: '2016-05-02',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        userImgUrl:require("../../assets/imgs/用户.png"),
        wifiImgUrl:require("../../assets/imgs/wifi.png"),
        settingUrl:require("../../assets/imgs/设置.png"),
        deviceUrl:require("../../assets/imgs/设备.png"),
        gisUrl:require("../../assets/imgs/GIS应用.png"),
        tableData: Array(10).fill(item)
      }
    },
    methods:{


    }
  }
</script>

<style scoped>

  .elImg{
    width:15px;
    height:15px;
  }

  a {
    text-decoration: none;
  }


</style>
