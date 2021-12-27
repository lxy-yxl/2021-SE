import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    login:false,//是否已经登陆
    userName:'',//用户名
    uid:'',//用户id
    avatar:'',//头像url
  },
  mutations: {
    LOGIN:state=>{
      //登陆
      state.login=true;
    },
    LOGOUT:state=>{
      //登出
      state.login=false;
    }
  },
  actions: {
  },
  modules: {
  }
})
