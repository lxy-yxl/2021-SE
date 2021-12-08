<template>
<div  style="width: 100%; height: 100vh;background-color: #9AC8E2; overflow: hidden">
<div style="width: 400px; margin: 150px auto">
  <div style="color :#576690; font-sizr:30px; text-align:center; padding:30px 0" >欢迎注册</div>
  <el-form ref="form" :model="form" size="normal" :rules="rules">
    <el-form-item>
      <el-input  v-model="form.username">
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-input v-model="form.password" show-password>
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-input v-model="form.confirm" show-password>
      </el-input>
    </el-form-item>
    <el-form-item>
    <el-button style="width:100% " type="primary" @click="register">注册</el-button>
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
      form: {},
      rules:{
        name:[
          {required: true ,message:"请输入用户名", trigger:"blur"},
          {min:3,max:5,message:"长度为3到5个字符",trigger:"blur"}
        ]
      }
    }
  },
  methods:{
    register(){
      if(this.form.password !== this.form.confirm){
        ElMessage({
          type: 'error',
          message:'2次密码输入不一致',
        })
      }
      request.post("/api/user/register",this.form).then(res=>{
        if(res.code === '0'){
          ElMessage({
            type: 'success',
            message:'注册成功',
          })
          this.$router.push("/login")   //登录成功之后进行页面跳转
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