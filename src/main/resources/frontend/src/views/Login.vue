<template>
	<el-container>
		<el-header>	
			<NavMenu></NavMenu>
		</el-header>
		<el-container>
		    <Login></Login>
		</el-container>
	</el-container>
</template>
<script type="text/javascript">
	import NavMenu from '@/components/NavMenu'
	import Login from '@/components/Login'
	import {getCookie} from '@/api/util'
	export default {
		name: 'manage',
		components: {NavMenu,Login},
		create(){
			if(getCookie('username') != ''){
				this.$router.push({path: '/'})
			}
  			    
		}
	}
</script>
<style type="text/css" scoped>
	.el-header{
		padding: 0px;
	}
</style>