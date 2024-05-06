<template>
  <div class="body">
    <div>
      <!-- <div class="container-fluid">
        <div class="row">
        <h4>紅利點數查詢</h4>
            <div>
              <label>會員ID：</label>
              <input
                type="text"
                id="searchQuery"
                placeholder="請輸入會員ID"
                v-model="body.memberId"
              />
              <button @click="performSearch">查詢</button>
            </div>
        </div>
      </div> -->
  
      <div v-if="searchResults.length > 0">
          <br>
          <h5 class="h5">紅利 查詢結果：</h5>
          <br>
          <ul>
            <div class="card"  style="width:90%; background-color: rgb(255, 255, 255);"  >
              <table class="table"  > 
                <tbody >
                  <tr >
                    <p class="list" style="background-color: rgb(255, 255, 255);"  v-for="record in searchResults" :key="record.orderNumber">
                      <span>日期：{{ record.receivedDate }}</span><br>
                      <span>訂單編號：{{ record.orderNumber }}</span><br>
                      <span>獲得點數：{{ record.receivedPoints }}</span>
                      <span>&nbsp&nbsp&nbsp使用點數：{{ record.usedPoints }}</span>
                      <span>&nbsp&nbsp&nbsp剩餘點數：{{ record.currentPoints }}</span>
                    </p>
                  </tr>
                </tbody>
              </table>
            </div>
          </ul>
      </div>

        <!-- <div v-else>
          <p>請輸入會員ID並按下查詢按鈕</p>
        </div> -->
    </div>
  </div>
</template>
  
<script setup>
import { reactive, ref } from "vue";
import axios from "axios";
axios.defaults.withCredentials = true;
const searchQuery = ref("");
const searchResults = ref([]);

let body=reactive({
  id:null,
  memberId:"",
})

const URL = import.meta.env.VITE_API_JAVAURL // 替換為實際的後端 API 端點  
const performSearch = async () => {
  body.memberId=localStorage.getItem('memberId')
  try {
    const response = await axios.post(`${URL}products/find`, body);  //路徑，變數
    console.log(response)
    searchResults.value = response.data.list;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};
performSearch()
</script>


<style scoped>
/* .body {
   
    background-color: rgb(189, 62, 128); 
    margin: auto;
    text-align:text-center
} */

.body .row {
  margin: auto;
  width: 400px;
  margin-top: 30px;
  /* background-color: rgb(189, 62, 128);  */
}

 .h5{
  margin: auto;
  width: 200px;
}

.body .table{
  margin: auto;
  width: auto;
  justify-content:space-between;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  /* background-color: rgb(189, 62, 128);  */
}

.list {
  flex-direction: column; /* 垂直布局，使内容居中 */
  align-items: center; /* 在主轴上居中 */
  justify-content: center; /* 在交叉轴上居中 */
}

.h1{
  margin: auto;
}

/* .body .container {
} */
</style>






  