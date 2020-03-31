<template>
  <!--:visible.sync=sonIsDialogShow  接收父组件传递的值决定是否显示
   :visible 是个样式 等价于v-bind:visible=  ；样式传值 ；  {{}}标签外传值 -->
    <el-dialog
      title="提示"
      :visible.sync=sonIsDialogShow
      :before-close="handleClose"
      width="30%">
      <span>{{dialogPrompt}}</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="hidePanel">取 消</el-button>
    <el-button type="primary" @click="hidePanel">确 定</el-button>
  </span>
    </el-dialog>

</template>

<script>
  export default {
    props:{
      sonIsDialogShow:{type:Boolean,default:false},   //父组件传递过来，用来控制dialog 是否显示
      dialogPrompt:''
    },
    data() {
      return {
        dialogVisibleX: true   //用不到了
      }
    },

    methods: {
      hidePanel() {
        // 子组件将sonIsDialogShow的值false传递给父组件更新
        this.$emit('update:sonIsDialogShow', false)
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            this.$emit('update:sonIsDialogShow', false)
            done();
          })
          .catch(_ => {});
      }

    }
  };
</script>

<style scoped>

</style>
