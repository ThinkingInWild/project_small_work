<template>
<!-- Modal Start-->
<div class="modal" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="loginModalLabel">會員登入</h3>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
<!-- Modal -->
<!-- 本體 Start -->
    <div>
    <form class="form-signin" @submit.prevent> <!--submit後不刷新input form--->
        <h1 class="h5 mb-3 fw-normal">請輸入帳密</h1>
        
        <label for="inputUsername" class="visually-hidden">username</label>
        <input type="text" id="inputUsername" class="form-control" placeholder="手機號碼" required autofocus v-model="user.memberId">
        
        <label for="inputPassword" class="visually-hidden">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="密碼" required v-model="user.memberPassword">
        <div class="links">
        <RouterLink to="/memberRegister" @click="$emit('close-modal')">註冊會員</RouterLink>
         <a href="#!" v-on:click="showAlert()">忘記密碼</a>
        </div>
        <span v-if="!isLoginSuccess" class="error-message">帳號或密碼不符</span>
        <!-- <button class="w-100 btn btn-lg btn-primary" type="submit" v-on:click="handleLogin(); $emit('updateStatusA', status)">Sign in</button> -->
        <button class="w-100 btn btn-lg btn-primary" type="submit" v-on:click="handleLogin()" data-bs-dismiss="modal">Sign in</button>

    </form>
    </div>
<!-- 本體 End -->
<!-- Modal -->    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" v-on:click="$emit('close-modal')" >取消</button>
        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>
<!-- Modal End -->

</template>
    
<script setup>
import { ref , reactive } from 'vue';
import axios from 'axios';
axios.defaults.withCredentials = true;
import { useRouter } from 'vue-router'
const router = useRouter()

const user = reactive({
  memberId: '0988555555',
  memberPassword: 'pwd5',
})

// const user = reactive({
//   memberId: '0988444444',
//   memberPassword: 'pwd4',
// })

import { defineEmits } from 'vue'
const emit = defineEmits(['updateStatusA', 'close-modal'])
function updateNavbarLoginLinks() {
  let random_num = Math.ceil(Math.random()*1000000)
  emit('updateStatusA', random_num)
  console.log('上傳=' + random_num)
}
function closeModal() {
  emit('close-modal')
}



//偷刷新頁面用
const refreshPage = () => {
  router.go(); // Reloads the current route
};

//使用Axios
const URL2 = import.meta.env.VITE_API_JAVAURL2
const list = ref([])
const isLoginSuccess=ref(true) //顯示'帳號或密碼不符'用
async function handleLogin(){
  //先取得session
  // console.log('用Axios執行getSession()' )
  const response1 =  await axios.get(`${URL2}getSession`)  
  // const response1 =  await axios.get(`${URL2}getSession`,{ withCredentials: true })  
  // console.log('回來的訊息: ' + JSON.stringify(response1))
  // console.log('給Axios執行verify的資料= ' + JSON.stringify(user))
  //驗證帳密
  const response2 =  await axios.get(`${URL2}verify/${user.memberId}/${user.memberPassword}` ) 
  // const response2 =  await axios.get(`${URL2}verify/${user.memberId}/${user.memberPassword}`, { withCredentials: true } ) 
  // console.log('回來的訊息1= ' + JSON.stringify(response2))
  // console.log('回來的訊息2= ' + JSON.stringify(response2.data)) //fail在這一層
  // console.log('回來的訊息3= ' + JSON.stringify(response2.data.list))
  
  //辨別成功或失敗
  if(response2.data!='fail'){
    console.log('成功case: ')
    list.value = response2.data.list //成功才取得到，包含此會員整個Memberbean內的值
    console.log(JSON.stringify(list.value))
    isLoginSuccess.value=true
    //將會員資料存到localStorage
    toLocalStorage(list)
    localStorage.setItem('login', 'yes');
    updateNavbarLoginLinks()
    closeModal()
    router.push('/')
    setTimeout(() => { //Delayed action performed after x milliseconds
        refreshPage()
      }, 600);
  } else {
    console.log('失敗case: ')
    console.log(response2.data)
    isLoginSuccess.value=false
  }
  
}


function toLocalStorage(bean){

  localStorage.setItem('id', bean.value[0].id);
  localStorage.setItem('memberId', bean.value[0].memberId);
  localStorage.setItem('memberName', bean.value[0].memberName);
  localStorage.setItem('memberPassword', bean.value[0].memberPassword);
  localStorage.setItem('email', bean.value[0].email);
  localStorage.setItem('gender', bean.value[0].gender);
  localStorage.setItem('memberAddress', bean.value[0].memberAddress);
  localStorage.setItem('discountPointsLeft', bean.value[0].discountPointsLeft);
  localStorage.setItem('signUpDate', bean.value[0].signUpDate);
  localStorage.setItem('finalLogInDate', bean.value[0].finalLogInDate);
  localStorage.setItem('accessLevel', bean.value[0].accessLevel);


    // let prop = {
    //     id: bean.value[0].id,
    //     memberId: bean.value[0].memberId,
    //     memberName: bean.value[0].memberName,
    //     email: bean.value[0].email,
    //     gender: bean.value[0].gender,
    //     memberAddress: bean.value[0].memberAddress,
    //     discountPointsLeft: bean.value[0].discountPointsLeft,
    //     accessLevel: bean.value[0].accessLevel,
    //     signUpDate: bean.value[0].signUpDate,
    // }
    // console.log('full bean=== ' + JSON.stringify(bean.value[0]))
    // console.log('bean.1= ' + bean.value[0].id)
    // console.log('bean.2= ' + bean.value[0].memberId)
    // console.log('bean.3= ' + bean.value[0].memberName)
    // console.log('bean.4= ' + bean.value[0].email)
    // console.log('bean.5= ' + bean.value[0].gender)
    // console.log('bean.6= ' + bean.value[0].memberAddress)
    // console.log('bean.7= ' + bean.value[0].discountPointsLeft)
    // console.log('bean.8= ' + bean.value[0].signUpDate)
    // console.log('bean.9= ' + bean.value[0].finalLogInDate)
    // console.log('bean.10= ' + bean.value[0].accessLevel)

    // localStorage.setItem('prop', JSON.stringify(prop)); //用sessionStoreage傳值
    
    // id =null
    // memberId=null
    // memberName=null
    // memberAddress=null
    // accessLevel=null
}

function showAlert(){
  alert('忘記密碼 沒救了')
}

</script>
    
<style scoped>
.modal {
  margin-top: 200px;
}

html,
body {
  height: 100%;
}

body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}
.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
  margin-bottom:3px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
.links {
    display: flex;
    flex: 1;
    justify-content: space-around;
    margin:10px;
}
.error-message {
    color: rgb(223, 82, 82);
    margin: 10px;
    display: flex;
    justify-content: center; /* 将子元素在主轴上水平居中 */
    align-items: center; /* 将子元素在交叉轴上垂直居中 */
    text-align: center;
}
</style>