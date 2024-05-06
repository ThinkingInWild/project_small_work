<template>
    <li class="nav-item" >
    <a href="#" class="btn btn-login" type="button" @click="openLoginModal">登入</a>
    </li>
    <div v-if="loginModalVisible" class="login-modal">
        <div class="login-content" style="width: 450px;">
          <span class="close" @click="closeLoginModal">&times;</span> 
    
      <div class="login-page" >
        <div class="login-container" >
    
          <h2>會員登入</h2>
          <form @submit.prevent="login">
            <div class="form-group">
              <input type="tel" class="form-control" v-model="phone" placeholder="手機號碼">
            </div>
            <div class="form-group">
              <input type="password" class="form-control" v-model="password" placeholder="密碼">
            </div>
            <div class="text-center"> 
            <a href="/passwordreset" class="right-side" >忘記密碼</a>|
            <a href="/register" class="left-side" >註冊會員</a>
            </div>
          </form>
          
            <div class="text-center">
              <button type="submit" class="btn btn-primary" style="width: 150px; ">登入</button>
            </div>
          
        </div>
      </div>
    
    </div>
      </div>
    </template>
    
    
    <script setup>
    
    import { ref } from 'vue';
    const loginModalVisible = ref(false);
    
    const openLoginModal = () => {
      loginModalVisible.value = true;
    
    }
    
    const closeLoginModal = () => {
      loginModalVisible.value = false;
    }
    
    // export default {
        
    //   data() {
    //     return {
    //       phone: '', // 添加 phone 属性用于存储手机号码
    //       password: '' // 保留密码属性
    //     };
    //   },
    //   methods: {
    //     // 取消登入功能
    //     cancelLogin() {
    //       // 在这里导航到首页或执行其他取消操作
    //       this.$router.push('/');
    //     },
    //     // 登入功能
    //     login() {
    //       // 在这里添加实际的登录逻辑，可以使用 this.phone 和 this.password 获取手机号码和密码
    //     }, 
    
    
    //   }
    // }
    </script>
    
    
    <style scoped>


  
        .form-group{
            margin: 30px;
            
        }
        
        .text-center{
            
            margin-top: 30px;
        }
        
    .right-side{
        
        
        margin-right: 25px;
    }
    
    .left-side{
        margin-left: 25px;
    }
    
    /* 登录按钮样式 */
  .navbar-nav.ml-auto .nav-item:nth-child(1) a {
      background-color: #ac0707; /* 设置按钮背景色为蓝色 */
      color: #FFF; /* 设置按钮文字颜色为白色 */
    border: none; /* 去掉按钮边框 */
    padding: 10px 20px; /* 设置按钮内边距 */
    border-radius: 50px; /* 添加圆角效果 */
    text-decoration: none; /* 去掉链接下划线 */
    transition: background-color 0.2s ease-in-out; /* 添加颜色渐变过渡效果 */
    margin-right: 10px; /* 添加右侧间距 */
}
  

/* 登入弹窗样式 */
.login-modal {
    display: none;
    position: fixed;
    z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  
}

.login-content {
    background-color: #fff;
    padding: 60px;
    border: 2px solid #888;
    width: 25%;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    text-align: center;
    border-radius: 20px;
    position: relative;
}
.close {
    position: absolute;
    top: 0;
    right: 0;
    padding: 10px;
    cursor: pointer;
  font-size: 20px;
  font-weight: bold;
}
.btn-login {
    background-color: #007BFF; /* 背景颜色 */
    color: #fff; /* 文本颜色 */
    border: none; /* 去除边框 */
  padding: 10px 20px; /* 内边距 */
  border-radius: 50px; /* 圆角 */
  cursor: pointer; /* 鼠标光标效果 */
  font-weight: bold; /* 文本粗细 */
}

.btn-login:hover {
  background-color: #0056b3; /* 按钮悬停时的背景颜色 */
}

</style>