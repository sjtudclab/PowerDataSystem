import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/Login'
import Index from '@/views/index'
import UploadFile from '@/views/UploadFile'
import Inquire from '@/views/Inquire'
import RemoteDownload from '@/views/RemoteDownload'

Vue.use(Router)

export default new Router({
  routes: [
    
    {
        path: '/',
        name: 'index',
        component: Index
    },
    {
    	path: '/login',
    	name: 'login',
    	component: Login
    },
    {
    	path:'/uploadfile',
    	name: 'uploadfile',
    	component: UploadFile
    },
    {
    	path: '/inquire',
    	name: 'inquire',
    	component: Inquire
    },
    {
    	path: '/remotedownload',
    	name: 'remotedownload',
    	component: RemoteDownload
    }
  ]
})
