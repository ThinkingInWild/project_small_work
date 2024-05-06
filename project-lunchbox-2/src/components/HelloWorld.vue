<template>
    <!-- <div id="app">
        <H1 style="display: inline-block;">紅利歷史{{ msg }}</H1>
    <button @click="queryData" style="d"
    </div> -->


  <imput type="text" v-model="msg"/>
  <H1>紅利歷史{{ msg }}</H1> 
  <button @click="queryData">查詢</button>
  <h3>使用日期 : </h3>   
  <h3>使用紅利 : </h3>
  <h3>訂單Id : </h3>
  <h3>剩餘紅利 : </h3>
</template>
    
<script setup>
  import {ref} from "vue"
  const msg=ref("")  
</script>
    
<style scoped>
    h1{color:rgb(215, 197, 178);}
</style>