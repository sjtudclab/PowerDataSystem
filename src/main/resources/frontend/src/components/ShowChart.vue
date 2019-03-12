<template>
  <el-button type="text" size="small" v-on:click="clicked">显示图表</el-button>
</template>

<script>
  export default {
    name: "ShowChart",
    methods: {
      clicked() {
        alert(scope.row.fileID);
      }
    }
  }
</script>

<style scoped>

</style>
