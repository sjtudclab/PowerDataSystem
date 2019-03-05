<template>
	<div id="aside">
		<el-menu
			  
		      class="el-menu-vertical-demo"
		      background-color="#545c64"
		      text-color="#fff"
		      active-text-color="#ffd04b"
			  :router="isActive"
		      >
		      
		      <el-menu-item index="/uploadfile">
		        <i class="el-icon-menu"></i>
		        <span slot="title">上传文件</span>
		      </el-menu-item>
		      <el-menu-item index="/inquire">
		        <i class="el-icon-document"></i>
		        <span slot="title">查询</span>
		      </el-menu-item>
		      <el-menu-item index="/remotedownload">
		        <i class="el-icon-setting"></i>
		        <span slot="title">远端下载</span>
		      </el-menu-item>
		</el-menu>
	</div>
</template>
<script type="text/javascript">
	import * as lgn from '@/api/link'
	import {setCookie,getCookie,delCookie} from '@/api/util'
	export default{
		name: 'sidebar',
		data(){
			return{
				isActive: true,
				
			}
		},
		created(){
			
		},
	}
</script>
<style type="text/css" scoped>
	a{
		text-decoration:none
	}
	.aside{
		width: 100%;
		min-height: calc(100vh - 60px);
		background-color: rgb(244,251,254);
		overflow: hidden;
	}

	
</style>