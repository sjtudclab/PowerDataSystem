<template>
  <div>
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
  </div>
</template>

<script>
  import * as link from '@/api/link'
  import ShowChart from "./ShowChart";
  import axios from "axios";
  import echarts from "echarts";

  export default {
    name: 'MiniUpload',
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
