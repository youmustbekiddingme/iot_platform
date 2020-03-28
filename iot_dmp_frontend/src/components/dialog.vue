<template>
  <div>
    <el-button type="text" @click="dialogVisibleX = true">点击打开 Dialog</el-button>

    <el-dialog
      title="提示"
      :visible.sync="dialogVisibleX"
      width="30%"
      :before-close="handleClose">
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisibleX = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisibleX = false">确 定</el-button>
  </span>
    </el-dialog>
    msg:{{msg}}<br>
    dialogVisibleX:{{dialogVisibleX}}
  </div>
</template>

<script>
  export default {
    props:{
      msg:{type:Boolean,default:false}

    },

    data() {
      return {
        dialogVisibleX: this.msg    //data赋值只能在这里赋值一次  ,dialogVisibleX:
      }
    },

    watch:{
      msg (val){
        this.dialogVisibleX=val
      }
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      }
    }
  };
</script>

<style scoped>

</style>
