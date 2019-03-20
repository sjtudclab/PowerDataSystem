<template>
  <el-container>
    <el-header>
      <Navmenu></Navmenu>
    </el-header>
    <el-container>
      <el-aside width='300px'>
        <Sidebar></Sidebar>
      </el-aside>
      <el-container>
        <el-main class="main">
          <el-table :data="tableData" >
            <el-table-column
              prop="field"
              label="字段"
              width="180">
            </el-table-column>
            <el-table-column
              prop="type"
              label="数据类型"
              width="180">
            </el-table-column>
            <el-table-column
              prop="logic"
              label="逻辑分类"
              width="180">
            </el-table-column>
            <el-table-column
              prop="unit"
              label="单位"
              width="180">
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>

    </el-container>
  </el-container>
</template>

<script>
  import Navmenu from "../components/NavMenu";
  import Sidebar from "../components/Sidebar";
  import Content from "../components/Content";

  export default {
    name: "MetaData",
    components: {Content, Sidebar, Navmenu},
    data: function () {
      return {
        tableData: [
          {
            'field': 'date',
            'type': 'string',
            'logic': '时间',
            'unit': 'null'

          },
          {
            'field': '线电流Ia',
            'type': 'float',
            'logic': '测点',
            'unit': 'A'

          },
          {
            'field': '线电流Ib',
            'type': 'float',
            'logic': '测点',
            'unit': 'A'

          },
          {
            'field': 'A相电压',
            'type': 'float',
            'logic': '测点',
            'unit': 'V'

          },
          {
            'field': 'B相电压',
            'type': 'float',
            'logic': '测点',
            'unit': 'V'

          },
          {
            'field': 'location',
            'type': 'string',
            'logic': '地点',
            'unit': 'null'

          },

        ]
      }
    }
  }
</script>

<style scoped>
  .el-header {
    padding: 0px;
    margin-bottom: 10px;
  }
</style>
