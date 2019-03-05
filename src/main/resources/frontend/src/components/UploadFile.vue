<template>
    <div id="UploadFile">
    	<el-breadcrumb separator-class="el-icon-arrow-right">
    	  <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    	  <el-breadcrumb-item>文件上传</el-breadcrumb-item>
    	</el-breadcrumb>
    	<el-upload
    	  class="upload-demo"
    	  ref="upload"
    	  action="http://localhost:8080/uploadFile/"
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
			           label="操作"
			           >
			           <template slot-scope="scope">
			             <a :href="'/api/downloadFile/' + scope.row.fileID"><el-button type="text" size="small">下载</el-button></a>
			             <!-- <el-button type="text" size="small">编辑</el-button> -->
			           </template>
			       </el-table-column>
			</el-table>
		</div>
    </div>
</template>
<script>
	import * as link from '@/api/link'
	export default {
		name: 'UploadFile',
		data(){
				
			return {
				fileList: [],
				tableData: [],
			}
		},
		created(){
			this.fetchData()
		},

		methods:{

			fetchData(){
				let that = this
				link.getFileList.r().then((res) => {
					if(res.data.status = "SUCCESS"){
						that.tableData = res.data.files
					}else{
						alert("文件列表加载失败")
					}
					
				})
			},
			submitUpload(){
				this.$refs.upload.submit()
			},
			successUpload(res, file, fileList){
				this.tableData = res.files
			},
			errorUpload(err,file,fileList){
				alert('文件上传失败')
			},
			handleRemove(file, fileList) {
	            console.log(file, fileList);
	        },
	        handlePreview(file) {
	           console.log(file);
	        },
	        
		}
	}
</script>
<style scoped>
	.upload-demo {
		margin-top: 10px;
	}
	
	
</style>