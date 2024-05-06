<template>
    <div class="input-container">
        <div class="column1">
        <!-- <h5>會員查找頁面</h5> -->
        <br><br>      
            <!-- <label for="input0">流水號</label>
            <input type="number" id="input0" v-model="id"><br> -->
            <label for="input1">會員ID</label>
            <input type="text" id="input1" v-model="memberId"><br>
        </div>
        <div class="column2">
            <label for="input2">姓名</label>
            <input type="text" id="input2" v-model="memberName"><br>
            <label for="input3">Email</label>
            <input type="text" id="input3" v-model="email"><br>
        </div>
        <div class="column3">  
            <label for="input4">地址</label>
            <input type="text" id="input4" v-model="memberAddress"><br>
            <label for="input5">權限</label>
            <select id="input5" v-model="accessLevel">
                <option value="">不限</option>
                <option value="customer">顧客</option>
                <option value="worker">員工</option>
                <option value="manager">主管</option>
                <option value="admin">系統管理員</option>
            </select><br>
        </div>
        <button type="submit" v-on:click="loadMembers">Submit查詢</button>
    </div>
    <div class="space">
    </div>

    <div class="result">
      <br>
      <h5>會員資料查詢結果</h5> 
       <table class="table table-bordered">
        <thead>
            <tr>
                <th style="width:55px;">查看</th>
                <th>流水號</th>
                <th>權限</th>
                <th>會員ID</th>
                <th>姓名</th>
                <th>Email</th>
                <th>性別</th>
                <th>地址</th>
                <th>剩餘紅利</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="bean in members" v-bind:key="id">
                <td class="img-center">
                    <RouterLink to="/backend/memberFind2"  v-on:click="propUserData(bean)" v-bind:prop="bean.memberId"><img src="..\assets\magnifier.png"/></RouterLink></td>              
                <td>{{ bean.id }}</td>
                <td>{{ bean.accessLevel }}</td>
                <td>{{ bean.memberId }}</td>
                <td>{{ bean.memberName }}</td>
                <td>{{ bean.email }}</td>
                <td>{{ genderReader(bean.gender) }}</td>
                <td>{{ bean.memberAddress }}</td>
                <td>{{ bean.discountPointsLeft }}</td>                 
            </tr>
        </tbody>
       </table>
       <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item" @click="clickHandler(value)" 
                    v-for="(value, index) in totalPages" v-bind:key="index">
                        <a v-bind:class="{ 'page-link': true, 'currentPage': (x+1) === value }">
                            {{ value }}
                        </a>
                </li>
            </ul>
        </nav>
    </div> 
</template>

<script setup>
import { ref , reactive } from 'vue';
import axios from 'axios';

//準備Axios給後端的搜尋條件的變數宣告
let id = ref(null);
let memberId = ref(null);
let memberName = ref(null);
let email = ref(null);
let memberAddress = ref(null);
let accessLevel = ref(null);

let start = ref(0);
let rows = ref(5); //一頁5筆
let sortOrder = ref('asc');
let sortType = ref('id');

const members = ref([]);
const totalPages = ref(0);

function propUserData(bean){
    let prop = {
        id: bean.id,
        memberId: bean.memberId,
        memberName: bean.memberName,
        memberPassword: bean.memberPassword,
        email: bean.email,
        gender: bean.gender,
        memberAddress: bean.memberAddress,
        discountPointsLeft: bean.discountPointsLeft,
        accessLevel: bean.accessLevel,
        signUpDate: bean.signUpDate,
    }
    sessionStorage.setItem('prop', JSON.stringify(prop)); //用sessionStoreage傳值
    localStorage.setItem('memberId', bean.memberId)
    // console.log('bean.1= ' + bean.id)
    // console.log('bean.2= ' + bean.memberId)
    // console.log('bean.3= ' + bean.memberName)
    // console.log('bean.4= ' + bean.email)
    // console.log('bean.5= ' + bean.gender)
    // console.log('bean.6= ' + bean.memberAddress)
    // console.log('bean.7= ' + bean.discountPointsLeft)
    // console.log('bean.8= ' + bean.accessLevel)
    // console.log('bean.9= ' + bean.signUpDate)
    
}

//把值塞入一個Json，給Axios送後端find()用
const datas = reactive({
    "id": id,
    "memberId": memberId,
    "memberName": memberName,
    "email": email,
    "memberAddress": memberAddress,
    "accessLevel": accessLevel,
    
    "start": start,
    "rows": rows,
    "order": sortOrder,
    "sort": sortType,
})

//設定頁面切換
let x = ref(0);
const clickHandler = page =>{
    x = (page-1)
    datas.start = (page-1)*datas.rows //每頁的第一筆是在總筆數的哪一筆
    loadMembers()
}
    
const URL = import.meta.env.VITE_API_JAVAURL
async function loadMembers(){
    // console.log('datas=')
    // console.log(datas)
    const response =  await axios.post(`${URL}members/find`, datas)     
    
    //取得會員資料
    members.value = response.data.list 
    console.log('members.value= ')
    console.log(members.value)
    // console.log('typeof member.value= '+ typeof members.value)
    // console.log('member.value[0]= ')
    // console.log(members.value[0])
    // console.log('typeof member.value[0]= '+ typeof members.value[0])
    
    //計算總共有幾頁
    // console.log('SQL count= '+ response.data.count)
    totalPages.value = datas.rows === 0 ? 1 : Math.ceil(response.data.count/datas.rows)
}
// loadMembers();

function genderReader(gender){
    switch(gender){
        case 0:
            return "不明"
        case 1:
            return "男性"
        case 2:
            return "女性"
        case 3:
            return "雙性"
        case 4:
            return "無性"
    
    }
    return "不明"
} 
</script>

<style scoped>
label{
    width: 56px;
    display: inline-block;
    text-align: left;
}
select {
    height: 30px;
    width: 178px;
}
button {
    margin-left: 55px;
    margin-top: 0px;
}

.input-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.input-container div {
  flex: 0 0 calc(30% - 10px); /* 30% width with a little spacing */
  margin-bottom: 10px;
  position: relative;
}
.column2,
.column3 {
    margin-top:3.2em;
}




.space {
  margin-bottom: 10px; /* Add margin at the bottom of the last div */
}
.pagination li {
    cursor: pointer;
}
.currentPage {
    background-color: lightblue;
}
table img {
    height: 1.5em;
    
}
td.img-center{
    text-align: center;
}
</style>

