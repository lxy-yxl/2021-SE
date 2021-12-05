<template>
  <div style="padding: 10px">
<!--  功能区域 -->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!-- 搜索区域   -->
    <div style="margin: 10px 0">
      <el-input v-model="search" style="width:20%" placeholder="请输入关键字" />
      <el-button type="primary" style="margin-left: 5px">搜索</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="username" label="用户名"  />
      <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="地址" />

      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button type="text"  @click="handleEdit(scope.row)"
          >编辑</el-button>

          <el-popconfirm title="确认删除吗?">
            <template #reference>
              <el-button type="text">删除</el-button>
            </template>
          </el-popconfirm>


        </template>
      </el-table-column>
    </el-table>

    <div style="margin:10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
      <el-dialog v-model="dialogVisible" title="Tips" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width:80%" ></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width:80%" ></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width:80%" ></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <div>
              <el-radio v-model="form.sex" label="男">男</el-radio>
              <el-radio v-model="form.sex" label="女">女</el-radio>
              <el-radio v-model="form.sex" label="其他">其他</el-radio>
            </div>
          </el-form-item>
          <el-form-item label="地址">
          <el-input type="textarea" v-model="form.address" style="width:80%" ></el-input>
        </el-form-item>


        </el-form>
        <template #footer>
           <span class="dialog-footer">
              <el-button @click="dialogVisible = false">Cancel</el-button>
               <el-button type="primary" @click="save">Confirm</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name:'Home',
  components:{

},
  data() {
    return {
       form:{},
      dialogVisible:false,
      search: '',
      currentPage:1,
      total:10,
      tableData: [

      ],
    }
  },
  methods:{
    save(){
        request.post("/api/user",this.form).then(res =>{
            console.log(res)
        })
    },
    add(){
        this.dialogVisible=true
        this.form={}
    },
    handleEdit(){

    },
    handleSizeChange(){

    },
    handleCurrentChange(){

    }
  }
}
</script>
