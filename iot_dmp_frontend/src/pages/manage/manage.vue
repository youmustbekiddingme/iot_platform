<template>
    <div>
      <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1', '5']">

            <el-submenu index="1">
              <template slot="title"  >
                <div id="menu1" @click="userClick">
                  <img :src="userImgUrl" class="elImg" >  用户管理
                </div>
              </template>
            </el-submenu>

            <el-submenu index="2">
              <template slot="title">
                <div id="menu2" @click="netClick">
                  <img :src="wifiImgUrl" class="elImg" >   网络测试
                </div>
              </template>
            </el-submenu>

            <el-submenu index="3">
              <template slot="title">
                <div id="menu3" @click="burnClick">
                  <img :src="settingUrl" class="elImg" >   串口烧录
                </div>
              </template>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">
                <div id="menu4" @click="deviceClick">
                  <img :src="deviceUrl" class="elImg" >  设备管理
                </div>
              </template>
            </el-submenu>

            <el-submenu index="5">
              <template slot="title">
                <div id="menu5" @click="gisClick">
                  <img :src="gisUrl" class="elImg" >  Gis管理
                </div>
              </template>
            </el-submenu>

          </el-menu>
        </el-aside>

        <el-container>
          <el-header >
                高菲物联后台管理系统
          </el-header>


          <el-main  v-show="isShowUser" >
            <el-table :data="tableData">
              <el-table-column prop="date" label="日期" width="140">
              </el-table-column>
              <el-table-column prop="name" label="姓名" width="120">
              </el-table-column>
              <el-table-column prop="address" label="地址">
              </el-table-column>
            </el-table>
          </el-main>

          <el-main class="main-button" v-show="isShowNetwork">
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


          <el-main  v-show="isShowCom" >
            串口烧录
          </el-main>

          <el-main  v-show="isShowDevice" >
            设备管理
          </el-main>

          <el-main  v-show="isShowGis" >
            Gis管理
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
        wifiImgUrl:require("../../assets/imgs/wifi.png"),
        settingUrl:require("../../assets/imgs/设置.png"),
        deviceUrl:require("../../assets/imgs/设备.png"),
        gisUrl:require("../../assets/imgs/GIS应用.png"),
        tableData: Array(20).fill(item),
        isShowUser:false,
        isShowNetwork:false,
        isShowCom:false,
        isShowDevice:false,
        isShowGis:false
      }
    },
    methods:{
      userClick(){
        this.isShowUser=true;
        this.isShowNetwork=false;
        this.isShowCom=false;
        this.isShowDevice=false;
        this.isShowGis=false;
      },
      netClick(){
        this.isShowUser=false;
        this.isShowNetwork=true;
        this.isShowCom=false;
        this.isShowDevice=false;
      },
      burnClick(){
        this.isShowUser=false;
        this.isShowNetwork=false;
        this.isShowCom=true;
        this.isShowDevice=false;
        this.isShowGis=false;

      },
      deviceClick(){
        this.isShowUser=false;
        this.isShowNetwork=false;
        this.isShowCom=false;
        this.isShowDevice=true;
        this.isShowGis=false;

      },
      gisClick(){
        this.isShowUser=false;
        this.isShowNetwork=false;
        this.isShowCom=false;
        this.isShowDevice=false;
        this.isShowGis=true;
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
  .main-button{
    padding: 20px;  /*不起作用？？？？？？？？？？？？？？*/
  }
</style>
