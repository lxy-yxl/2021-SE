<template>
  <div style="width: 100%; height: 100vh;background-color: #9AC8E2; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color :#576690; font-sizr:30px; text-align:center; padding:30px 0">欢迎注册</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="username">
          <el-input v-model="form.username">
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" show-password>
          </el-input>
        </el-form-item>
        <el-form-item prop="confirm">
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
  name: "Register",
  data() {
    return {
      form: {},
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: 'blur'},
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'}
        ],
        confirm: [
          {required: true, message: "请确认密码", trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    register() {
      if (this.form.password !== this.form.confirm) {
        ElMessage({
          type: 'error',
          message: '2次密码输入不一致',
        })
      }

      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/user/register", this.form).then(res => {
            if (res.code === '0') {
              ElMessage({
                type: 'success',
                message: '注册成功',
              })
              this.$router.push("/login")   //注册成功之后进行页面跳转
            } else {
              ElMessage({
                type: 'error',
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>