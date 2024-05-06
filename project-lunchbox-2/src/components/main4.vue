<template>
      <img :src="'src/pictures/m' + pictureNumber + '.png'" @mouseover="changePicture1" @mouseleave="changePicture2">    
</template>
    
<script setup>
    import { ref } from 'vue';

const pictureNumber = ref(4);

function changePicture1() {
    if(inventoryInformation.value.rBeef === 0){
    pictureNumber.value = 444;
    }else{
    pictureNumber.value = 14;
    }
}

function changePicture2() {
    if(inventoryInformation.value.rBeef === 0){
    pictureNumber.value = 444;
    }else{
    pictureNumber.value = 4;
    }    
}

const props = defineProps({inventoryCheck:null})                      
const inventoryInformation = ref()
inventoryInformation.value = props.inventoryCheck;

// console.log(inventoryInformation);
// console.log(inventoryInformation.rBeef);
if(inventoryInformation.value.rBeef === 0){
    pictureNumber.value = 444;
}
</script>
    
<style scoped>
    
</style>