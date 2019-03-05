<template>
	<div id="InquireData">
		<el-breadcrumb separator-class="el-icon-arrow-right">
    	  <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    	  <el-breadcrumb-item>查询</el-breadcrumb-item>
    	</el-breadcrumb>
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
				  	  placeholder="查询对象"
				  	  suffix-icon="el-icon-date"
				  	  v-model="input7">
				  	</el-input>
				  </el-col>
				  <el-col :span="5">
				  	<el-input
				  	  placeholder="列表"
				  	  suffix-icon="el-icon-date"
				  	  v-model="input8">
				  	</el-input>
				  </el-col>
				  
			</el-row>
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
				input6: '',
			    input7: '',
			    input8: '',
			    input9: ''
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