<template>
  <div id="UploadFile">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>文件上传</el-breadcrumb-item>
    </el-breadcrumb>
    <el-upload
      class="upload-demo"
      ref="upload"
      action="http://localhost:8080/api/uploadFile/"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-error="errorUpload"
      :on-success="successUpload"
      :file-list="fileList"
      :auto-upload="false">
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>

    </el-upload>
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
          label="操作1"
        >
          <template slot-scope="scope">
            <a :href="'/api/downloadFile/' + scope.row.fileID">
              <el-button type="text" size="small">下载</el-button>
            </a>
            <!-- <el-button type="text" size="small">编辑</el-button> -->
          </template>
        </el-table-column>
        <el-table-column
          label="操作2"
        >
          <template slot-scope="scope">
            <!--<a :href="'/api/parseFile/' + scope.row.fileID">-->
            <el-button type="text" size="small" v-on:click="showSelect(scope.row.fileID)">配置图表</el-button>
            <!--</a>-->
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-button type="primary" style="margin-top: 100px" @click="dialogVisibleForShowFunc">点击打开图表</el-button>
    <el-dialog
      :title="form.name"
      :visible.sync="dialogVisibleForShow"
      :before-close="handleClose"
      width="80%">

      <div id="chart1" v-show="isShow"
           style="height: 400px;width: 800px;margin-top: auto;margin-left: auto"></div>
      <span slot="footer" class="dialog-footer">
    <!--<el-button @click="dialogVisibleForShow = false">取 消</el-button>-->
    <el-button type="primary" @click="dialogVisibleForShow = false">确 定</el-button>
  </span>
    </el-dialog>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisibleForSetting"
      width="50%"
      :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="图表名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="工作簿">
          <el-select v-model="form.region" placeholder="请选择sheet">
            <el-option v-for="(a,sheetName) in sheets" :label="sheetName" :value="sheetName"></el-option>
            <!--<el-option label="区域一" value="shanghai"></el-option>-->
            <!--<el-option label="区域二" value="beijing"></el-option>-->
          </el-select>
        </el-form-item>
        <el-form-item label="方案">
          <el-radio-group v-model="form.resource">
            <el-radio label="方案1" value="1"></el-radio>
            <el-radio label="方案2" value="2"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisibleForSetting = false">取 消</el-button>
    <el-button type="primary" @click="onSubmitSetting">确 定</el-button>
  </span>
    </el-dialog>
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
      initChart1() {
        var option1 = {
          title: {
            text: '折线图简单实例'
          },
          xAxis: {
            name: '第n次',
            type: 'category',
            data: [1, 2, 3, 4, 5],
            boundaryGap: false
          },
          yAxis: {
            name: 'y轴(分数)'
          },
          series: [
            {
              name: 'a同学',
              data: [100, 90, 50, 40, 30],
              type: 'line',
              // areaStyle:{}
            },
            {
              name: 'b同学',
              data: [30, 80, 50, 70, 100],
              type: 'line',
              // areaStyle:{}

            }
          ],
          legend: {
            data: ['a同学', 'b同学']
          },
          tooltip: {
            trigger: 'axis'
          }
        };
        var option2 = {
          legend: {},
          tooltip: {},
          dataset: {
            // 提供一份数据。
            dimensions: ['time', 'B相电压', '反向有功电度', 'BC线电压', '正向无功电度', '线电流Ic', 'CA线电压', '线电流Ia', '线电流Ib', 'AB线电压', 'C相电压', '正向有功电度', '反向无功电度', 'A相电压'],
            source:
              [
                {
                  'B相电压': 237.617462158203,
                  '反向有功电度': 37.439998626709,
                  'BC线电压': 410.058868408203,
                  '正向无功电度': 11.3400001525879,
                  '线电流Ic': 0.0,
                  'CA线电压': 409.687774658203,
                  '线电流Ia': 0.0,
                  '线电流Ib': 0.0,
                  'AB线电压': 410.058868408203,
                  'C相电压': 237.187774658203,
                  '正向有功电度': 370.819976806641,
                  '反向无功电度': 844.139953613281,
                  'A相电压': 237.187774658203,
                  'time': 20181001
                }, {
                'B相电压': 237.617462158203,
                '反向有功电度': 37.439998626709,
                'BC线电压': 410.058868408203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 410.429962158203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 410.429962158203,
                'C相电压': 237.187774658203,
                '正向有功电度': 370.819976806641,
                '反向无功电度': 844.139953613281,
                'A相电压': 237.832305908203,
                'time': 20181002
              }, {
                'B相电压': 237.832305908203,
                '反向有功电度': 37.4500007629395,
                'BC线电压': 410.429962158203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 410.801055908203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 410.801055908203,
                'C相电压': 237.617462158203,
                '正向有功电度': 370.819976806641,
                '反向无功电度': 844.139953613281,
                'A相电压': 238.047149658203,
                'time': 20181003
              }, {
                'B相电压': 237.187774658203,
                '反向有功电度': 37.4500007629395,
                'BC线电压': 409.687774658203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 409.687774658203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 410.058868408203,
                'C相电压': 236.972930908203,
                '正向有功电度': 370.819976806641,
                '反向无功电度': 844.149963378906,
                'A相电压': 237.402618408203,
                'time': 20181004
              }, {
                'B相电压': 236.328399658203,
                '反向有功电度': 37.4500007629395,
                'BC线电压': 407.832305908203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 407.832305908203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 408.203399658203,
                'C相电压': 235.898712158203,
                '正向有功电度': 370.829986572266,
                '反向无功电度': 844.149963378906,
                'A相电压': 236.328399658203,
                'time': 20181005
              }, {
                'B相电压': 238.047149658203,
                '反向有功电度': 37.4500007629395,
                'BC线电压': 410.429962158203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 18.1640777587891,
                'CA线电压': 410.058868408203,
                '线电流Ia': 7.0312557220459,
                '线电流Ib': 37.5000305175781,
                'AB线电压': 410.801055908203,
                'C相电压': 237.402618408203,
                '正向有功电度': 370.829986572266,
                '反向无功电度': 844.149963378906,
                'A相电压': 237.617462158203,
                'time': 20181006
              }, {
                'B相电压': 230.742462158203,
                '反向有功电度': 37.4599990844727,
                'BC线电压': 398.183837890625,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 70.3125610351562,
                'CA线电压': 397.812744140625,
                '线电流Ia': 70.8984985351562,
                '线电流Ib': 70.3125610351562,
                'AB线电压': 398.183837890625,
                'C相电压': 230.312774658203,
                '正向有功电度': 370.829986572266,
                '反向无功电度': 844.169982910156,
                'A相电压': 230.527618408203,
                'time': 20181007
              }, {
                'B相电压': 236.328399658203,
                '反向有功电度': 37.4599990844727,
                'BC线电压': 407.461212158203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 407.090118408203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 407.832305908203,
                'C相电压': 235.683868408203,
                '正向有功电度': 371.130004882813,
                '反向无功电度': 845.609985351563,
                'A相电压': 235.898712158203,
                'time': 20181008
              }, {
                'B相电压': 237.187774658203,
                '反向有功电度': 37.4599990844727,
                'BC线电压': 408.945587158203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 102.53914642334,
                'CA线电压': 408.574493408203,
                '线电流Ia': 101.95320892334,
                '线电流Ib': 103.12508392334,
                'AB线电压': 409.316680908203,
                'C相电压': 236.328399658203,
                '正向有功电度': 371.130004882813,
                '反向无功电度': 845.609985351563,
                'A相电压': 236.543243408203,
                'time': 20181009
              }, {
                'B相电压': 236.113555908203,
                '反向有功电度': 37.4599990844727,
                'BC线电压': 407.090118408203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 45.1172256469727,
                'CA线电压': 407.090118408203,
                '线电流Ia': 41.0156593322754,
                '线电流Ib': 42.7734718322754,
                'AB线电压': 407.832305908203,
                'C相电压': 235.469024658203,
                '正向有功电度': 371.619995117188,
                '反向无功电度': 847.719970703125,
                'A相电压': 236.113555908203,
                'time': 20181010
              }, {
                'B相电压': 236.972930908203,
                '反向有功电度': 37.4799995422363,
                'BC线电压': 408.574493408203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 43.9453468322754,
                'CA线电压': 408.203399658203,
                '线电流Ia': 39.8437805175781,
                '线电流Ib': 41.6015968322754,
                'AB线电压': 408.945587158203,
                'C相电压': 235.898712158203,
                '正向有功电度': 371.619995117188,
                '反向无功电度': 847.859985351563,
                'A相电压': 236.758087158203,
                'time': 20181011
              }, {
                'B相电压': 231.386993408203,
                '反向有功电度': 37.5200004577637,
                'BC线电压': 398.926025390625,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 398.926025390625,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 399.297119140625,
                'C相电压': 230.742462158203,
                '正向有功电度': 371.630004882813,
                '反向无功电度': 848.469970703125,
                'A相电压': 231.172149658203,
                'time': 20181012
              }, {
                'B相电压': 230.957305908203,
                '反向有功电度': 37.5200004577637,
                'BC线电压': 398.926025390625,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 39.2578430175781,
                'CA线电压': 398.926025390625,
                '线电流Ia': 38.6719055175781,
                '线电流Ib': 39.8437805175781,
                'AB线电压': 398.926025390625,
                'C相电压': 230.742462158203,
                '正向有功电度': 371.630004882813,
                '反向无功电度': 848.469970703125,
                'A相电压': 231.172149658203,
                'time': 20181013
              }, {
                'B相电压': 231.386993408203,
                '反向有功电度': 37.5400009155273,
                'BC线电压': 399.297119140625,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 399.297119140625,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 399.297119140625,
                'C相电压': 231.172149658203,
                '正向有功电度': 371.639984130859,
                '反向无功电度': 848.859985351563,
                'A相电压': 231.172149658203,
                'time': 20181014
              }, {
                'B相电压': 236.113555908203,
                '反向有功电度': 37.5400009155273,
                'BC线电压': 407.461212158203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 42.1875343322754,
                'CA线电压': 407.461212158203,
                '线电流Ia': 43.3594093322754,
                '线电流Ib': 47.4609756469727,
                'AB线电压': 407.832305908203,
                'C相电压': 235.683868408203,
                '正向有功电度': 371.639984130859,
                '反向无功电度': 848.859985351563,
                'A相电压': 236.113555908203,
                'time': 20181015
              }, {
                'B相电压': 237.402618408203,
                '反向有功电度': 37.5699996948242,
                'BC线电压': 409.687774658203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 42.1875343322754,
                'CA线电压': 409.316680908203,
                '线电流Ia': 41.6015968322754,
                '线电流Ib': 41.6015968322754,
                'AB线电压': 409.687774658203,
                'C相电压': 236.972930908203,
                '正向有功电度': 371.649993896484,
                '反向无功电度': 849.649963378906,
                'A相电压': 237.187774658203,
                'time': 20181016
              }, {
                'B相电压': 237.617462158203,
                '反向有功电度': 37.629997253418,
                'BC线电压': 410.058868408203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 42.1875343322754,
                'CA线电压': 410.058868408203,
                '线电流Ia': 41.6015968322754,
                '线电流Ib': 43.3594093322754,
                'AB线电压': 410.429962158203,
                'C相电压': 237.187774658203,
                '正向有功电度': 371.660003662109,
                '反向无功电度': 850.519958496094,
                'A相电压': 237.617462158203,
                'time': 20181017
              }, {
                'B相电压': 237.402618408203,
                '反向有功电度': 37.6499977111816,
                'BC线电压': 409.687774658203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 409.687774658203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 410.058868408203,
                'C相电压': 236.972930908203,
                '正向有功电度': 371.669982910156,
                '反向无功电度': 850.719970703125,
                'A相电压': 237.402618408203,
                'time': 20181018
              }, {
                'B相电压': 236.113555908203,
                '反向有功电度': 37.6499977111816,
                'BC线电压': 407.461212158203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 407.461212158203,
                '线电流Ia': 0.0,
                '线电流Ib': 2.92968988418579,
                'AB线电压': 407.832305908203,
                'C相电压': 235.898712158203,
                '正向有功电度': 371.669982910156,
                '反向无功电度': 850.72998046875,
                'A相电压': 235.898712158203,
                'time': 20181019
              }, {
                'B相电压': 236.972930908203,
                '反向有功电度': 37.6499977111816,
                'BC线电压': 409.316680908203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 42.1875343322754,
                'CA线电压': 408.945587158203,
                '线电流Ia': 42.1875343322754,
                '线电流Ib': 42.7734718322754,
                'AB线电压': 409.316680908203,
                'C相电压': 236.758087158203,
                '正向有功电度': 371.669982910156,
                '反向无功电度': 850.72998046875,
                'A相电压': 236.758087158203,
                'time': 20181020
              }, {
                'B相电压': 237.617462158203,
                '反向有功电度': 37.7000007629395,
                'BC线电压': 410.058868408203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 409.687774658203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 410.058868408203,
                'C相电压': 237.187774658203,
                '正向有功电度': 371.679992675781,
                '反向无功电度': 851.630004882813,
                'A相电压': 237.187774658203,
                'time': 20181021
              }, {
                'B相电压': 238.261993408203,
                '反向有功电度': 37.7000007629395,
                'BC线电压': 411.172149658203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 0.0,
                'CA线电压': 410.801055908203,
                '线电流Ia': 0.0,
                '线电流Ib': 0.0,
                'AB线电压': 411.172149658203,
                'C相电压': 237.832305908203,
                '正向有功电度': 371.679992675781,
                '反向无功电度': 851.630004882813,
                'A相电压': 237.832305908203,
                'time': 20181022
              }, {
                'B相电压': 236.543243408203,
                '反向有功电度': 37.7000007629395,
                'BC线电压': 408.574493408203,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': 45.1172256469727,
                'CA线电压': 408.574493408203,
                '线电流Ia': 41.6015968322754,
                '线电流Ib': 44.5312881469727,
                'AB线电压': 408.574493408203,
                'C相电压': 236.328399658203,
                '正向有功电度': 371.679992675781,
                '反向无功电度': 851.630004882813,
                'A相电压': 236.758087158203,
                'time': 20181023
              }, {
                'B相电压': null,
                '反向有功电度': 37.7799987792969,
                'BC线电压': null,
                '正向无功电度': 11.3400001525879,
                '线电流Ic': null,
                'CA线电压': null,
                '线电流Ia': null,
                '线电流Ib': null,
                'AB线电压': null,
                'C相电压': null,
                '正向有功电度': 371.699981689453,
                '反向无功电度': 852.859985351563,
                'A相电压': null,
                'time': 20181024
              }]

          },
          // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
          xAxis: {type: 'category'},
          // 声明一个 Y 轴，数值轴。
          yAxis: {},
          // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
          series: [
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'}
          ]
        };

        var chart1 = echarts.init(document.getElementById('chart1'));
        console.log(this.form.resource);
        if (this.form.resource === "方案1") {
          chart1.setOption(option1);
        }
        else if (this.form.resource==="方案2") {
          chart1.setOption(option2)
        }
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      onSubmitSetting() {
        this.dialogVisibleForSetting = false
      },
      sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  },
      async dialogVisibleForShowFunc(){
        this.dialogVisibleForShow = true;
        await this.sleep(2000);
        this.initChart1()
      }

    }
  }


</script>

<style scoped>
  .upload-demo {
    margin-top: 10px;
  }


</style>
