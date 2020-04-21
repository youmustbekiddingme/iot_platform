<template>
<div>
  <dialog_delete :sonIsDialogShow.sync="isDialogShow"  :dialogPrompt="dialogDeletePrompt"  :userRow="userRow"></dialog_delete>   <!--msg 父组件传递给子组件-->
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
    <el-button type="success" @click="addClick" >添加</el-button>
    <el-button type="primary" @click="searchClick"  >搜索</el-button>
    <el-button type="danger" @click="resetClick">重置</el-button>

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
      <el-table-column prop="address" label="地址" width="300">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template scope="scope" >
          <div class="operatex">
            <div>
              <el-button type="primary" size="small" @click="userEdit(scope.$index, scope.row)">编辑</el-button>
            </div>
            <div>
              <el-button type="danger" size="small" @click="deleteClick(scope.row)">删除</el-button>
            </div>
          </div>
        </template>
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
  import dialog_delete from '@/components/dialog/userDeleteDailog'
    export default {
        components:{
          dialog_delete
        },
        name: "user_vue",
        data(){
          return{
            total:0, //记录总数
            tableData:[], //table数据
            nicknameInput:'',
            phoneInput:'',
            regTimeInput:'',
            addressInput:'',
            isDialogShow:false, //是否显示对话框
            dialogDeletePrompt:'用户删除',  //删除对话框提示
            userRow:'',   //单个用户行数据，需要传递给子组件对话框
            pageSize:"10", //每页数据条数
            pageNum:"1", //页码
            userSelectManyUrl:'http://localhost:8080/user/searchMany',
            userSelectCountUrl:'http://localhost:8080/user/count',
            userDeleteOneUrl:'http://localhost:8080/user/deleteOne'
          }
        },
      methods:{
          getParamData(){    //获取查询的公共参数。可以被其他方法以this.method() 方式调用
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
          //---------------------增删改查 接口 Begin---------------------------------------------------------------
            userSearchMany(){   //1.查询多个
              this.$axios.post(this.userSelectManyUrl,{body:this.getParamData()}).then(res=>{
                var result= res.data
                this.tableData=result.list
              })
            },
            userCount(){    //2.用户查询记录数
              this.$axios.post(this.userSelectCountUrl).then(res=>{
                var result = res.data
                if(result.code==1){
                  this.total=result.list[0]
                }
              })
            },
            userEdit(a,b){       //3.用户修改
               console.log(   this.tableData[a].nickname)
               console.log(a)
               console.log(b)
            },
            userDelete(row){         //4.用户删除
                  //对话框判断
              var param={id:row.id.toString()}
                  this.$axios.post(this.userDeleteOneUrl,{body:param}) //删除
                  this.userCount()              //查询count
                  this.userSearchMany()                 //重新查询数据
            },
        //---------------------增删改查 接口 End---------------------------------------------------------------

        //---------------------------增删改查事件单击Begin---------------------------------------------------------
        addClick(){            //新增按钮

        },
        deleteClick(row){
          this.userRow=row
          this.isDialogShow=true
        },
        resetClick(){          //重置按钮
              this.nicknameInput=''
              this.phoneInput=''
              this.regTimeInput=''
              this.addressInput=''
        },
          searchClick(){        //单击模糊查询多个
             this.userSearchMany()
          },
        //---------------------------增删改查事件单击End---------------------------------------------------------

        handlePageSizeChange(val){     //每页显示多少条
            //将val转为string ，不然传递给后端就是double类型了
           this.pageSize= val.toString()
          this.$axios.post(this.userSelectManyUrl,{body:this.getParamData()}).then(res=>{
            var result= res.data
            this.tableData=result.list
          })
        },
        handlePageNumChange(val){        //页码数
            this.pageNum=val.toString()
          //单机模糊查询多个
          this.$axios.post(this.userSelectManyUrl,{body:this.getParamData()}).then(res=>{
            var result= res.data
            this.tableData=result.list
          })
        },
        keydown(e){           //回车事件
              if(e.keyCode==13){
                this.searchClick()
              }
        }
      },

      //--------------------------------生命周期方法Begin---------------------------------------------------------
      created:function(){
         this.userCount()  //创建vue实例前，查询总记录数
      },
      mounted:function () {
          window.addEventListener('keydown',this.keydown) //添加事件监听
      },
      destroyed:function () {
        window.removeEventListener('keydown',this.keydown,false) //移除事件监听
      }
     //--------------------------------生命周期方法End---------------------------------------------------------
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

.operatex{
  display: flex;
  flex-wrap: nowrap;
}

</style>
