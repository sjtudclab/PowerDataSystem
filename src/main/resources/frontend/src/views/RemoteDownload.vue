
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
          <RemoteDownload></RemoteDownload>
        </el-main>
      </el-container>
        
    </el-container>
  </el-container>
</template>
<script type="text/javascript">
  import NavMenu from '@/components/NavMenu'
  import RemoteDownload from '@/components/RemoteDownload'
  import Sidebar from '@/components/Sidebar'
  
  export default {
    name: 'remoteDownload',
    components: {NavMenu,RemoteDownload,Sidebar},
    created(){
      
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
    margin-left: 10px;
  }
  
</style>