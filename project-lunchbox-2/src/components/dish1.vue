<template>
      
      <img :src="'src/pictures/s' + pictureNumber + '.jpg'" @mouseover="changePicture1" @mouseleave="changePicture2">
       
</template>
    
<script setup>
import { ref } from 'vue';

const pictureNumber = ref(1);

function changePicture1() {

    pictureNumber.value = 11;

}

function changePicture2() {
    pictureNumber.value = 1;

}

</script>
    
<style scoped>

</style>