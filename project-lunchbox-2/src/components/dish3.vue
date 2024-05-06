<template>
    <img :src="'src/pictures/s' + pictureNumber + '.jpg'" @mouseover="changePicture1" @mouseleave="changePicture2">
</template>

<script setup>
import { ref } from 'vue';

const pictureNumber = ref(3);

function changePicture1() {

    pictureNumber.value = 13;

}

function changePicture2() {
    pictureNumber.value = 3;

}
</script>

<style scoped>

</style>