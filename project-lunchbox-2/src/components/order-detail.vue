<template>
    <div>
        <input type = "text" v-model = "body.orderNumber">
    </div>
    <button @click = "abcd">查詢</button>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>流水號</th>
                <th>訂單ID</th>
                <th>主菜</th>
                <th>配菜1</th>
                <th>配菜2</th>
                <th>配菜3</th>
                <th>配菜4</th>   
                <th>便當總價</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for= "product in products" :key = product.orderNumber>
                <td>{{product.id}}</td>
                <td>{{product.orderNumber}}</td>
                <td>{{product.main}}</td>
                <td>{{product.side1}}</td>
                <td>{{product.side2}}</td>
                <td>{{product.side3 }}</td>
                <td>{{product.side4}}</td>
                <td>{{product.mainPrice }}</td>
            </tr>
           
        </tbody>
       </table>

       <Paging :totalPages="totalPages" :thePage="cP + 1" @abcde="clickHandler"></Paging>



</template>

<script setup>

import{ref, reactive} from 'vue';
import axios from 'axios';
import Paging from '../components/paging.vue';



const body = reactive({

orderNumber:null,

    start:0,
    rows:3,
    order: "desc",
    sort: "id"

})

body.orderNumber = sessionStorage.getItem("orderNumber");
console.log(body.orderNumber);

const cP = ref(0);                        //分頁功能

const clickHandler = page => {            //分頁功能
    cP.value = page - 1
    body.start = (page - 1) * body.rows;
    callFindBy();
};


function abcd(){
console.log(body.orderNumber);
if(body.orderNumber === ""){
    body.orderNumber = null;
}
console.log(body.orderNumber);
callFindBy();
}


const totalPages = ref(0);                //分頁功能
const products = ref([]);                 //宣告一個陣列接response。

const URL = import.meta.env.VITE_API_whateverURL


async function callFindBy(){
    const response = await axios.post(`${URL}detailAndMain/find`, body)
    // const response = await axios.get(`${URL}member/` + id.value)
    // const response = await axios.get("http://localhost:8080/pages/member/1")
    console.log(response);
    // console.log(response.data.list[0].orderNumber);
    console.log(body.orderNumber);
    products.value = response.data.list

    totalPages.value = body.rows === 0 ? 1 : Math.ceil(response.data.count / body.rows) //分頁功能
};

callFindBy();

</script>
<style>
    
</style>