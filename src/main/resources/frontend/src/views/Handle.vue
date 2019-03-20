<template>
  <el-container>
    <el-header>
      <Navmenu></Navmenu>
    </el-header>
    <el-container>
      <el-aside width='300px'>
        <Sidebar></Sidebar>
      </el-aside>
      <el-container class="container">
        <el-main class="main">
          <el-button>添加任务</el-button>
          <el-button>开始任务</el-button>
          <el-table></el-table>
        </el-main>
      </el-container>

    </el-container>
  </el-container>
</template>

<script>
  import Navmenu from "../components/NavMenu";
  import Sidebar from "../components/Sidebar";

  export default {
    name: "Handle",
    components: {Sidebar, Navmenu}
  }
</script>

<style scoped>
  .el-header {
    padding: 0px;
    margin-bottom: 10px;
  }

  .main {
    padding: 0px;
    margin-left: 10px;
  }

</style>
