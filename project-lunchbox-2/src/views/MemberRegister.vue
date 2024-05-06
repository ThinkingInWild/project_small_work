<template>
    <br>
    <div class="container mb3">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6"> 
                <h5 style="padding-left:20px;">會員註冊頁面</h5><br>
                <label for="input0" >會員ID</label>
                <input type="text" id="input0" placeholder="請輸入手機號碼" v-model="data.memberId">
                <span v-if="!isMemberIdValid" class="error-message">&lt;--請符合臺灣手機號碼格式10碼</span><br>

                <label for="input1">姓名</label>
                <input type="text" id="input1" v-model="data.memberName">
                <span v-if="!isMemberNameValid" class="error-message">&lt;--姓名需至少2個字元</span><br>
                
                <label for="input2">密碼</label>
                <input type="password" id="input2" v-model="data.memberPassword">
                <span v-if="!isPasswordValid" class="error-message">&lt;--密碼最少4位, 含1位數字及1位英文字</span><br>

                <label for="confirm_input2">確認密碼</label>
                <input type="password" id="confirm_input2" v-model="data.confirmPassword">
                <span v-if="!passwordMatch" class="error-message">&lt;--請輸入相同密碼</span><br>

                <label for="input3">Email</label>
                <input type="text" id="input3" v-model="data.email"><br>

                <label for="input4">性別</label>
                <select id="input4" v-model="data.gender">
                <option value= "0" ></option>
                <option value= "1" >男性</option>
                <option value= "2" >女性</option>
                <option value= "3" >雙性</option>
                <option value= "4" >無性</option>
                </select><br>          
                <!-- <input type="number" id="input4" v-model="data.gender"><br> -->
                
                <label for="input5">地址</label>
                <input type="text" id="input5" v-model="data.memberAddress"><br>

                <!-- <RouterLink to="/memberRegister2"><button type="submit" v-on:click="registerMember">確定註冊</button></RouterLink> -->
                <button type="submit" v-on:click="registerMember">確定註冊</button>
            </div>
            <div class="col-sm-3"></div>
        </div>
      </div> 
</template>

<script setup>
import { reactive, computed } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router'
const router = useRouter();

const data = reactive({
    id: 0,
    memberId: '0988119119',
    memberName: 'alex',
    memberPassword: 'a123',
    confirmPassword:'a123',
    email: 'aa@gmail',
    gender: null,
    memberAddress: '',
    discountPointsLeft: 0,
    accessLevel: 'customer',
    couponQuantity: 2,
    signUpDate: new Date(),
})

//檢查輸入欄位的正確性
const isMemberIdValid = computed(()=>{
    const memberId = data.memberId;
    const memberIdRegex = /^09\d{8}$/; // Regular expression to match 09 followed by 8 digits
    // Check if memberId is not empty and matches the required format
    return memberId && memberIdRegex.test(memberId);
})

const isMemberNameValid = computed(()=>{
    const name = data.memberName;
    // Check if name is not empty and has at least 2 characters
    return name && name.length >= 2;
})

const isPasswordValid = computed(() => {
    const password = data.memberPassword
    // Password must be at least 4 characters long
    if (password.length < 4) {
        return false;
    }
    // Password must contain at least one letter and one number
    const letterRegex = /[a-zA-Z]/;
    const numberRegex = /[0-9]/;
    if (!letterRegex.test(password) || !numberRegex.test(password)) {
        return false;
    }
    // Password can contain special characters if needed, add special character regex if necessary
    return true;
})

const passwordMatch = computed(() => {
    return data.memberPassword === data.confirmPassword;
});
//使用Axios
const URL = import.meta.env.VITE_API_JAVAURL
async function registerMember(){
    
    if (!isMemberIdValid.value) { 
    console.error('請符合臺灣手機號碼格式10碼');
    return;
    }
    if (!isMemberNameValid.value) { 
    console.error('姓名需至少2個字元');
    return;
    }
    if (!isPasswordValid.value) { 
    console.error('密碼最少4個字元,至少各需要一個英文字、一個數字');
    return;
    }
    if (!passwordMatch.value) {
    console.error('密碼兩欄輸入不一致');
    return;
    }
    console.log('給Axios的資料= ' + JSON.stringify(data))
    const response =  await axios.post(`${URL}members`, data) 
    console.log('回來的訊息' + JSON.stringify(response))
    router.push('/memberRegister2')
}
</script>

<style scoped>
input  {
    padding:8px;
}

.mb-3 {
    margin: 0 auto; /* This centers the element horizontally */
    text-align: center; /* This centers the content inside the element */
    
}
label{
    width: 70px;
    display: inline-block;
    text-align: right;
    margin-right: 5px;
}
select {
    height: 42px;
    width: 195px;
}
button {
    width: 100px;
    margin-left: 75px;
    margin-top: 20px;
}
.error-message {
    color: rgb(223, 82, 82);
    margin-left: 3px;
}
</style>