<template>
  <div class="container">
    <h2>IOT物联网平台</h2>
    <form method="post" id="formId">
      <div class="form-input">
        <el-input v-model="input1" placeholder="用户名" required="required"></el-input>
      </div>
      <div class="form-input">
        <el-input v-model="input2" placeholder="密码" required="required"></el-input>
      </div>
      <div class="form-button">
        <el-button type="success" @click="loginClick()">登录</el-button>
        <el-button type="primary">注册</el-button>
      </div>
    </form>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        //data() 内部的return 的input 是该组件内部的局部变量.  在el-input 标签里进行了双向绑定. 这里要定义input变量的初始值，不然报错
        input1: '',
        input2:''
      }
    },
    methods:{
      loginClick(){
        this.$axios.get("http://localhost:8080/login").then(res=>{
          if(res.data.result==1){

            this.$router.push({path:'/device/'});

          }

        })

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
