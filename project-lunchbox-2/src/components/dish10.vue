<template>
    <img :src="'src/pictures/s' + pictureNumber + '.jpg'" @mouseover="changePicture1" @mouseleave="changePicture2">
</template>

<script setup>
import { ref } from 'vue';

const pictureNumber = ref(10);

function changePicture1() {

    pictureNumber.value = 20;

}

function changePicture2() {
    pictureNumber.value = 10;

}
</script>

<style>

</style>