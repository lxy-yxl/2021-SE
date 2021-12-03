<template>
<div>
  <el-menu
      style="width: 200px;min-height: 100vh"
      default-active="2"
      class="el-menu-vertical-demo"
  >
      <el-sub-menu index="1-4">
        <template #title>选项1</template>
        <el-menu-item index="1-4-1">选项 1-1</el-menu-item>
      </el-sub-menu>
    <el-menu-item index="2">
      <el-icon><icon-menu /></el-icon>
      <span>Navigator Two</span>
    </el-menu-item>
    <el-menu-item index="3" disabled>
      <el-icon><document /></el-icon>
      <span>Navigator Three</span>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <span>Navigator Four</span>
    </el-menu-item>
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