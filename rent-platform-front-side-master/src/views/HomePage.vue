<template>
  <div id="home-page">
    <!-- 头部导航栏选择绝对布局 -->
    <div class="head-box-container" ref="head">
      <div class="head-box">
        <div class="head-box-left"></div>
        <div class="head-box-right">
          <div class="head-box-menu">
            <router-link to="/" class="head-box-menu-item">Home</router-link>
            <router-link to="/homepage/items" class="head-box-menu-item">Items</router-link>
            <router-link to="/homepage/user" class="head-box-menu-item">Orders</router-link>
          </div>
          <router-link to="/login" class="sign-in" v-show="login">Sign In</router-link>
          <div class="sign-in" v-show="!login" @click="logout">Log Out</div>
           <router-link to="/rent">
            <div class="head-box-btn">
             <div class="head-box-btn-content">Rent An Item</div>
            </div>
          </router-link>
        </div>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  computed: {
    login:function(){
      return !this.$store.state.login;
    }
  },
  methods: {
    logout(){
      this.$store.commit('LOGOUT');
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

      .head-box-btn {
        user-select: none;
        font-size: 16px;
        background-color: rgb(43, 90, 225);
        color: white;
        width: 170px;
        height: 50px;
        border-radius: 25px;
        //margin-right: 50px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        background-image: url('../assets/add.png');
        background-repeat: no-repeat;
        background-size: 25px 25px;
        background-position: left 20px center;
        transition: all .3s ease;

        .head-box-btn-content {
          flex-shrink: 0;
          margin-left: 30px;
        }
      }

      .head-box-btn:hover {
        background-color: rgb(34, 69, 175);
      }
    }
  }
}
</style>
