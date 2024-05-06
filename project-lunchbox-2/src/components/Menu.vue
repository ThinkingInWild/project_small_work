<template>
    <div class="wrapper">
          <div class="menu transition" @click="isActive = !isActive" v-bind:class="{ active: isActive }">
            <div class="bar bar1 transition"></div>
            <div class="bar bar2 transition"></div>
            <div class="bar bar3 transition"></div>
            <ul v-if="isActive">
                <li><MemberModifyModal v-bind:status="prop" v-show=false></MemberModifyModal></li>
                <li><RouterLink to="/memberModifyModal" v-on:click="$emit('member-modify-modal'); update()" >個人資料</RouterLink></li> 
                
                <!-- <li><a href="">訂單歷史</a></li> -->
                
                <li><RouterLink to="/memberRewardWindow">訂單紅利歷史</RouterLink></li>
                
                <li><RouterLink to="/logout"  v-on:click="$emit('log-out')">登出</RouterLink></li>
            </ul>
        </div>
    </div>
    
</template>


<script setup>
import { ref } from 'vue';
import MemberModifyModal from '../views/MemberModifyModal.vue';
const isActive = ref(false);

const prop = ref(0);
function update() {
  let random_num = Math.ceil(Math.random()*1000000)
  prop.value=random_num
//   console.log('update prop='+ prop.value)
}

</script>


<style scoped>  
    .transition {
        transition: 0.4s;
    }

    .wrapper {
        width: 60px;
        margin: -7px
    }

    .menu {
        display: inline-block;
        cursor: pointer;
        padding: 0 0 0 12px;
        position: relative;
        
    }

    ul,
    li {
        list-style: none;
        padding: 0;
        margin: 0;
        width: 200px;
        font-size: 20px;
    }

    .bar {
        width: 36px;
        height: 6px;
        background: #666;
        margin: 6px 0 0;
        border-radius: 2px;
    }

    .active .bar1 {
        -webkit-transform: rotate(-45deg) translate(-9px, 7px);
        transform: rotate(-45deg) translate(-9px, 7px);
    }

    .active .bar2 {
        opacity: 0;
    }

    .active .bar3 {
        -webkit-transform: rotate(45deg) translate(-9px, -8px);
        transform: rotate(45deg) translate(-9px, -8px);
    }

    .menu ul{
        background: rgba(255,255,255,.8);
        position: absolute;
        /* display: none; */
        transition: .4s;
        opacity: 0;
        max-height: 0;
        height:120px;
        /* overflow: hidden; */
        overflow: visible;
        z-index: 9999;
    }
    .menu.active ul{
        /* display: block; */
        opacity: 1;
        max-height: 200px;
    }
    .menu ul li a{
        font-family: Arial, Helvetica, sans-serif;
        display: block;
        color: #333;
        text-decoration: none;
        padding: 3px 20px;
    }
    .menu ul li a:hover{
        background: #fff;
    }
</style>
  