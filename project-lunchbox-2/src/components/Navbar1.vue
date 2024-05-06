<template>
  <nav class="navbar navbar-expand-xl bg-body-tertiary" id="custom-navbar">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">筷筷吃便當
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <!-- <li class="nav-item">
            <RouterLink to="/about" class="nav-link">關於</RouterLink>   
          </li> -->
          <!-- <li class="nav-item">
            <RouterLink to="/contact" class="nav-link">聯絡我們</RouterLink>
          </li> -->
          <!-- <li class="nav-item">
            <RouterLink to="/products" class="nav-link">商品管理</RouterLink>
          </li> -->
          <li class="nav-item">
          <RouterLink class="nav-link forAdmin" to="/backend" v-if="isAdmin">後台入口</RouterLink>    <!-- 這裡 -->
          </li>
        </ul>
      </div>
      
            <!-- 回到最上層 -->
    <div class="back-to-top" @click="scrollToTop">
      <i class="bi bi-arrow-up-circle"></i>
    </div>
    
      <!-- 右側按鈕 -->
      <div class="navbar-nav ml-auto">

        <li class="nav-item">
        <RouterLink class="nav-link" v-bind:class="{addPadding:isCustomer }" to="/billboard">留言板</RouterLink>
        </li>
        
        <li class="nav-item">
          <a href="/shoppingcart" class="btn btn-login">購物車</a>
        </li>
        
        <li class="nav-item">         
          <a href="#" class="btn btn-login" style="margin-left:10px;" @click="showLoginModal()" v-if="!isCustomer">登入</a>
        </li>
        <li>
          <!-- <a href="#" class="btn btn-login" type="button" @click="openLoginModal">登入</a> -->
          <!-- <Login></Login> -->
        </li>
        <li class="nav-item">
          <Menu class="nav-link" v-on:log-out="resetLogin()" v-on:member-modify-modal="showMemberModifyModal()" v-if="isCustomer"></Menu>
        </li> 
        
      </div>
    </div>
    
    <div>
        <LoginModal v-on:close-modal="closeModal()"></LoginModal>
        <MemberModifyModal v-on:close-modal="closeModal()"></MemberModifyModal> 
    </div>
  </nav>

  <!-- 登入弹窗 -->
  <!-- <div v-if="loginModalVisible" class="login-modal">
    <div class="login-content">
      <span class="close" @click="closeLoginModal">&times;</span>  -->
      <!-- 这里可以添加登录表单或其他内容 -->
    <!-- <Login></Login> -->
  <!-- <div class="login-page">
    <div class="login-container">
      <h2>會員登入</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <input type="email" class="form-control" v-model="email" placeholder="Email">
        </div>
        <div class="form-group">
          <input type="password" class="form-control" v-model="password" placeholder="Password">
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-login">登入</button>
        </div>
      </form>
      <p class="mt-20">還不是會員？ <router-link to="/signup">註冊新帳號</router-link></p>
    </div>
  </div> -->

    <!-- </div>
  </div> -->
</template>

<script setup>
import Login from '../views/Login.vue';
// import { ref } from 'vue';
// const loginModalVisible = ref(false);

// const openLoginModal = () => {
//   loginModalVisible.value = true;

// }

// const closeLoginModal = () => {
//   loginModalVisible.value = false;
// }

// 方法
const scrollToTop = () => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth',
  });
};

import Menu from './Menu.vue'
import { ref, watch } from 'vue'
const isAdmin = ref(false)
const login = ref('')
const isCustomer = ref(false)
const accessLevel = ref('') 
//
//明毅的部分
//登入頁面的浮出功能
//會員個人資料修改頁面的浮出功能
import { onMounted } from 'vue'
import LoginModal from '../views/LoginModal.vue'
import MemberModifyModal from '../views/MemberModifyModal.vue'
let myLoginModal;
let myMemberModifyModal;
onMounted(()=>{
    myLoginModal = new bootstrap.Modal(document.getElementById('loginModal'))
    myMemberModifyModal = new bootstrap.Modal(document.getElementById('memberModifyModal'))
})
function showLoginModal(){
    myLoginModal.show()
}
function showMemberModifyModal(){
    myMemberModifyModal.show()
}
function closeModal(){
    console.log('closeModal reached')
    myLoginModal.hide()
    checkLogin()
}


const props = defineProps({
    status: Number
})
//當props.status改變時，會觸發這個回呼函數
watch(()=>props.status, (newStatus, oldStatus) => {
    //在這裡可以執行你的邏輯，例如更新元件的內部狀態或觸發其他操作 
    console.log(`props.status 从 ${oldStatus} 变为 ${newStatus}`);
    checkLogin() 
});

function checkLogin(){
    login.value=localStorage.getItem('login')
    accessLevel.value=localStorage.getItem('accessLevel')
    // console.log('檢查login=', login.value)
    // console.log('檢查accessLevel=', accessLevel.value)

    if(login.value=='yes' && (accessLevel.value=='admin' || accessLevel.value=='manager' || accessLevel.value=='worker')){
        isAdmin.value = true
        isCustomer.value = true
        console.log('有到checkLogin case1')
    } else if(login.value=='yes' && accessLevel.value=='customer'){
        isAdmin.value = false
        isCustomer.value = true
        console.log('有到checkLogin case2')
    } else {       
        isCustomer.value = false;
        isAdmin.value = false;
        console.log('有到checkLogin case3')
        
    }
    // console.log('isAdmin.value= '+ isAdmin.value)
    // console.log('isCustomer.value= '+ isCustomer.value)
}

function resetLogin(){
    localStorage.clear();
    // sessionStorage.clear();
    isCustomer.value = false;
    isAdmin.value = false;
    login.value='';
    accessLevel.value='';
}

checkLogin()
</script>

<style scoped>
.forAdmin {
    color:red;
    font-size: 18px;
}

  /* 其他导航栏样式 */

.back-to-top {
  position: fixed;
  right: 20px;
  bottom: 20px;
  background-color:#000000 ;
  opacity: 0.3;
  color: #fff;
  border: none;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  text-align: center;
  cursor: pointer;
  transition: background-color 0.8s;
}
.back-to-top i {
  font-size: 60px;
  line-height: 40px;
}

.back-to-top:hover {
  background-color: #9fa0a1;
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
  /* 注册按钮样式 */
  .navbar-nav.ml-auto .nav-item:nth-child(2) a {
    background-color: #ac0707; /* 设置按钮背景色为灰色 */
    color: #FFF; /* 设置按钮文字颜色为白色 */
    border: none; /* 去掉按钮边框 */
    padding: 10px 20px; /* 设置按钮内边距 */
    border-radius: 50px; /* 添加圆角效果 */
    text-decoration: none; /* 去掉链接下划线 */
    transition: background-color 0.2s ease-in-out; /* 添加颜色渐变过渡效果 */
  }
  .navbar-nav.ml-auto .nav-item:nth-child(3) a {
    background-color: #ac0707; /* 设置按钮背景色为灰色 */
    color: #FFF; /* 设置按钮文字颜色为白色 */
    border: none; /* 去掉按钮边框 */
    padding: 10px 20px; /* 设置按钮内边距 */
    border-radius: 50px; /* 添加圆角效果 */
    text-decoration: none; /* 去掉链接下划线 */
    transition: background-color 0.2s ease-in-out; /* 添加颜色渐变过渡效果 */
  }
  /* 鼠标悬停时按钮背景色 */
  .navbar-nav.ml-auto .nav-item:nth-child(1) a:hover,
  .navbar-nav.ml-auto .nav-item:nth-child(2) a:hover,
  .navbar-nav.ml-auto .nav-item:nth-child(3) a:hover {
    background-color: #d60b0b; /* 设置悬停时的背景色 */
  }

</style>