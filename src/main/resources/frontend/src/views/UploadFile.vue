
<template>
  <el-container>
    <el-header> 
      <NavMenu></NavMenu>
    </el-header>
    <el-container>
      <el-aside width='300px'>
          <Sidebar></Sidebar>
      </el-aside>
      <el-container class="container">
        <el-main class="main">
          <UploadFile></UploadFile>
        </el-main>
      </el-container>
        
    </el-container>
  </el-container>
</template>
<script type="text/javascript">
  import NavMenu from '@/components/NavMenu'
  import UploadFile from '@/components/UploadFile'
  import Sidebar from '@/components/Sidebar'
  import {getCookie} from '@/api/util'
  export default {
    name: 'manage',
    components: {NavMenu,UploadFile,Sidebar},
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