<template>
  <div @click="router.go(-1)">
      <!-- <h5 class="center">個別訂單內容</h5> -->
      <!-- <p>便當1:</p> -->
        <!-- <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            訂單ID
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a class="dropdown-item" href="#">{{ orderNumber }}</a></li>
          </ul>
        </div> -->
      <h5>訂單編號：{{ body.orderNumber }}</h5>
      <table class="table table-bordered">  
          <thead>
              <tr>
                <th style="width: 50px;">主菜</th>
                <th style="width: 70px;">配菜1</th>
                <th style="width: 70px;">配菜2</th>
                <th style="width: 70px;">配菜3</th>
                <th style="width: 70px;">配菜4</th>
                <th style="width: 40px;">加點主菜</th>
                <th style="width: 70px;">便當價格</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="{ id , main, side1, side2, side3, side4, extraMain, detailTotal } in detail" :key="id">
                <!-- <td>{{id}}</td> -->
                <!-- <td>訂單ID:{{ orderNumber }}</td> -->
                <td>{{ main }}</td>
                <td>{{ side1 }}</td>
                <td>{{ side2 }}</td>
                <td>{{ side3 }}</td>
                <td>{{ side4 }}</td>
                <td>{{ extraMain }}</td>
                <td>${{ detailTotal }}</td>
              </tr>
          </tbody>
      </table> 
    
  </div>
</template>

<script setup>

import axios from "axios";
import {ref,reactive} from 'vue';
// import { ArrowLeft } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const detail = ref([]);

const body = reactive({
  orderNumber: null
})
const URL = import.meta.env.VITE_API_JAVAURL;

const fetchData = async () => {
  body.orderNumber=localStorage.getItem('iOrderNumber')
  try {
    const response = await axios.post(`${URL}detail/find`, body); // 替換為你的後端 API 端點
    detail.value = response.data.list; // 將獲得的數據賦值給 products 變數
    console.log('response=' + JSON.stringify(response))
    console.log("detail="+detail.value[0].orderNumber)
  } catch (error) {
    console.error("Error fetching data:", error);
  }
  
};

// 調用 fetchData 函數以獲取數據
fetchData();

// const goBack = () => {
//   router.go(-1); // 返回上一页
// };

</script>

<style scoped>
.bottom-buttons {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: #f0f0f0; /* 背景颜色，根据需要进行调整 */
  padding: 10px; /* 内边距，根据需要进行调整 */
  text-align: center; /* 文本居中对齐 */
}

/* 首頁按鈕樣式 */
.router-link-exact-active {
  background-color: #007BFF; /* 设置按钮背景色为灰色 */
  color: #fff; /* 鏈接文字顏色 */
  border: none; /* 去掉按钮边框 */
  padding: 10px 20px; /* 设置按钮内边距 */
  border-radius: 30px; /* 添加圆角效果 */
  transition: background-color 0.2s ease-in-out; /* 添加颜色渐变过渡效果 */

  font-weight: bold; /* 加粗字體 */
  text-decoration: none; /* 下劃線效果 */
  margin-right: 10px; /* 右邊距，可根據需要調整按鈕之間的間距 */

}
.router-link-exact-active:hover{
  background-color: #0056b3; /* 鼠標懸停時的背景顏色 */

}

/* 上一頁按鈕樣式 */
button {
  background-color: #007BFF; /* 按鈕背景顏色 */
  color: #fff; /* 按鈕文字顏色 */
  border: none; /* 移除邊框 */
  padding: 10px 20px; /* 內邊距 */
  border-radius: 30px; /* 圓角 */
  cursor: pointer; /* 鼠標指針形狀 */
  margin-right: 10px; /* 右邊距，可根據需要調整按鈕之間的間距 */
  transition: background-color 0.2s; /* 背景顏色變化的過渡效果 */
}

button:hover {
  background-color: #0056b3; /* 鼠標懸停時的背景顏色 */
}
.center{
  margin-top: 50px;
  text-align: center; /* 文本居中对齐 */

}
</style>