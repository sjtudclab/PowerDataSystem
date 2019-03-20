<template>
	<div id="InquireData">
		<el-breadcrumb separator-class="el-icon-arrow-right">
    	  <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    	  <el-breadcrumb-item>查询</el-breadcrumb-item>
    	</el-breadcrumb>
    	<div id="input_area">
        <el-date-picker
          v-model="value6"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
			<el-row>
				<el-button>查询</el-button>	
			</el-row>
		</div>
		<div id="result_area">
			
		</div>
	</div>
</template>

<script>
	import * as link from '@/api/link'
	export default {
		name: "InquireData",
		data(){
			return {
				value6:'',
			}
		},
		create(){

		},
		methods: {

		}
	}
</script>
<style scoped>
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
