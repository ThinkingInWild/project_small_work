<template>
      
      <nav aria-label="Page navigation example">
  <ul class="pagination">   
    <li class="page-item" v-for="(value,index) in totalPages" :key="index"  @click="clickHandler(value)"><a :class="{'currentPage': thePage===value,'page-link':true}" href="#">{{value}}</a></li>
   
  </ul>
</nav> 
</template>
    
<script setup>
    
    //定義父元件傳進來的屬姓名稱
//父傳子
const props = defineProps({
   totalPages: Number,
   thePage: Number
})
//defineEmits 定義父元件傳入到事件
const emit = defineEmits(["abcde"])
const clickHandler = page => {
    emit("abcde", page)                     //傳事件和變數到父元件，自定義事件abcde為呼叫此函數的事件
}


</script>
    
<style>
    .pagination li {
    cursor: pointer;
}
.currentPage {
    background-color: lightblue;
}
</style>