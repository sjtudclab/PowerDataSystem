<template>
	<div id="RemoteDownload">
		<el-breadcrumb separator-class="el-icon-arrow-right">
    	  <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    	  <el-breadcrumb-item>远端下载</el-breadcrumb-item>
    	</el-breadcrumb>
    	<div id="input_area">
			<el-row :gutter="10"> 
				  <el-col :span="5">
					<el-input
					  placeholder="请输入内容"
					  suffix-icon="el-icon-date"
					  v-model="input6">
					</el-input>
				  </el-col>
				  <el-col :span="5">
					<el-button>开始传输</el-button>
				  </el-col>
			
				  
			</el-row>
			
		</div>
		<div id="result_area">
			
		</div>
	</div>
</template>

<script>
	import * as link from '@/api/link'
	export default {
		name: "RemoteDownload",
		data(){
			return {
				input6: ''
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
