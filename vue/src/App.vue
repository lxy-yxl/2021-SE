<template>
  <div>
<!--    头部-->
    <Header></Header>
<!--    主体-->
    <div style="display: flex">
<!--      侧边栏-->
      <Asside></Asside>
<!--      内容区域-->
      <router-view style="flex: 1"/>

    </div>
  </div>
</template>

<style>

</style>


<script>
import Header from '@/components/Header.vue';
import Asside from '@/components/Asside.vue';
export default {
  name: "Layout",
  components: {
    Header,
    Asside
  }
}

</script>