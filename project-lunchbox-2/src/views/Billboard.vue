<template>
   <div>
    <div class="message-board">      
      
        <div class="part-title">發送留言</div>
      <div class="form-panel">
            <form>
                <div class="user-name">
                    暱稱：<input type="input" id="nick-name-input" v-model.trim="nickName"/>
                </div>
                <div class="input-panel">
                    <div>留言：</div>
                    <textarea id="message-input" v-model="message"></textarea>
                    <a href="javascript:void(0)" v-on:click="sendMsg()" class="send-btn">發送</a
                    >
                </div>
            </form>
        </div>

        <div class="part-title">留言列表</div>
        <div id="message-list">
            <div class="no-data" v-if="messageList.length == 0">暫無留言、歡迎留言</div>
                <div class="message-item" v-for="(item, index) in messageList">
                    <div class="nick-name">
                        <span>{{ item.alias }}</span>
                        <span class="send-time">{{ item.messageTime }}</span>
                        <!-- <span class="del" @click="delMessage(index)">删除</span> -->
                    </div>
                    <div class="message">
                        {{ item.content }}
                    </div>
                </div>
            </div>
        </div>      
    </div>
</template>


<script setup>
import { ref, reactive } from "vue";
import axios from 'axios';
const messageList = ref([]);
const nickName = ref();
const message = ref();
const URL = import.meta.env.VITE_API_JAVAURL

async function loadMsg(){
    const data = reactive({
    id: 0,
    alias: null,
    content: null,
    messageTime: null,
    })
    const response =  await axios.post(`${URL}billboard/find`, data)
    // console.log(response.data.list)
    messageList.value=response.data.list
}

loadMsg()

async function sendMsg() {
    const nickNameDomValue = nickName.value;
    const messageDomValue = message.value.trim();
    if (nickNameDomValue === "") {
        alert("请输入昵称");
        return;
    }
    if (messageDomValue === "") {
        alert("请输入留言内容");
        return;
    }
    messageList.value.push({ //新增到此array尾端
        nickName: nickNameDomValue,
        message: messageDomValue,
        sendTime: new Date().toLocaleString(),
    });
    
    //console.log('messageList.value= '+ JSON.stringify(messageList.value))
    const data = reactive({
        id: 0,
        alias: nickName.value,
        content: message.value,
        messageTime: new Date(),
        // messageTime: new Date().toLocaleString(),
    })

    console.log('XXXXX= '+data.messageTime)
    console.log('給Axios的資料= ' + JSON.stringify(data))
    const response =  await axios.post(`${URL}billboard`, data) 
    console.log('回來的訊息' + JSON.stringify(response.data))

    nickName.value = "";
    message.value = "";
    loadMsg()
};

const delMessage = (index) => {
  messageList.value.splice(index, 1);
};
</script>


<style scoped>
body {
  text-align: center;
  background: #ddd;
}

.message-board {
  background: #fff;
  margin: 0px auto;
  width: 1000px;
  text-align: left;
  padding: 20px;
}

.no-data {
  color: rgb(63, 63, 63);
  text-align: center;
}

.message-item {
  border-bottom: 1px solid #ddd;
  padding: 12px;
}

.message-item .nick-name {
    
  color: rgb(81, 80, 80);
  font-size: 18px;
}

.send-time {
  margin-left: 10px;
  font-size: 13px;
}

.del {
  cursor: pointer;
  color: red;
  margin-left: 10px;
}

.message-item .message {
  margin-top: 5px;
  font-size: 16px;
}

.part-title {
  font-size: 18;
  border-left: 2px solid rgb(16, 106, 202);
  padding-left: 10px;
  font-weight: bold;
  margin-top: 10px;
}

.input-panel {
  display: flex;
  align-items: center;
  margin-top: 5px;
}

.input-panel textarea {
  height: 50px;
  width: 100%;
  flex: 1;
  padding: 0px;
  resize: none;
}

.input-panel .send-btn {
  width: 51px;
  height: 51px;
  line-height: 50px;
  background: rgb(8, 141, 194);
  color: #fff;
  text-decoration: none;
  text-align: center;
  margin-left: 10px;
}
</style>





