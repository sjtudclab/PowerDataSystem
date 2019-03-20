<template>
  <div id="UploadFile">
    <el-container>
    <!--<el-breadcrumb separator-class="el-icon-arrow-right">-->
      <!--<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
      <!--<el-breadcrumb-item>文件上传</el-breadcrumb-item>-->
    <!--</el-breadcrumb>-->
      <el-header>
    <el-upload
      class="upload-demo"
      ref="upload"
      action="http://localhost:8080/api/uploadFile/"
      border="true"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-error="errorUpload"
      :on-success="successUpload"
      :file-list="fileList"
      :auto-upload="false">
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>

    </el-upload>
      </el-header>
      <el-main>
    <div>
      <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <!-- <el-table-column
                prop="date"
                label="日期"
                width="180">
              </el-table-column> -->
        <el-table-column
          prop="fileName"
          label="文件名"
          width="180">
        </el-table-column>
        <!-- <el-table-column
                prop="address"
                label="地址"
                width="180">
              </el-table-column> -->
        <el-table-column
          label="传输进度"
        >
          <template slot-scope="scope">
            <!--<a :href="'/api/downloadFile/' + scope.row.fileID">-->
            <el-progress :percentage="70"></el-progress>

            <!--</a>-->
            <!-- <el-button type="text" size="small">编辑</el-button> -->
          </template>
        </el-table-column>
        <el-table-column
          label="文件大小"
        >
          <template slot-scope="scope">
            <!--<a :href="'/api/parseFile/' + scope.row.fileID">-->
            <span>10M</span>
            <!--</a>-->
          </template>
        </el-table-column>
        <el-table-column
          label="文件类型"
        >
          <template slot-scope="scope">
            <!--<a :href="'/api/parseFile/' + scope.row.fileID">-->
            <span>源数据</span>
            <!--</a>-->
          </template>
        </el-table-column>
      </el-table>
    </div>
      </el-main>
    <!--<el-button type="primary" style="margin-top: 100px" @click="dialogVisibleForShowFunc">点击打开图表</el-button>-->
    <!--<el-button @click="dialogVisibleForShow = false">取 消</el-button>-->
    </el-container>
  </div>
</template>
<script>
  import * as link from '@/api/link'
  import ShowChart from "./ShowChart";
  import axios from "axios";
  import echarts from "echarts";

  export default {
    name: 'UploadFile',
    components: {ShowChart},
    data() {

      return {
        fileList: [],
        tableData: [],
        isShow: true,
        dialogVisibleForShow: false,
        dialogVisibleForSetting: false,
        sheets: [],
        form: {
          name: '可视化表格',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      }
    },
    created() {
      this.fetchData()
    },
    props:['showButton','showTable'],

    methods: {

      fetchData() {
        let that = this
        link.getFileList.r().then((res) => {
          if (res.data.status = "SUCCESS") {
            that.tableData = res.data.files
          } else {
            alert("文件列表加载失败")
          }

        })
      },
      submitUpload() {
        this.$refs.upload.submit()
      },
      successUpload(res, file, fileList) {
        this.tableData = res.files
      },
      errorUpload(err, file, fileList) {
        alert('文件上传失败')
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      showSelect(fileID) {
        let that = this
        this.dialogVisibleForSetting = true;
        axios.get('/api/getBookTitle',
          {
            params: {
              fileID: fileID
            }
          }
        ).then(function (response) {
          that.sheets = response.data
        })
      }
      ,
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },

    }
  }


</script>

<style scoped>
  .upload-demo {
    margin-top: 10px;
  }


</style>
