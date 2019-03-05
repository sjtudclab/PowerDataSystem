import Vue from 'vue'

//ajax错误处理
const catchError = function(error) {
  if (error.response) {
    switch (error.response.status) {
      case 400:
        Vue.prototype.$message({
          message: error.response.data.message || '请求参数异常',
          type: 'error'
        });
        break;
      case 401:
        Vue.prototype.$message({
          message: error.response.data.message || '密码错误或账号不存在！',
          type: 'warning',
          onClose: function(){
            // location.reload();
          }
        });
        break;
      case 403:
        Vue.prototype.$message({
          message: error.response.data.message || '无访问权限，请联系企业管理员',
          type: 'warning'
        });
        break;
      default:
        Vue.prototype.$message({
          message: error.response.data.message || '服务端异常，请联系技术支持',
          type: 'error'
        });
    }
  }
  return Promise.reject(error);
}
const getCookie = function(cname){
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  }
  return "";
}

const setCookie = function(cname,value,expiredays){
  var exdate = new Date();
  exdate.setDate(exdate.getDate() + expiredays);
  document.cookie = cname + "=" + escape(value) + ((expiredays==null)? "" : ";expiredays=" + exdate.toGMTString());  
}

const delCookie = function(name){
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval = getCookie(name);
  if(cval != ""){
    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
  }
}
const clone = function(obj) {
  var copy = {};
  for (var attr in obj) {
    if(obj[attr] == null){
      copy[attr] = null
    }else{
      copy[attr] = typeof(obj[attr])==='object' ? clone(obj[attr]) : obj[attr];
    }
  }
  return copy;
}


export {
  catchError,
  getCookie,
  setCookie,
  delCookie,
  clone
}