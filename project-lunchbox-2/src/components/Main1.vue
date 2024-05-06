<template>
    <img :src="'src/pictures/m' + pictureNumber + '.png'" @mouseover="changePicture1" @mouseleave="changePicture2">
</template>
    
<script setup>
import { ref } from 'vue';

const pictureNumber = ref(1);



function changePicture1() {
    if(inventoryInformation.value.rChicken === 0){
    pictureNumber.value = 111;
}else{
    pictureNumber.value = 11;
}

}

function changePicture2() {
    if(inventoryInformation.value.rChicken === 0){
    pictureNumber.value = 111;
}else{
    pictureNumber.value = 1;
}
}

const props = defineProps({inventoryCheck:null})                      
const inventoryInformation = ref()
inventoryInformation.value = props.inventoryCheck;

// console.log(inventoryInformation);
// console.log(inventoryInformation.rChicken);
if(inventoryInformation.value.rChicken === 0){
    pictureNumber.value = 111;
}

</script>
    
<style scoped></style>