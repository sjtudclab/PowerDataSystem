import axios from 'axios'
import * as util from './util'

const instance = axios.create({
  baseURL: '/api',//后台服务基础连接
  timeout: 0
});

//错误处理
instance.interceptors.response.use(function(response) {
  return response;
},util.catchError);

export default instance