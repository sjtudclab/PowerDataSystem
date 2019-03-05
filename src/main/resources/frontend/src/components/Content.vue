<template>
    <div id="Content">
    	
    </div>
</template>
<script>
	import * as content from '@/api/link'
	import {setCookie,getCookie,delCookie} from '@/api/util'
	export default {
		name: 'Content',
		data(){
				
			return {
				username: '',
				isAdmin: false,
				
			}
		},
		created(){
			this.username = getCookie('username')
			if (this.username == 'admin') {
				this.isAdmin = true
			}
		},

		methods:{
			
		}
	}
</script>
<style scoped>
	
	
	
</style>