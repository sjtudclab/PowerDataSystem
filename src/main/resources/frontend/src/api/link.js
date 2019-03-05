import instance from './ajax'

const preUrlPath = '';
//用户登录
const userLogin = {
  p: ['post,/login/'],
  r: params => {
    return instance.post(`${preUrlPath}/login/`, params)
  }
};

//文件下载 
const downloadFile = {
  p: ['post,/downloadFile'],
  r: params => {
    return instance.post(`${preUrlPath}/downloadFile/`, params)
  }
}
//获取所有已上传文件
const getFileList = {
  p:['post,/getFileList'],
  r: params => {
    return instance.post(`${preUrlPath}/getFileList/`, params)
  }
}




export {
	userLogin,
	downloadFile,
  getFileList,
}