<template>
    <span class="queryBlock1">
        會員ID
       <input type = "text" v-model =  "body.memberId"> 
        <button @click = "abcd">查詢</button>
    </span>

    <span class="queryBlock2">
        訂單ID
       <input type = "number" v-model = "body.orderNumber"> 
    </span>

    <span class="queryBlock3">
        下單日期
       <input type = "text" v-model = "body.orderDate"> 
    </span>

    <br>

    <span class="queryBlock4">
        取單日期
       <input type = "text" v-model = "body.pickupDate"> 
    </span>

    <span class="queryBlock5">
        訂單地址
       <input type = "text" v-model = "body.orderAddress"> 
    </span>

    <span class="queryBlock6">
        訂單狀態
       <input type = "text"> 
    </span>



    <table class="table table-bordered">
        <thead>
            <tr>
                <th>訂單ID</th>
                <th>會員ID</th>
                <th>雞</th>
                <th>豬</th>
                <th>魚</th>
                <th>牛</th>
                <th>羊</th>
                <th>下單日期</th>
                <th>取單日期</th>
                <th>總價</th>
                <th>訂單地址</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="product in products" :key = product.orderNumber>
                <td><RouterLink @click = "deliverInfo(product.orderNumber)" class="nav-link active"  to="/backend/orderDetail" >
                    {{ product.orderNumber}}</RouterLink></td>
                    <td>{{product.memberId}}</td>
                    <td>{{product.chicken }}</td>
                    <td>{{product.pork }}</td>
                    <td>{{product.fish }}</td>
                    <td>{{product.beef }}</td>
                    <td>{{product.lamb }}</td>
                <td>{{ product.orderDate}}</td>
                <td>{{ product.pickupDate }}</td>
                <td>{{ product.orderTotal }}</td>
                <td>{{ product.orderAddress }}</td>
            </tr>
           
        </tbody>
       </table>



<Paging :totalPages="totalPages" :thePage="cP + 1" @abcde="clickHandler"></Paging>
        <!-- <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item" @click="clickHandler(value)" v-for="(value, index) in totalPages" :key="index"><a
                        :class="{ 'page-link': true, 'currentPage': cP + 1 === value }">{{ value }}</a></li>

            </ul>
        </nav> -->
        
</template>

<script setup>

import{ref, reactive} from 'vue';
import axios from 'axios';
import Paging from '../components/paging.vue';
// import Order from '../views/order.js'

// const memberId = ref();
// const body = ref(Order)

// const body = reactive({
    
//     "orderNumber":null,
//     "memberId":memberId,
//     "orderAddress":"",
//     "orderDate":"",
//     "pickupDate":"",
    
// })

let storedInfo = ref();
function deliverInfo(inputValue){
    storedInfo.value = inputValue;
sessionStorage.setItem('orderNumber', storedInfo.value);
console.log(storedInfo.value);
}


function abcd(){
console.log(body.orderNumber);
if(body.orderNumber === ""){
    body.orderNumber = null;
}
console.log(body.orderNumber);
callFindBy();
}



let body = reactive({


    orderNumber: null,
    memberId: null,
    orderAddress: null,
    orderDate: null,
    pickupDate:null,

    start:0,
    rows:3,
    order: "desc",
    sort: "orderNumber"
})

// const datas = reactive({
//     "start": 0,
//     "rows": 3,
//     "name": "",
//     "sortOrder": "asc",
//     "sortType": "id"
// });

const cP = ref(0);                        //分頁功能

const clickHandler = page => {            //分頁功能
    cP.value = page - 1
    body.start = (page - 1) * body.rows;
    callFindBy();
};

const totalPages = ref(0);                //分頁功能


const products = ref([]);                 //宣告一個陣列接response。

const URL = import.meta.env.VITE_API_whateverURL


async function callFindBy(){
    const response = await axios.post(`${URL}purchase/find`, body)
    // const response = await axios.get(`${URL}member/` + id.value)
    // const response = await axios.get("http://localhost:8080/pages/member/1")
    console.log(response);
    // console.log(response.data.list[0].orderNumber);
    console.log(body.memberId);
    products.value = response.data.list

    totalPages.value = body.rows === 0 ? 1 : Math.ceil(response.data.count / body.rows) //分頁功能
};

callFindBy();

</script>

<style scoped>

    
    .pagination li {
    cursor: pointer;
}

.currentPage {
    background-color: lightblue;
}

.queryBlock1{
    margin-bottom:20px;
    margin-right:100px;
    top:20px;
    left:30px;
}

.queryBlock2{
    margin-right:100px;
    top:20px;
    left:320px;
}

.queryBlock3{
   
    top:20px;
    left:30px;
}

.queryBlock4{
    margin-right:117px;
    margin-bottom:30px;
}

.queryBlock5{
    margin-right:100px;
    top:20px;
    left:30px;
}

.queryBlock6{
    
    top:20px;
    left:30px;
}

</style>