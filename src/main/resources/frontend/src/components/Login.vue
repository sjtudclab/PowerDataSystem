<template>
	<div class="login-wrap">
		<Background></Background>
		<div id="login">
			<h2 class="login-title">用户登录</h2>
			<el-form :label-position="labelPosition" label-width="80px" :model="login" :rules="rules" ref="login">
			  <el-form-item label="用户名" prop="username">
			    <el-input v-model="login.username" clearable></el-input>
			  </el-form-item>
			  <el-form-item label="密码" prop="password">
			    <el-input v-model="login.password" type="password"></el-input>
			  </el-form-item>
			  <!--  <el-row type="flex" justify="space-between">
			  	<el-col :span="12">
			  		<router-link to="/forgetPwd" class="text">忘记密码</router-link>
			  	</el-col>
			  	<el-col :span="12" class="text-right">	
			  		<span class="text">没有账号 <router-link to="/Register" class="text">前往注册</router-link></span>
			  	</el-col>
			  </el-row> -->
			  <el-form-item>
			      <el-button size="medium" @click="onSubmit('login')">登录</el-button>
			  </el-form-item>
			</el-form>
		</div>

	</div>
</template>
<script>
	import Background from '@/components/Background'
	import * as lgn from '@/api/link'
	import {setCookie,getCookie,delCookie} from '@/api/util'
	import Vue from 'vue'
	export default {
		name: 'Login',
		components:{Background},
		data() {
	      return {
	        labelPosition: 'top',
	        login: {
	          username: '',
	          password: '',
	        },
	        rules:{
	        	username:[
	        		{required:true,message:'请输入用户名',trigger:'blur'},
	        		{min:1,message:'用户名不能为空',trigger:'blur'}
	        	],
	        	password:[
	        		{required:true,message:'请输入密码',trigger:'blur'},
	        		{min:1,message:'密码不能为空',trigger:'blur'}
	        	]
	        }
	      }
		},
		methods: {
	      onSubmit(formName) {
  			let that = this
	      	this.$refs[formName].validate((valid)=>{
	      		if(valid){
  			        lgn.userLogin.r(that.login).then((res)=>{
  			        	if(res.data.status == 'SUCCESS'){
  			        		let expireDays = 1000*60*60
  			        		setCookie('username',that.login.username,expireDays)
  			        		this.$router.push({path: '/'})
  			        	}else{
  			        		alert(res.data.message)
  			        	}
  			        });

	      		}else{
	      			Vue.prototype.$message({
	      				message: '格式错误',
	      				type:'error'
	      			});
	      			return false;
	      		}
	      	})
	      }
	    }
	}
</script>



<style scoped>
	.login-wrap >>> .el-form-item__label {
		margin-bottom: -7px;
		padding-bottom: 0px;
	}
	.login-wrap >>> .el-form-item:first-child{
		margin-bottom: 10px;
	}
	.login-wrap >>> .el-form-item:not(:first-child){
		margin-bottom: 15px;
	}
	.login-wrap >>> .el-row {
		margin-bottom: 10px;
	}
	.login-wrap >>> .el-button{
		width: 100%;
	}
	.login-wrap >>> label {
		font-size: 3px;
		font-weight: 200;
	}
	#login {
	    background-color: #fff;
	    width: 300px;
	    height: 270px;
	    position: absolute;
	    top: 25%;
	    left: 40%;
	    padding: 20px;
	}
	.login-title{
		font-size: 13px;
		font-weight: 400;
	}
	/*.login-wrap >>> .text-right{
		text-align: right;
	}*/
	.login-wrap >>> .text{
		font-size: 0.5em;
		font-weight: 50px;
		text-decoration-line: none;
	}
	
</style>