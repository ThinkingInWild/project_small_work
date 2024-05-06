<template>
    <img :src="'src/pictures/s' + pictureNumber + '.jpg'" @mouseover="changePicture1" @mouseleave="changePicture2">
</template>

<script setup>
import { ref } from 'vue';

const pictureNumber = ref(2);

function changePicture1() {

    pictureNumber.value = 12;

}

function changePicture2() {
    pictureNumber.value = 2;

}
</script>

<style scoped>

</style>