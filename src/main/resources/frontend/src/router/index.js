import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/Login'
import Index from '@/views/index'
import UploadFile from '@/views/UploadFile'
import Inquire from '@/views/Inquire'
import RemoteDownload from '@/views/RemoteDownload'
import FileSearch from "@/views/FileSearch"
import Handle from '@/views/Handle'
import MetaData from '@/views/MetaData'
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
    },
    {
      path:'/filesearch',
      name:'filesearch',
      component:FileSearch
    },
    {
      path:'/handledata',
      name:'handle',
      component:Handle
    },
    {
      path:'/metadata',
      name:'metadata',
      component:MetaData
    }
  ]
})
