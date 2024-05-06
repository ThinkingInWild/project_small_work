<template>
    <img :src="'src/pictures/s' + pictureNumber + '.jpg'" @mouseover="changePicture1" @mouseleave="changePicture2">
</template>

<script setup>
import { ref } from 'vue';

const pictureNumber = ref(8);

function changePicture1() {

    pictureNumber.value = 18;

}

function changePicture2() {
    pictureNumber.value = 8;

}
</script>

<style scoped>

</style>