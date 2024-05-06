<template>
<!-- Modal Start-->
<div class="modal" id="memberModifyModal" tabindex="-1" aria-labelledby="memberModifyModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="memberModifyModalLabel">會員資料修改</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
<!-- Modal -->
 
        <!-- <div><a @click="loadData()" style="cursor: pointer; color: rgb(0, 68, 255);">載入資料</a></div><br> -->
        <div>
            <form>
            <label>會員ID</label>
            
            <input type="text" id="input1" v-model="data.memberId" readonly>
            <span v-if="!isMemberIdValid" class="error-message">&lt;--請符合臺灣手機號碼格式10碼</span><br>

            <label>姓名</label>
            <input type="text" id="input2" v-model="data.memberName">
            <span v-if="!isMemberNameValid" class="error-message">&lt;--姓名需至少2個字元</span><br>

            <label>新密碼</label>
            <input type="text" id="input3" v-model="data.memberPassword">
            <span v-if="!isPasswordValid" class="error-message">&lt;--密碼最少4位, 含1位數字及1位英文字</span><br>

            <label>確認新密碼</label>
            <input type="text" id="input3c" v-model="data.confirmPassword">
            <span v-if="!passwordMatch" class="error-message">&lt;--請輸入相同密碼</span><br>

            <label>Email</label>
            <input type="text" id="input4" v-model="data.email"><br>

            <label>性別</label>
            <select id="input5" v-model="data.gender">
            <option value= "0" ></option>
            <option value= "1" >男性</option>
            <option value= "2" >女性</option>
            <option value= "3" >雙性</option>
            <option value= "4" >無性</option>
            </select><br> 
            
            <label>地址</label>
            <input type="text" id="input6" v-model="data.memberAddress"><br>
            
            <!-- <label for="input5">加入日期</label>
            <input type="text" id="input5" v-model="data.signUpDate"><br> -->

            <!-- <button type="submit" v-on:click="modifyMember">確定修改</button> -->

            </form>
            <br>
            
        </div>

<!-- Modal -->    
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary custom-btn-primary" data-bs-dismiss="modal" v-on:click="modifyMember">儲存修改</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" v-on:click="$emit('close-modal')" >Close</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal End -->

</template>
    
<script setup>
import { ref, reactive, computed, watch, onMounted } from 'vue';
import axios from 'axios';

//初始化data物件
const data = reactive({
    id: 0,
    memberId: '',
    memberName: '',
    memberPassword: '',
    confirmPassword:'',
    email: '',
    gender: null,
    memberAddress: '',

    // discountPointsLeft: null,
    // accessLevel: '',  
})

const data2 = reactive({
    memberId: '',
})


//取用已儲存在localStorage的資料
function loadData() {
    
    // console.log('loadData function is called');

    data.id = localStorage.getItem('id')
    data.memberId = localStorage.getItem('memberId')
    data.memberName = localStorage.getItem('memberName')
    data.memberPassword = localStorage.getItem('memberPassword')
    data.confirmPassword = localStorage.getItem('memberPassword')
    data.email = localStorage.getItem('email')
    data.gender = localStorage.getItem('gender')
    data.memberAddress = localStorage.getItem('memberAddress')

    data2.memberId=localStorage.getItem('memberId')
}

const props = defineProps({
    status: Number
})
watch(()=>props.status, (newStatus, oldStatus) => {
    console.log(`props.status從 ${oldStatus} 變為 ${newStatus}`)
    loadData()
    console.log('watch3',data)
});



//使用Axios
const URL = import.meta.env.VITE_API_JAVAURL
async function modifyMember(){
    const tempData = ref([]);
    // const tempData2 = ref([]);
    if (!isPasswordValid.value) { 
    console.error('密碼最少4個字元,至少各需要一個英文字、一個數字');
    return;
    }
    if (!passwordMatch.value) {
    console.error('密碼兩欄輸入不一致');
    return;
    }
    console.log('給Axios的資料= ' + JSON.stringify(data2))
    const response =  await axios.post(`${URL}members/find`, data2) 
    console.log('data2回來的訊息' + JSON.stringify(response))
    tempData.value = JSON.parse(JSON.stringify(response.data.list[0]))
    console.log('tempData=response.data.list[0]= ')
    console.log(tempData.value)
    console.log('id=' + tempData.value.id)

    const response2 = await axios.put(`${URL}members/${tempData.value.id}`, data)
    console.log(response2.data)
    // data.id = 0;
    // data.memberId = '';
    // data.memberName = '';
    // data.memberPassword = '';
    // data.confirmPassword = '';
    // data.email = '';
    // data.gender = null;
    // data.memberAddress = '';

    // data2.memberId=0;
}



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
    // Allow empty password
    if (!password) {
        return true;
    }
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


onMounted(()=>{
  loadData()
})
</script>


<style scoped>
label{
    font-size: 20px;
}
.modal-content {
    width: 700px; /* Set your desired width here */
    margin: 200px auto; /* Center the modal horizontally */
}
label{
    width: 120px;
    display: inline-block;
    text-align: right;
    margin-right: 10px;
}
select {
    height: 30px;
    width: 182px;
}
button {
    width: 100px;
    margin-left: 90px;
    margin-top: 20px;
}
.error-message {
    color: rgb(223, 82, 82);
    margin-left: 3px;
}
</style>