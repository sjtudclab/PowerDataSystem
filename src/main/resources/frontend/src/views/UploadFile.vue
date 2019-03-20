
<template>
<el-container>
    <el-header>
      <NavMenu></NavMenu>
    </el-header>
    <div>

    <el-container>
      <el-aside width='300px'>
          <Sidebar></Sidebar>
      </el-aside>
        <el-main class="main">
          <MiniUpload></MiniUpload>
        </el-main>

    </el-container>
    </div>
  </el-container>
</template>
<script type="text/javascript">
  import NavMenu from '@/components/NavMenu'
  import UploadFile from '@/components/UploadFile'
  import Sidebar from '@/components/Sidebar'
  import {getCookie} from '@/api/util'
  import MiniUpload from "../components/MiniUpload";
  export default {
    name: 'manage',
    components: {MiniUpload, NavMenu,UploadFile,Sidebar},
    created(){
      if(getCookie('username') == ""){
        this.$router.push({path:"/login"})
      }
    }
  }
</script>
<style type="text/css" scoped>
  .el-header{
    padding: 0px;
    margin-bottom: 10px;
  }
  .main{
    padding: 0px;
    padding-left: 10px;
  }

</style>
