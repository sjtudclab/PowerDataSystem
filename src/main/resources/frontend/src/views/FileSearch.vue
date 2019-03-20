
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
          <div id="input_area">
            <el-row :gutter="10">
              <el-col :span="5">
                <el-input
                  placeholder="时间范围"
                  suffix-icon="el-icon-date"
                  v-model="input6">
                </el-input>
              </el-col>
              <el-col :span="5">
                <el-input
                  placeholder="上传人"
                  suffix-icon="el-icon-date"
                  v-model="input7">
                </el-input>
              </el-col>
              <el-col :span="5">
                <el-input
                  placeholder="文件名"
                  suffix-icon="el-icon-date"
                  v-model="input8">
                </el-input>
              </el-col>

            </el-row>
            <el-row>
              <el-button>查询</el-button>
            </el-row>
          </div>
          <FileTable></FileTable>
        </el-main>
      </el-container>

    </el-container>
  </el-container>
</template>
<script type="text/javascript">
  import NavMenu from '@/components/NavMenu'
  import InquireData from '@/components/InquireData'
  import Sidebar from '@/components/Sidebar'
  import UploadFile from "../components/UploadFile";
  import FileTable from "../components/FileTable";

  export default {
    name: 'inquire',
    components: {FileTable, UploadFile, NavMenu,InquireData,Sidebar},
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
  #input_area {
    margin-top: 10px;
    overflow: hidden;
  }
  #result_area {
    margin-top: 10px;
  }
  .el-row {
    margin-bottom: 10px;
  }

</style>
