<template>
  <div class="body">


    <!-- <div class="container">
    <div class="item">
        <p>Item1</p>
    </div>
    <div class="item">
        <p>Item2</p>
    </div>
    <div class="item">
        <p>Item3</p>
    </div>
    <div class="item">
        <p>Item4</p>
    </div>
</div> -->


 
    <!-- <img src= "https://adservice.google.com/ddm/fls/z/dc_pre=CLCM4tjZ3oEDFVQgKgodlDAKfQ;src=10204232;type=invmedia;cat=webyx0;ord=8282155633172;auiddc=*;u1=www.mcdonalds.co.jp;ps=1;pcor=1147595154;gtm=45He3a20;uaa=x86;uab=64;uafvl=Google%2520Chrome%3B117.0.5938.149%7CNot%253BA%253DBrand%3B8.0.0.0%7CChromium%3B117.0.5938.149;uamb=0;uam=;uap=Windows;uapv=10.0.0;uaw=0;epver=2;~oref=https%3A%2F%2Fwww.mcdonalds.co.jp%2F"> -->
    <!-- <p style="color: rgba(187, 41, 41, 0.1);">這是紅色文字。</p> -->
    <div>
          <div class="container-fluid">
          <div class="row">
      <h1>紅利點數查詢</h1>
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
  </div>


  <!-- <div class="row">
    <div class="col-4">

      <div class="row">
        123
      </div>
      <div class="row">
          456
      </div>
      
    </div>
    <div class="col-9">
      789
    </div>
  </div> -->



  <!-- <div class="card" style="width: 50%;">
    <table class="table table table-borderless" > 
      <tbody>
        <tr> -->
          <!-- <td> -->
            <!-- 222 -->
          <!-- </td> -->
          <!-- <td>123
        </td>
        </tr>
      </tbody>
    </table>
  </div> -->



    




      <div v-if="searchResults.length > 0">
        <br>
        <h5 class="h5">查詢結果：</h5>
        <ul>
         <!-- <div class="card"  style="width: 100% ; background-color: rgb(255, 255, 255);" > -->
           <!-- <tbody > -->
             <table class="table table-bordered" >  
              <thead>
            
             <tr style="background-color: rgb(255, 255, 255);"  v-for="record in searchResults" :key="record.orderNumber">
            <th>獲得日期：{{ record.receivedDate }}</th>
            <th>獲得點數：{{ record.receivedPoints }}</th>
            <th>訂單編號：{{ record.orderNumber }}</th>
            <th>剩餘點數：{{ record.currentPoints }}</th>
          </tr>     
      </thead>
      <!-- </tbody> -->
    </table>
  <!-- </div> -->
        </ul>
      </div>

      <div v-if="searchResults.length > 0">
        <ul>
           <!-- <div class="card" style="width: 100%;"> -->
            <table class="table table-bordered" >  
              <thead>
          <tr  style="background-color: rgb(255, 255, 255);" v-for="record in searchResults" :key="record.orderNumber">
            <th>使用日期：{{ record.usedDate}}</th>
            <th>使用點數：{{ record.usedPoints}}</th>
            <th>訂單編號：{{ record.orderNumber }}</th>
            <th>剩餘點數：{{ record.currentPoints}}</th>
          </tr>
      </thead>
    </table>
  <!-- </div> -->
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
  
  const searchQuery = ref("");
  const searchResults = ref([]);
  // const bodybody = ref({memberId:""});

  let body=reactive({
    id:null,
    memberId:"",
  })

  
  const URL = import.meta.env.VITE_API_whateverURL // 替換為實際的後端 API 端點
  const performSearch = async () => {
  
    try {
      const response = await axios.post(`${URL}products/find`,body);  //路徑，變數
      console.log(response)
      searchResults.value = response.data.list;
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  
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
  width: 500px;
  justify-content:space-between;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  /* background-color: rgb(189, 62, 128);  */
}


/* .body .container {
  
 
} */

</style>






  