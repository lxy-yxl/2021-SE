<template>
<div>
  <el-menu
      style="width: 200px;min-height: 100vh"
      default-active="user"
      router
      class="el-menu-vertical-demo"
  >
      <el-sub-menu index="1">
        <template #title>系统管理</template>
        <el-menu-item index="user">用户管理</el-menu-item>
      </el-sub-menu>
       <el-menu-item index="product">产品管理</el-menu-item>
  </el-menu>
</div>
</template>

<script>
export default {
  name: "asside"
}
</script>

<style scoped>

</style>