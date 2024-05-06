<script setup>

// import Home from './views/Home.vue'
// import Conservatore from './views/Conservator.vue'
// import ShoppingCart from './views/ShoppingCart.vue'
import Login from './views/Login.vue'

import Navbar1 from './components/Navbar1.vue';

//  import HelloWorld from './components/HelloWorld.vue';
//  import Reward from './views/Reward.vue';
 import Reward2 from './views/Reward2.vue';


import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.js'
import 'bootstrap-icons/font/bootstrap-icons.css'
import NewCarousel from './components/NewCarousel.vue';

//明毅
import { ref } from 'vue'
const status = ref(0);
function updateStatusB(e){
  status.value = e
}
</script>

<template>
<!-- <router-link to="/">筷筷吃便當</router-link>|
<router-link to="/conservator">管理員</router-link> |
<router-link to="/shoppingcart">購物車</router-link> |
<router-link to="/login">登入</router-link> |  
<hr />  -->



<div class="container">
    <!-- <Navbar></Navbar> -->
    <Navbar1 v-bind:status="status"></Navbar1> <!-- 給 -->
    <RouterView v-on:updateStatusA="updateStatusB"></RouterView> <!-- 接 -->

</div>


    <!-- <home></home>
    <ShoppingCart></ShoppingCart>
    <Conservatore></Conservatore> -->
    <!-- <Login></Login> -->
    

</template>

<style>
/* 這是全局樣式，會影響整個HTML頁面 */
body {
    /* background-color: rgba(0, 0, 0, 0.04); */
    /* background-color: rgb(166, 95, 95); */
    /* background-color: rgb(44, 114, 154);  */
    /* background-color: rgbRGB (247,247,247); */
    /* background-color: rgb(189, 62, 128);  */
    background-color: rgba(0, 0, 0, 0.04);
 
}

/* body {
   
   background-color: rgb(44, 114, 154); 


} */
</style>
