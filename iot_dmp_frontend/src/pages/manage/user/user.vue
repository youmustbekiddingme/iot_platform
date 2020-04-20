<template>
<div>
  <el-header >
    <div class="search">
      <div>
        昵称 <el-input v-model="nicknameInput" ></el-input>
      </div>
      <div>
        手机 <el-input v-model="phoneInput" ></el-input>
      </div>
      <div>
        注册时间 <el-input v-model="regTimeInput" ></el-input>
      </div>
      <div>
        地址 <el-input v-model="addressInput" ></el-input>
      </div>
    </div>

    <el-button type="primary" @click="searchClick">搜索</el-button>
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
      <el-table-column prop="regtime" label="注册时间" width="140">
      </el-table-column>
      <el-table-column prop="isonline" label="在线状态" width="100">
      </el-table-column>
      <el-table-column prop="address" label="地址" width="500">
      </el-table-column>

    </el-table>
  </el-main>

  <el-footer>
    <div class="block">
      <el-pagination
        @size-change="handlePageSizeChange"
        @current-change="handlePageNumChange"
        :page-sizes="[10, 20, 30, 40]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </el-footer>
</div>
</template>

<script>
    export default {
        name: "user_vue",
        data(){
          return{
            total:0,
            // tableData: Array(10).fill(item),
            tableData:[],
            nicknameInput:'',
            phoneInput:'',
            regTimeInput:'',
            addressInput:'',
            pageSize:"10",
            pageNum:"1"
          }
        },
      methods:{
          //获取查询的公共参数
          getParamData(){
            var data={
              nickname:this.nicknameInput,
              phone:this.phoneInput,
              regTime:this.regTimeInput,
              address:this.addressInput,
              pageNum:this.pageNum,
              pageSize:this.pageSize
            }
            return data;
          },
          searchClick(){
              //单机模糊查询多个
              this.$axios.post("http://localhost:8080/user/many",{body:window.JSON.stringify(this.getParamData())}).then(res=>{
                      var result= res.data
                      this.tableData=result.list
              })
          },
        //每页显示多少条
        handlePageSizeChange(val){
            //将val转为string ，不然传递给后端就是double类型了
           this.pageSize= val.toString()

          //单机模糊查询多个
          this.$axios.post("http://localhost:8080/user/many",{body:window.JSON.stringify(this.getParamData())}).then(res=>{
            var result= res.data
            this.tableData=result.list
          })
        },
        //页码数
        handlePageNumChange(val){
            this.pageNum=val.toString()
          //单机模糊查询多个
          this.$axios.post("http://localhost:8080/user/many",{body:window.JSON.stringify(this.getParamData())}).then(res=>{
            var result= res.data
            this.tableData=result.list
          })
        },

      },
      //创建vue实例前，查询总记录数
      created:function(){
          var data={}
          this.$axios.post("http://localhost:8080/user/count",{
            body: window.JSON.stringify(data)
          }).then(res=>{
            var result = res.data
            if(result.code==1){
              this.total=result.list[0]
            }
            })
      },
      mounted:function () {

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
