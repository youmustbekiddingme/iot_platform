<template>
    <div>
      <el-container style="height: 900px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1', '5']">

            <el-submenu index="1">
              <template slot="title"  >
                <div id="menu1" @click="setIsShow('isShowUser')">
                  <img :src="userImgUrl" class="elImg" >  用户管理
                </div>
              </template>
            </el-submenu>

            <el-submenu index="2">
              <template slot="title">
                <div id="menu2" @click="setIsShow('isShowNetwork')">
                  <img :src="wifiImgUrl" class="elImg" >   网络测试
                </div>
              </template>
            </el-submenu>

            <el-submenu index="3">
              <template slot="title">
                <div id="menu3" @click="setIsShow('isShowCom')">
                  <img :src="settingUrl" class="elImg" >   串口烧录
                </div>
              </template>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">
                <div id="menu4" @click="setIsShow('isShowDevice')">
                  <img :src="deviceUrl" class="elImg" >  设备管理
                </div>
              </template>
            </el-submenu>

            <el-submenu index="5">
              <template slot="title">
                <div id="menu5" @click="setIsShow('isShowGis')">
                  <img :src="gisUrl" class="elImg" >  Gis管理
                </div>
              </template>
            </el-submenu>

          </el-menu>
        </el-aside>

        <el-container>


          <el-header >
                搜索条件框
          </el-header>


          <el-main  v-show="isShowUser" >
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

          <!--footer.............-->
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
        tableData: Array(10).fill(item),
        isShowUser:true,
        isShowNetwork:false,
        isShowCom:false,
        isShowDevice:false,
        isShowGis:false
      }
    },
    methods:{
      setIsShow(type){
        this.isShowUser=false;
        this.isShowNetwork=false;
        this.isShowCom=false;
        this.isShowDevice=false;
        this.isShowGis=false;
        this[type]=true;
        //do request search web
      }

    }
  }
</script>

<style scoped>
  .el-header {
    /*background-color: #B3C0D1;*/
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-footer {
    margin:100px  5px  100px  5px;
    color: #333;
    text-align: center;
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
