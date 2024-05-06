<template>
    <img :src="'src/pictures/m' + pictureNumber + '.png'" @mouseover="changePicture1" @mouseleave="changePicture2">      
</template>
    
<script setup>
    import { ref } from 'vue';

const pictureNumber = ref(5);

function changePicture1() {
    if(inventoryInformation.value.rLamb === 0){
    pictureNumber.value = 555;
    }else{
    pictureNumber.value = 15;
    }
}

function changePicture2() {
    if(inventoryInformation.value.rLamb === 0){
    pictureNumber.value = 555;
    }else{
    pictureNumber.value = 5;
    }
}

const props = defineProps({inventoryCheck:null})                      
const inventoryInformation = ref()
inventoryInformation.value = props.inventoryCheck;

// console.log(inventoryInformation);
// console.log(inventoryInformation.rLamb);
if(inventoryInformation.value.rLamb === 0){
    pictureNumber.value = 555;
}
</script>
    
<style scoped>
    
</style>