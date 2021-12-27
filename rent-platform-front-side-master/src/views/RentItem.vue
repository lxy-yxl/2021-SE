<template>
    <div>
        <img src="../assets/img/bg-banner-custome.jpg" style="z-index:-100;position:absolute;left:0%;bottom:8%;"/>
        <div class="head-box-container" ref="head">
        <div class="head-box">
            <div class="head-box-left"></div>
            <div class="head-box-right">
            <div class="head-box-menu">
                <router-link to="/" class="head-box-menu-item"><i class="el-icon-s-home"/>Home</router-link>
                <router-link to="/homepage/items" class="head-box-menu-item"><i class="el-icon-s-goods"/>Items</router-link>
                <router-link to="/homepage/user" class="head-box-menu-item"><i class="el-icon-s-order"/>Orders</router-link>
            </div>
            <div class="sign-in" >Log Out</div>
            </div>
        </div>
        </div>
            <el-card style="width:55%;height:60%;position:absolute;top:22%;left:10%;border-radius: 35px;padding:50px">
                <div style="float:right">上传图片</div>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" style="width:60%">
                    <el-form-item label="物品名称" prop="name">
                        <el-input v-model="ruleForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="交付地点" prop="region">
                        <el-select v-model="ruleForm.region" placeholder="请选择交付地点">
                        <el-option label="嘉定校区" value="嘉定"></el-option>
                        <el-option label="四平校区" value="四平"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="交付时间" required>
                        <el-col :span="11">
                        <el-form-item prop="date1">
                            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                        <el-form-item prop="date2">
                            <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>
                        </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="物品种类" prop="type">
                        <el-checkbox-group v-model="ruleForm.type">
                        <el-checkbox label="服装" name="type"></el-checkbox>
                        <el-checkbox label="数码" name="type"></el-checkbox>
                        <el-checkbox label="文具" name="type"></el-checkbox>
                        <el-checkbox label="鞋" name="type"></el-checkbox>
                        </el-checkbox-group>
                    </el-form-item>
                    <el-form-item label="物品描述" prop="desc">
                        <el-input type="textarea" v-model="ruleForm.desc"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">立即上传</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        ruleForm: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          type: [],
          desc: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入物品名称', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择交付地点', trigger: 'change' }
          ],
          date1: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
          date2: [
            { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个物品种类', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写物品描述', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style lang="scss" scoped>
.head-box-container {
  position: fixed;
  background-color: white;
  top: 0;
  height: 80px;
  width: 100%;
  padding: 0 40px;
  z-index: 99999;
  transition: all .5s ease;

  .head-box {
    height: 100%;
    width: 100%;
    padding: 30px 0;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .head-box-left {
      height: 60px;
      width: 187px;
      background-image: url('../assets/img/logo.svg');
    }

    .head-box-right {
      display: flex;
      flex-direction: row;
      justify-content: flex-end;
      align-items: center;

      .head-box-menu {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        padding-right: 35px;

        .head-box-menu-item {
          user-select: none;
          width: 90px;
          height: 60px;
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items: center;
          transition: all .3s ease;
          text-decoration: none;
        }

        .head-box-menu-item:hover {
          color: rgba(245, 42, 42, 0.616);
        }
      }

      .sign-in {
        user-select: none;
        text-decoration: underline;
        margin-right: 53px;
        display: flex;
        flex-direction: row;
        align-items: center;

        &:before {
          content: '';
          display: inline-block;

          width: 20px;
          height: 20px;

          background-image: url('../assets/Login.svg');
          background-size: cover;
          background-repeat: no-repeat;
          margin-right: 3px;
        }
      }

      .sign-in:hover {
        color: rgba(245, 42, 42, 0.616);
      }
    }
  }
}
</style>
