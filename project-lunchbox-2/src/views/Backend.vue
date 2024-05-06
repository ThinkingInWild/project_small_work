<template>
    <!-- <RouterLink class="routerlink" to="/backend/order"><span type="button" class="size1 color1"  @click="switchTab('A')">訂單查詢</span></RouterLink> -->
    <RouterLink class="routerlink" to="/backend/orderQueryAdmin"><span type="button" class="size1 color1"  @click="switchTab('A')">訂單查詢</span></RouterLink>
    <RouterLink class="routerlink" to="/backend/memberFind"><span type="button" class="size1 color2"  @click="switchTab('B')">會員查詢</span></RouterLink>
    <RouterLink class="routerlink" to="/backend/inventory"><span type="button" class="size1 color3"  @click="switchTab('C')">庫存顯示</span></RouterLink>
    <RouterLink class="routerlink" to="/backend/report"><span type="button" class="size1 color4"  @click="switchTab('D')">報表</span></RouterLink>
    
    <div class="size2 color1" v-if="buttonA === 'A'"       style="height:1600px"><RouterView></RouterView></div>
    <div class="size2 color2" v-else-if="buttonA === 'B'"  style="height:1600px"><RouterView></RouterView></div>
    <div class="size2 color3" v-else-if="buttonA === 'C'"  style="height:1600px"><RouterView></RouterView></div>
    <div class="size2 color4" v-else-if="buttonA === 'D'"  style="height:1600px"><RouterView></RouterView></div>

    <!-- <div>
    <button ><RouterLink class="nav-link" to="/backend/order">訂單查詢</RouterLink></button>
    <button><RouterLink class="nav-link" to="/backend/memberFind">會員查詢</RouterLink></button>
    <button><RouterLink class="nav-link" to="/backend/inventory">庫存顯示</RouterLink></button>
    <button><RouterLink class="nav-link" to="/backend/report">報表</RouterLink></button>
    </div>
    <div><RouterView></RouterView></div> -->
    
</template>
    
<script setup>

import { ref } from 'vue';
const buttonA = ref("A");
function switchTab(cclick) {
    buttonA.value = cclick
}

</script>
    
<style scoped>
.routerlink {
    text-decoration: none;
    color: black;
}
.size1{
    width: 250px;
    border-top-right-radius: 100px 100px;
    font-size:22px
}
.size2 {
    width: 1100px;
    height: 700px;
}
.color1 {
    background-color: rgb(216, 219, 203);
}
.color2 {
    background-color: rgba(0, 255, 42, 0.062);
}
.color3 {
    background-color: rgba(204, 230, 245, 0.753);
}
.color4 {
    background-color: rgba(247, 152, 199, 0.315);
}

</style>