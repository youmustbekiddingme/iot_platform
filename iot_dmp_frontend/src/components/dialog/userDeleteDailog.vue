<template>
    <el-dialog
      title="提示"
      :visible.sync=sonIsDialogShow
      :before-close="cancelPanel"
      width="30%">
      <span>{{dialogPrompt}}</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="cancelPanel">取 消</el-button>
    <el-button type="primary" @click="confirmPanel">确 定</el-button>
  </span>
    </el-dialog>

</template>

<script>
  export default {
    props:{
      sonIsDialogShow:{type:Boolean,default:false},   //父组件传递过来，用来控制dialog 是否显示
      dialogPrompt:'',
      userRow:''  //父组件的需要删除的行，从父组件传递过来
    },
    data() {
      return {
      }
    },

    methods: {
      confirmPanel() {
         this.$parent.userDelete(this.userRow)  //子组件调用父组件的方法，执行删除
          this.$emit('update:sonIsDialogShow', false)
      },
      cancelPanel(done) {
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
