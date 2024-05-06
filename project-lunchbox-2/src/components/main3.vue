<template>
     <img :src="'src/pictures/m' + pictureNumber + '.png'" @mouseover="changePicture1" @mouseleave="changePicture2">    
</template>
    
<script setup>
    import { ref } from 'vue';

const pictureNumber = ref(3);

function changePicture1() {
    if(inventoryInformation.value.rFish === 0){
    pictureNumber.value = 333;
    }else{
    pictureNumber.value = 13;
    }
}

function changePicture2() {
    if(inventoryInformation.value.rFish === 0){
    pictureNumber.value = 333;
    }else{
    pictureNumber.value = 3;
    }
}

const props = defineProps({inventoryCheck:null})    
const inventoryInformation = ref()
inventoryInformation.value = props.inventoryCheck;

// console.log(inventoryInformation);
// console.log(inventoryInformation.rFish);
if(inventoryInformation.value.rFish === 0){
    pictureNumber.value = 333;
}

</script>
    
<style scoped>
    
</style>