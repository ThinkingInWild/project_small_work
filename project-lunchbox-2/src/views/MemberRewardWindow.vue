<template>
        <div class="container"><!-- 切割畫面 -->
            <div class="row">
                
                <!-- Column One -->
                <!-- <div class="col-sm" v-show="!showOrderContent"  @click="showOrderContent = true">
                    <Purchase></Purchase>
                    
                </div>
                <div class="col-sm" v-show="showOrderContent" @click="showOrderContent = false">
                    <OrderContent ></OrderContent>
                </div> -->

                <div class="col-sm">                
                <RouterView></RouterView>  
                </div>
                <!-- Column Two -->
                <div class="col-sm">
                    <Reward></Reward>
                    
                </div>
            </div>
        </div>
</template>
    
<script setup>
import { ref } from "vue"
import Reward from './Reward.vue'
import Purchase from './Purchase.vue'
import OrderContent from './OrderContent.vue'

const showOrderContent = ref(false)

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

</style>
    
