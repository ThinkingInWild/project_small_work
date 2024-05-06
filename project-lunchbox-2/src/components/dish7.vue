<template>
    <img :src="'src/pictures/s' + pictureNumber + '.jpg'" @mouseover="changePicture1" @mouseleave="changePicture2">
</template>

<script setup>
import { ref } from 'vue';

const pictureNumber = ref(7);

function changePicture1() {

    pictureNumber.value = 17;

}

function changePicture2() {
    pictureNumber.value = 7;

}
</script>

<style>

</style>