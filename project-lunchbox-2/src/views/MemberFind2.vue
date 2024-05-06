<template>
        <br><br>
        <div class="container"><!-- 切割畫面 -->
            <div class="row">
                
                <!-- Column One -->
                <div class="col-sm">
                    <h5>個別會員資料</h5>
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                            <th>流水號</th>
                            <td>{{ propObj.id }}</td>
                            </tr>
                            <tr>
                            <th>會員ID</th>
                            <td>{{ propObj.memberId }}</td>
                            </tr>
                            <tr>
                            <th>姓名</th>
                            <td>{{ propObj.memberName }}</td>
                            </tr>
                            <tr>    
                            <th>Email</th>
                            <td>{{ propObj.email }}</td>
                            </tr>
                            <tr>
                            <th>性別</th>
                            <td>{{ genderReader() }}</td>
                            </tr>
                            <tr>
                            <th>地址</th>
                            <td>{{ propObj.memberAddress }}</td>
                            </tr>
                            <tr>
                            <th>剩餘紅利</th>
                            <td>{{ propObj.discountPointsLeft }}</td>
                            </tr>
                            <tr>
                            <th>加入日期</th>
                            <td>{{ propObj.signUpDate }}</td>
                            </tr>
                            <tr>
                            <th>權限</th>
                            <td>{{ propObj.accessLevel }}</td> 
                            </tr>
                        </tbody>
                    </table>
                </div>
                
                <!-- Column Two -->
                <div class="col-sm">
                    <!-- <span><a v-on:click="toggle1" v-bind:class="rewardClass" >檢視紅利</a></span> -->
                    <!-- <span><a v-on:click="toggle2" v-bind:class="purchaseClass" >檢視訂單</a></span> -->

                    <!-- <Reward v-if="isRewardSelected"></Reward>
                    <Purchase v-if="isPurchaseSelected"></Purchase> -->
                    
                    <span><RouterLink to="/backend/memberFind2/purchase" >檢視訂單</RouterLink></span>
                    <span><RouterLink to="/backend/memberFind2/reward" >檢視紅利</RouterLink></span>
                    <RouterView></RouterView>

                </div>
                
                
                
            
            </div>
            
        </div>
        
        <div class="space"></div>
</template>
    
<script setup>
import { ref, computed } from 'vue'
// import Reward from './Reward.vue'
// import Purchase from './Purchase.vue'

// const isRewardSelected = ref(false);
// const isPurchaseSelected = ref(false);
// function toggle1(){isRewardSelected.value=true; isPurchaseSelected.value=false;}
// function toggle2(){isPurchaseSelected.value=true; isRewardSelected.value=false;}
// const rewardClass = computed(()=>{ return {'selected-link' : isRewardSelected.value} })
// const purchaseClass = computed(()=>{ return {'selected-link' : isPurchaseSelected.value} })


let propObj = JSON.parse(sessionStorage.getItem('prop'))  //接收存在sessionStorage的資料

function genderReader(){
    switch(propObj.gender){
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
label {
    width: 70px;
    display: inline-block;
    text-align: right;
    margin-right: 5px;
}
button {
    width: 100px;
    margin-left: 75px;
    margin-top: 20px;
}
div a{
    cursor: pointer;
    font-size:20px;
    text-decoration: none;
    color: inherit;
    margin-right: 20px;
}
.selected-link {
  background-color: rgb(211, 211, 211);
  /* Add any other styles you want for the selected links */
}
.space {
  margin-bottom: 200px;   /* Add margin at the bottom of the last div */
}
</style>