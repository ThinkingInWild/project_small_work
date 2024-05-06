<template>
    <div class="outer-frame">
    <h1><i class="bi bi-cart-fill"></i>
        購物車:</h1>
    <table class="table">
    <thead>
        <tr>
            <th scope="col">米飯</th>
            <th scope="col">主菜</th>
            <th scope="col">配菜1</th>
            <th scope="col">配菜2</th>
            <th scope="col">配菜3</th>
            <th scope="col">配菜4</th>
            <th scope="col">價格</th>
            <th scope="col">刪除</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="(lunchboxOrder, index) in lunchboxOrders" :key = index >
            <!-- <td @click = "deliverInfo(product.orderNumber)"><RouterLink class="nav-link active"  to="/Order-Detail" >{{product.orderNumber}}</RouterLink></td> -->
            
            <td>{{lunchboxOrder.riceChoice}}</td>
            <td>{{lunchboxOrder.main}}</td>
            <td>{{lunchboxOrder.side1}}</td>
            <td>{{lunchboxOrder.side2}}</td>
            <td>{{lunchboxOrder.side3}}</td>
            <td>{{lunchboxOrder.side4}}</td>   
            <td>{{lunchboxOrder.price}}</td> 
            <td><button class="btn btn-danger"  @click="confirmDelete(index)">
                <i class="bi bi-trash-fill"></i>
                <!-- <i class="bi bi-cart-x-fill"></i> -->
            </button></td>   
        </tr>
    </tbody>
</table>

    <div>
            <p>你目前訂購了{{lunchboxOrders.length}}個便當</p>
            <p><i class="bi bi-cash-coin"></i>總共{{totalPrice}}元</p>
            <h3>本次消費獲得紅利點數{{acquairedBonus}}點</h3>
            <div>                                       
                <h3>是否要使用紅利點數?</h3>                              
                <button @click="showBonus" >是</button>     
                <button @click="notShowBonus">否</button>   
                </div>                                      
                <div v-show="showingBonus">                  
                    <h4>剩餘紅利點數:{{ showingCurrentBonus }}</h4>                        <!--新增-->               
                    <h4>使用紅利點數:<input type="number" v-model="pointsUsed" @input="showBonus">點</h4>    <!--新增--> 
                    <h4>折抵紅利後應付金額:{{totalMoneyAfterDiscount }}</h4>                <!--新增--> 
                </div>                                       
        </div>
       
    </div>
    
  <!-- <button @click="printLunchboxOrders">按一下</button> -->
  <button @click="confirmOrder">確認訂購</button>                             <!-- 新增的 -->

</template>

<script setup>
import {ref} from 'vue';
const lunchboxOrdersString = localStorage.getItem("putInShoppingCart");
console.log(lunchboxOrdersString);

let newLunchboxOrdersString;


const lunchboxOrders = ref();
lunchboxOrders.value = JSON.parse(lunchboxOrdersString);
console.log(lunchboxOrders.value);
//console.log(lunchboxOrders.value[0].price);

const totalPrice = ref(0);

function calculateTotalPrice(){
    if(lunchboxOrders.value === null){                                                                 //新增
        lunchboxOrders.value = [];
    }                                                 
    for(let i = 0; i < lunchboxOrders.value.length; i++) {
    console.log(lunchboxOrders.value.length);
    totalPrice.value = totalPrice.value + lunchboxOrders.value[i].price;
    console.log(totalPrice.value);
    }
}

calculateTotalPrice(); 



function deleteOrder(input){
console.log(input);
lunchboxOrders.value.splice(input, 1);
console.log(lunchboxOrders.value);
console.log('hahaha');
newLunchboxOrdersString = JSON.stringify(lunchboxOrders.value);
console.log(newLunchboxOrdersString);
localStorage.setItem("putInShoppingCart", newLunchboxOrdersString);
}



function confirmDelete(index) {
  // Show a confirmation dialog
  const confirmed = window.confirm('確定要刪除？');
  if (confirmed) {
    deleteOrder(index);
  }
}





import { reactive } from 'vue';
import axios from 'axios';
// axios.defaults.withCredentials=true;

const testObject = reactive({
    id:10000,                                                                               //修改
    orderNumber: 10012,
    memberId: null,
    orderAddress: '台北市',
    orderDate: null,
    pickupDate: null,
    orderTotal: null,
    chicken: 0,
    pork: 0,
    beef: 0,
    lamb: 0,
    fish: 0
})


console.log(lunchboxOrders.value)
if(lunchboxOrders.value.length !== 0){
    testObject.pickupDate = lunchboxOrders.value[0].pickupDate;
}

if(localStorage.getItem("orderNumber") === null){                                        //新增
localStorage.setItem("orderNumber", 10500);                            
};

const tempOrderNumber = ref();
// tempOrderNumber.value = localStorage.getItem('orderNumber');
// testObject.orderNumber = parseInt(tempOrderNumber.value);
testObject.orderTotal = totalPrice.value;

const acquairedBonus = ref();
acquairedBonus.value = Math.floor(testObject.orderTotal / 200) * 20       //滿兩百送20點紅利點數。 
console.log(acquairedBonus.value);

console.log(testObject);
let today = new Date();
testObject.orderDate = `${today.getFullYear()}-${today.getMonth() + 1}-${today.getDate()}`;

const pointsUsed = ref(0);
const totalMoneyAfterDiscount = ref();



const showingBonus = ref(false);
const showingCurrentBonus = ref();
function showBonus(){                                                                                 //新增
    showingBonus.value = true;
    callFindRewardByMemberId();

    console.log(totalPrice.value);
    console.log(pointsUsed.value);                                                                      //新增
    if(showingCurrentBonus.value < pointsUsed.value || totalPrice.value < pointsUsed.value){                                                   //新增
        alert("數學老師時常請假？");
        pointsUsed.value = Math.min(showingCurrentBonus.value, totalPrice.value);
    };
    totalMoneyAfterDiscount.value = totalPrice.value - pointsUsed.value;
}

function notShowBonus(){
    showingBonus.value = false;
}

function countMainDish(){
    testObject.chicken = 0;
    testObject.pork = 0;
    testObject.fish = 0;
    testObject.beef = 0;
    testObject.lamb = 0;

for(let i = 0; i < lunchboxOrders.value.length; i++) {
    if(lunchboxOrders.value[i].main === "雞"){
    testObject.chicken = testObject.chicken + 1;
    console.log('雞: ' + testObject.chicken);
    }else if(lunchboxOrders.value[i].main === "豬"){
    testObject.pork = testObject.pork + 1;
    console.log('豬: ' + testObject.pork);
    }else if(lunchboxOrders.value[i].main === "魚"){
    testObject.fish = testObject.fish + 1;
    console.log('魚: ' + testObject.fish);
    }else if(lunchboxOrders.value[i].main === "牛"){
    testObject.beef = testObject.beef + 1;
    console.log('牛: ' + testObject.beef);
    }else if(lunchboxOrders.value[i].main === "羊"){
    testObject.lamb = testObject.lamb + 1;
    console.log('羊: ' + testObject.lamb);
    }
    }
}

testObject.memberId = localStorage.getItem("memberId");

function confirmOrder() {                                                                   //這裡大改
    callFindPurchase();
    tempOrderNumber.value = localStorage.getItem('orderNumber');
    console.log("first: " + tempOrderNumber.value);
    testObject.orderNumber = parseInt(tempOrderNumber.value);
    console.log("second: " + testObject.orderNumber);
    testObject.memberId = localStorage.getItem("memberId");
    testObject.orderNumber = testObject.orderNumber + 1;
    localStorage.setItem('orderNumber', testObject.orderNumber);
    countMainDish();
    console.log(testObject);
    testObject.orderTotal = totalMoneyAfterDiscount.value;                                  //新增
    callInsertPurchase();
    
    putValueInReward();
    callInsertReward();
    putValueInDetail();
    callInsertDetail();
    putValueInInventory();
    callModifyInventoryByDate();
    
    


    lunchboxOrders.value = [];                                                               //清空購物車
    localStorage.setItem("putInShoppingCart", "[]")                                          //清空首頁小購物車
    returnToLoadIn();
    alert("訂購成功");
}



const PurchaseInsertInfo = ref();
const rewardInfo = ref();

function returnToLoadIn(){
    totalPrice.value = 0;
    pointsUsed.value = 0;
    totalMoneyAfterDiscount.value = totalPrice.value - pointsUsed.value;
    callFindRewardByMemberId();
}





const URL = import.meta.env.VITE_API_whateverURL

async function callInsertPurchase(){                                                            //將訂單寫進資料庫。
    const response = await axios.post(`${URL}purchase`, testObject);
    // const response = await axios.get(`${URL}member/` + id.value)
    // const response = await axios.get("http://localhost:8080/pages/member/1")
    console.log(response);
    // console.log(response.data.list[0].orderNumber);
    PurchaseInsertInfo.value = response.data.list
 
};


const useIt = reactive({                                                                        //修改
    memberId:null,
    
    sort:"id",
    order:"desc",
    start:0,
    rows:null
})

async function callFindRewardByMemberId(){  
    useIt.memberId = testObject.memberId;                                                         //修改  //查詢紅利點數。
    const response = await axios.post(`${URL}products/find`, useIt);                              //修改
    // const response = await axios.get(`${URL}member/` + id.value)
    // const response = await axios.get("http://localhost:8080/pages/member/1")
    console.log(response);
    console.log("會員Id: " + useIt.memberId)
    rewardInfo.value = response.data.list                                                          //新增
    console.log(rewardInfo.value[0]);
    showingCurrentBonus.value = rewardInfo.value[rewardInfo.value.length - 1].currentPoints;       //新增
 
};

const rewardToBeInserted = reactive({                                                               //新增
currentPoints:null,
id:null,
memberId:null,
orderNumber:null,
receivedDate:null,
receivedPoints:null,
usedDate:null,
usedPoints:null
})

function putValueInReward(){                                                                        //新增
    rewardToBeInserted.id = 10000;
    rewardToBeInserted.memberId = testObject.memberId;
    rewardToBeInserted.orderNumber = testObject.orderNumber;
    rewardToBeInserted.receivedDate = testObject.orderDate;
    rewardToBeInserted.receivedPoints = acquairedBonus.value;
    rewardToBeInserted.usedDate = testObject.pickupDate;
    rewardToBeInserted.usedPoints = pointsUsed.value;
    rewardToBeInserted.currentPoints = showingCurrentBonus.value + rewardToBeInserted.receivedPoints - rewardToBeInserted.usedPoints;
    console.log(rewardToBeInserted);
};

const rewardInsertedInfo = ref();

async function callInsertReward(){  
    useIt.memberId = testObject.memberId;                                                         //新增  //新增紅利點數資料。
    const response = await axios.post(`${URL}products`, rewardToBeInserted);                              
    // const response = await axios.get(`${URL}member/` + id.value)
    // const response = await axios.get("http://localhost:8080/pages/member/1")
    console.log(response);
    rewardInsertedInfo.value = response.data.list                                                          
    console.log(rewardInsertedInfo.value);       
};

function putValueInDetail(){                                                                        //新增
for(let i = 0; i <= lunchboxOrders.value.length - 1; i++){
lunchboxOrders.value[i].orderNumber = localStorage.getItem("orderNumber");
console.log("試試" + lunchboxOrders.value[i].orderNumber)
}
};

async function callInsertDetail(){                                                                  //新增  //新增便當明細資料。
     
    const response = await axios.post(`${URL}multipleDetails`, lunchboxOrders.value);           
    console.log(response);
    
};

const purchaseInfo = ref([]);
const purchaseBody = reactive({
    memberId:null,

    start:0,
    rows:3,
    order: "desc",
    sort: "orderNumber"
})

async function callFindPurchase(){                                                           //修改  查詢最近一筆orderNumber
    const response = await axios.post(`${URL}purchase/find`, purchaseBody);                              //修改
    console.log(response);
    purchaseInfo.value = response.data.list; 
    console.log("try一try: " + purchaseInfo.value[0].orderNumber); 
    localStorage.setItem("orderNumber", purchaseInfo.value[0].orderNumber);            //新增 
};

callFindPurchase();                                                                                     //新增

const inventoryInfo = ref();                                                                            //新增

const inventoryBody = reactive({                                                                        //新增
    inventoryDate:null,
    chicken: 0,
    pork: 0,
    beef: 0,
    lamb: 0,
    fish: 0

})

function putValueInInventory(){                                                                         //新增
    inventoryBody.inventoryDate = lunchboxOrders.value[0].pickupDate;
    inventoryBody.chicken = testObject.chicken,
    inventoryBody.pork = testObject.pork,
    inventoryBody.beef = testObject.beef,
    inventoryBody.lamb = testObject.lamb,
    inventoryBody.fish = testObject.fish
}

async function callModifyInventoryByDate(){                                                           //修改  //修改存貨。
    const response = await axios.put(`${URL}inventory`, inventoryBody);                              //修改
    console.log("看一下: " + inventoryBody.inventoryDate);
    console.log(response);
    inventoryInfo.value = response.data.list;  
};






</script>

<style scoped>
.outer-frame {
  border: 2px solid #000; /* 外框的边框样式 */
  padding: 20px; /* 外框内边距 */
  background-color: #f0f0f0; /* 外框的背景颜色 */
  border-radius: 10px; /* 外框的圆角 */
  margin: 20px; /* 外框与其他内容的间距 */
}
</style>