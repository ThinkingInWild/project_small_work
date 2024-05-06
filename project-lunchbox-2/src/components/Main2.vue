<template>
    <img :src="'src/pictures/m' + pictureNumber + '.png'" @mouseover="changePicture1" @mouseleave="changePicture2"> 
</template>

<script setup>
import { ref } from 'vue';

const pictureNumber = ref(2);

function changePicture1() {
    if(inventoryInformation.value.rPork === 0){
    pictureNumber.value = 222;
    }else{
    pictureNumber.value = 12;
    }
}

function changePicture2() {
    if(inventoryInformation.value.rPork === 0){
    pictureNumber.value = 222;
    }else{
    pictureNumber.value = 2;
    }
}

const props = defineProps({inventoryCheck:null})    
const inventoryInformation = ref()
inventoryInformation.value = props.inventoryCheck;

// console.log(inventoryInformation);
// console.log('PorkInventory: ' + inventoryInformation.rPork);
if(inventoryInformation.value.rPork === 0){
    pictureNumber.value = 222;
}
</script>

<style scoped>

</style>