<template>
    <div class="smallShoppingCart opacity-75">
      <h3 class="mb-4">訂單清單</h3>
      <div class="toggle-list" @click="toggleList">
        <i class="bi bi-dash-square-fill"></i>
      </div>
      <table class="table table-bordered">
        <thead class="table-dark">
          <tr>  
            <th>項目</th>
          </tr>
        </thead>
        <tbody v-show="showList">
          <tr v-for="(lunchboxOrder, index) in lunchboxOrders" :key="index">
            <td>{{ index + 1 }}. {{ lunchboxOrder.main }}肉{{ lunchboxOrder.riceChoice }}便當
                <i class="bi bi-cart-check-fill"></i>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref, defineProps } from 'vue';
  
  const props = defineProps({
    lunchboxesToSmallShoppingCart: null,
  }); // key:lunchboxStringToSmallShoppingCart, value:傳進來一個 JSON Array。
  console.log(props.lunchboxStringToSmallShoppingCart);
  const lunchboxOrders = props.lunchboxesToSmallShoppingCart;
  
  const showList = ref(true);
  
  const toggleList = () => {
    showList.value = !showList.value;
  };
  </script>
  
  <style scoped>
  .smallShoppingCart {
    position: fixed;
    z-index: 3;
    top: 200px;
    right: 20px;
    background-color: rgb(255, 255, 255);
    width: 300px;
    max-height: 400px;
    overflow-y: auto;
    border-radius: 20px;
    padding: 20px;
    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.4);
  }
  
  .toggle-list {
    position: absolute;
    top: 10px;
    right: 10px;
    /* background-color: rgb(163, 6, 6);
    color: rgb(255, 255, 255); */
    border-radius: 10px;
    width: 30px;
    height: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
    cursor: pointer;
  }
  
  .btn {
    display: block;
    margin-top: 20px;
    width: 100%;
    cursor: pointer;
  }
  </style>
  