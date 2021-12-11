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
      <el-input v-model="search" style="width:20%" placeholder="请输入关键字" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
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
          <el-button type="text"  @click="handleEdit(scope.row.id)"
          >编辑</el-button>

          <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
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
          :page-size="pageSize"
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
              <el-button @click="dialogVisible = false">取消</el-button>
               <el-button type="primary" @click="save">确定</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";

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
      pageSize:10,
      total:0,
      tableData: [],
    }
  },
  created(){
    this.load()
  },
  methods:{
    load(){
        request.get("/user",{
          params:{
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search:this.search
          }
        }).then(res=>{
          console.log(res)
          this.tableData=res.data.records
          this.total=res.data.total
        })
    },
    save(){
      if (this.form.id){
          request.put("/user",this.form).then(res =>{
            console.log(res)
            if(res.code === '0'){
              ElMessage({
                type: 'success',
                message:'更新成功',
              })
            }else{
              ElMessage({
                type: 'error',
                message:'更新失败',
              })
            }

            this.load() //刷新数据
            this.dialogVisible= false
          })
      }
      else {
        request.post("/user", this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            ElMessage({
              type: 'success',
              message:'新增成功',
            })
          }else{
            ElMessage({
              type: 'error',
              message:'新增失败',
            })
          }
          this.load() //刷新数据
          this.dialogVisible= false
        })
      }
    },
    add(){
        this.dialogVisible=true
        this.form={}
    },
    handleEdit(row){
        this.form = JSON.parse(JSON.stringify((row)))
        this.dialogVisible=true
    },
    handleSizeChange(pageSize){  //改变当每页展示条目的数量
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){  //改变当前页码
      this.currentPage=pageNum
      this.load()
    },
    handleDelete(id){
      console.log(id)
      request.delete("/user/"+id).then(res => {
        console.log(res)
        if(res.code === '0'){
          ElMessage({
            type: 'success',
            message:'删除成功',
          })
        }else{
          ElMessage({
            type: 'error',
            message:'删除失败',
          })
        }
        this.load() //刷新数据
      })
    }
  }
}
</script>
