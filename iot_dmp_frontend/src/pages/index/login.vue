<template>
  <div class="container">
    <h2>高菲物联网平台</h2>
    <form method="post" id="formId">
      <div class="form-input">
        <el-input v-model="username" placeholder="用户名" required="required" ></el-input>
      </div>
      <div class="form-input">
        <el-input v-model="password" placeholder="密码" required="required" type="password"></el-input>
      </div>
      <div class="form-button">
        <el-button type="success" @click="loginClick()">登录</el-button>
        <el-button type="primary" @click="registerClick">注册</el-button>
      </div>
    </form>

    <dialog_login :sonIsDialogShow.sync="isDialogShow"  :dialogPrompt="dialogParam"></dialog_login>   <!--msg 父组件传递给子组件-->
  </div>
</template>

<script>
  import dialog_login from '@/components/dialog'
  export default {
    components:{
      dialog_login   //子组件 是dialog_login
    },
    data() {    //data() 内部的return 的input 是该组件内部的局部变量.  在el-input 标签里进行了双向绑定. 这里要定义input变量的初始值，不然报错
      return {
        username: '',
        password:'',
        isDialogShow:false,
        dialogParam:''
      }
    },

    methods:{
      //登录单击事件
      loginClick(){
        var data={
            username: this.username,
            password :this.password
        }
        this.$axios.post("http://localhost:8080/login",{        //url 提取出来作为变量，并根据三大环境动态切换？
          body:JSON.stringify(data)   //传递json数据到后台
        }).then(res=>{
          var result = res.data.result;

            if(result==1){
              this.$router.push({path:'/manage'}); //路由跳转到manage管理页面


            }else if(result==2){
              this.isDialogShow=true; //密码输入错误，弹出对话框
              this.setDialogParam("用户名或密码错误");
              this.username=''; //清空输入框用户名密码
              this.password='';


            }else {
              //例如返回值为-1 及非约定的业务逻辑值
              this.isDialogShow=true;
              this.setDialogParam("服务器异常");
            }
        }).catch(err=>{
          //网络异常，同样调用对话框组件
          this.isDialogShow=true;
          this.setDialogParam("网络异常");
        })
      },
      //注册单击事件
      registerClick(){
        this.$router.push({path:'/register'})
      },
      //设置对话框参数
      setDialogParam(param){
        this.dialogParam=param;
      }

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .container{
    width:100%;
    height:40%;
    /*background-color: rgba(212,242,231,0.5);*/
    margin: auto;
    text-align: center;
    padding-top: 200px;

  }
  #formId {
    width:30%;
    heigth:100%;
    margin: auto;
    padding: auto;
  }
  .form-input{
    margin: 10px;
  }
  .form-button{
    margin: 20px;
  }
</style>
