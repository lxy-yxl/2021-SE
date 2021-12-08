<template>
<div  style="width: 100%; height: 100vh;background-color: #9AC8E2; overflow: hidden">
<div style="width: 400px; margin: 150px auto">
  <div style="color :#576690; font-sizr:30px; text-align:center; padding:30px 0" >欢迎登录</div>
  <el-form ref="form" :model="form" size="normal">
    <el-form-item>
      <el-input  v-model="form.username">
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-input v-model="form.password" show-password>
      </el-input>
    </el-form-item>
    <el-form-item>
    <el-button style="width:100% " type="primary" @click="login">登录</el-button>
    </el-form-item>
  </el-form>
</div>
</div>
</template>

<script>
import {ElMessage} from "element-plus";
import request from "../utils/request";

export default {
  name: "login",
  data(){
    return {
      form: {}
    }
  },
  methods:{
    login(){
      request.post("/api/user/login",this.form).then(res=>{
        if(res.code === '0'){
          ElMessage({
            type: 'success',
            message:'登录成功',
          })
          this.$router.push("/")   //登录成功之后进行页面跳转
        }else{
          ElMessage({
            type: 'error',
            message: res.msg
          })
        }

      })
    }
  }
}
</script>

<style scoped>

</style>