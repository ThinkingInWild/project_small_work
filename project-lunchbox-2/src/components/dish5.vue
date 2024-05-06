<template>
    <img :src="'src/pictures/s' + pictureNumber + '.jpg'" @mouseover="changePicture1" @mouseleave="changePicture2">
</template>

<script setup>
import { ref } from 'vue';

const pictureNumber = ref(5);

function changePicture1() {

    pictureNumber.value = 15;

}

function changePicture2() {
    pictureNumber.value = 5;

}
</script>

<style scoped>

</style>