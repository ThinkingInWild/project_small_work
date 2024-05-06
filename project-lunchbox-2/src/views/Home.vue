<template>
    <!-- <body style="padding: 0 10%; background-color: rgb(202, 202, 25);"> -->
    <!-- 页面内容 -->
    <!-- </body> -->


    <!-- <H1>Login Page</H1> -->
    <NewCarousel></NewCarousel>
        <br>
    <!-- 便當盒格子 -->
    
    <div :class="lunchboxClassContent"  id="bottom-section">
    <img :src="'src/pictures/便當盒' + lunchboxPictures + '.png'" class="ricebox" alt="" height="500">
    </div>
    <!-- <img :src="'rice'+ rice"  style="position: absolute;" v-show="showOrNot" height="500"> -->
    <img :src="'src/pictures/rice' + rice" class="r"  style="position: absolute;" v-show="showOrNot">
    <img :src="'src/pictures/' + mainDish" class="m"  style="position: absolute;" v-show="showOrNotForMainDish">
    <img :src="'src/pictures/' + sideDish1" class="s" style="position: absolute;" v-show="showOrNotForSideDish1">
    <img :src="'src/pictures/' + sideDish2" class="d" style="position: absolute;" v-show="showOrNotForSideDish2">
    <img :src="'src/pictures/' + sideDish3" class="e" style="position: absolute;" v-show="showOrNotForSideDish3">
    <img :src="'src/pictures/' + sideDish4" class="f" style="position: absolute;" v-show="showOrNotForSideDish4">
    
    <img :src="'src/pictures/' + chopstickPillow" class="black" style="width: 100px; height:500px; ">


<div class="frame001">
    
    <div class="datePicker" >
        <li>
     <span>日期選單:</span>
         <label for="datePicker"></label>
        <input type="date" name="datePicker" v-model="body.inventoryDate" @change="findMainDishStockByDate">
     </li>
    </div>

<br>



<div>
    <button class="buttonForRandom"  @click="randomChooseAll"><i :class="qMark" @mousedown="changeQMark" @mouseup="changeQMark2"></i>隨便</button>
</div>

<br>

<div>
    <button class="buttonForClearLunchBox" @click="clearCurrentLunchbox">清空菜色</button>
</div>

<br>

    <div>
        <button class="buttonForAddToShoppingCart" @click="addToShopCart">加入購物車</button>
    </div>

</div>

    <br>
    <!-- 1 米飯 -->
    <div id="carouselExampleControlsNoTouching" class="carousel slide" data-bs-touch="false" data-bs-interval="false">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="d-flex justify-content-center align-items-center">
                    <img src="/底圖.jpg" style="border:0px rgb(255, 140, 0) solid;" class="d-block w-100" alt="底圖" width="150"
                        height="386">
                    <rice1 class="rice1-image" style="z-index: 1; position: absolute;  left:300px" @click="returnValueRice1" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"
                        alt="白米"></rice1>
                    <rice2 class="rice2-image" style="z-index: 1; position: absolute;  left:700px" @click="returnValueRice2" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"
                        alt="紫米"></rice2>
                </div>
            </div>



            <!-- 2 主菜 -->
            <div class="carousel-item">
                <div class="d-flex" style="position: relative;">
                    <img src="/底圖.jpg" style="z-index: 1; border:0px rgb(255, 140, 0) solid;" class="d-block w-100" alt="底圖"
                        width="150" height="386">
                    <basemap2 class="basemap2-image"
                        style="z-index:2;  position: absolute; top:90px; right:950px; border:0px rgb(255, 140, 0) solid;"
                        alt="main1底圖2"></basemap2>
                    <basemap2 class="basemap2-image"
                        style="z-index:2;  position: absolute; top:90px; right:750px;   border:6px  rgb(230, 187, 152) solid; "
                        alt="main2底圖2"></basemap2>
                    <basemap2 class="basemap2-image"
                        style="z-index:2;  position: absolute; top:90px; right:550px; border:0px rgb(255, 140, 0) solid;"
                        alt="main3底圖2"></basemap2>
                    <basemap2 class="basemap2-image"
                        style="z-index:2;  position: absolute; top:90px; right:350px; border:0px rgb(255, 140, 0) solid;"
                        alt="main4底圖2"></basemap2>
                    <basemap2 class="basemap2-image"
                        style="z-index:2;  position: absolute; top:90px; right:150px; border:6px rgb(230, 187, 152) solid;"
                        alt="main5底圖2"></basemap2>


                    <main1 class="main1-image" style="z-index:3; position: absolute;  top:90px; right:950px"
                        @click="returnValueMain1" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2" :inventoryCheck="remainingStock"></main1>
                    <main2 class="main2-image" style="z-index:3; position: absolute;  top:70px; right:710px"
                        @click="returnValueMain2" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2" :inventoryCheck="remainingStock"></main2>
                    <main3 class="main3-image" style="z-index:3; position: absolute;  top:90px; right:550px"
                        @click="returnValueMain3" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2" :inventoryCheck="remainingStock"></main3>
                    <main4 class="main4-image" style="z-index:3; position: absolute;  top:90px; right:350px"
                        @click="returnValueMain4" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2" :inventoryCheck="remainingStock"></main4>
                    <main5 class="main5-image" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2" style="z-index:3; position: absolute;  top:90px; right:150px"
                        @click="returnValueMain5" :inventoryCheck="remainingStock"></main5>

                </div>
            </div>








            <!-- 3 配菜 -->

            <div class="carousel-item">
                <div class="carousel-inner">
                    <div class="d-flex2">
                        <img src="/底圖.jpg" style="z-index: 1; border:0px rgb(255, 140, 0) solid;" class="d-block w-100"
                            alt="底圖" width="150" height="386">
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:50px; right:865px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish1底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:185px; right:865px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish2底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:50px; right:715px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish3底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:185px; right:715px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish4底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:50px; right:565px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish5底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:185px; right:565px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish6底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:50px; right:415px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish7底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:185px; right:415px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish8底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:50px; right:265px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish9底圖2"></basemap2>
                        <basemap2 class="basemap3-image"
                            style="z-index:2;  position: absolute; top:185px; right:265px; border:0px rgb(230, 187, 152) solid;"
                            alt="dish10底圖2"></basemap2>


                        <side1 class="dish1-image"
                            style="z-index:3;  position: absolute; top:50px; right:895px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish1底圖2" @click="returnValueSide1" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side1>
                        <side2 class="dish2-image"
                            style="z-index:3;  position: absolute; top:185px; right:895px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish2底圖2" @click="returnValueSide2" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side2>
                        <side3 class="dish3-image"
                            style="z-index:3;  position: absolute; top:50px; right:725px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish3底圖2" @click="returnValueSide3" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side3>
                        <side4 class="dish4-image"
                            style="z-index:3;  position: absolute; top:185px; right:725px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish4底圖2" @click="returnValueSide4" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side4>
                        <side5 class="dish5-image"
                            style="z-index:2;  position: absolute; top:50px; right:555px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish5底圖2" @click="returnValueSide5" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side5>
                        <side6 class="dish6-image"
                            style="z-index:2;  position: absolute; top:185px; right:555px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish6底圖2" @click="returnValueSide6" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side6>
                        <side7 class="dish7-image"
                            style="z-index:2;  position: absolute; top:50px; right:405px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish7底圖2" @click="returnValueSide7" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side7>
                        <side8 class="dish8-image"
                            style="z-index:2;  position: absolute; top:185px; right:405px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish8底圖2" @click="returnValueSide8" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side8>
                        <side9 class="dish9-image"
                            style="z-index:2;  position: absolute; top:50px; right:255px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish9底圖2" @click="returnValueSide9" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side9>
                        <side10 class="dish10-image"
                            style="z-index:2;  position: absolute; top:185px; right:255px; border:6px rgb(230, 187, 152) solid;"
                            alt="dish10底圖2" @click="returnValueSide10" @mouseover="changeChopstickPillow" @mouseleave="changeChopstickPillow2"></side10>
                    </div>
                </div>
            </div>
        </div>


        <div>
            <SmallShoppingCart :lunchboxesToSmallShoppingCart="selectedLunchBoxes"></SmallShoppingCart>
        </div>


        <!-- 幻影片 -->
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsNoTouching"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsNoTouching"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <!-- <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
            <!-- <button id="confirmButton">確定</button> -->
            <span class="visually-hidden">Next</span>
        </button>
    </div>




    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">










    <!-- footer -->
    <section class="footer" id="footer">

        <div class="social-container">
            <div class="social">
                <p>關於我們</p>
                <p>人才招募</p>
                <p>加盟專線</p>
            </div>
            <div class="social">
                <p>地址:臺北市大安區復興南路1段390號15樓</p>
                <p>電話:(02)66316588</p>
                <p>追蹤我們</p>
                <a href="#"><i class="fab fa-facebook-square" style="font-size: 2em; color: blue;"></i></a>
                <a href="#"><i class="fab fa-line" style="font-size: 2em; color: rgb(36, 158, 48);"></i></a>
            </div>
            <div class="social">
                <p>使用者條款</p>
                <p>隱私權政策</p>
            </div>
        </div>
        <hr>
        <h5 class="credit">由<span>TLCC設計</span>版權所有&copy;</h5>
    </section>
</template>





<script setup>
import { ref, reactive, onMounted } from 'vue';
import 'animate.css';
import axios from 'axios';

import NewCarousel from '../components/NewCarousel.vue';
import basemap2 from '../components/basemap2.vue';
import Rice1 from '../components/rice1.vue';
import Rice2 from '../components/rice2.vue';
import Main1 from '../components/Main1.vue';
import Main2 from '../components/Main2.vue';
import Main3 from '../components/main3.vue';
import Main4 from '../components/main4.vue';
import Main5 from '../components/main5.vue';
import Side1 from '../components/dish1.vue';
import Side2 from '../components/dish2.vue';
import Side3 from '../components/dish3.vue';
import Side4 from '../components/dish4.vue';
import Side5 from '../components/dish5.vue';
import Side6 from '../components/dish6.vue';
import Side7 from '../components/dish7.vue';
import Side8 from '../components/dish8.vue';
import Side9 from '../components/dish9.vue';
import Side10 from '../components/dish10.vue';
import SmallShoppingCart from '../components/SmallShoppingCart.vue';


/* 回上層按鈕 */


// const detaProperty = ref ();


const scrollToTop = () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth",
    });
};


const qMark = ref("bi bi-question-square-fill");  // 新增
function changeQMark(){
    qMark.value = "bi bi-question-square"
}

function changeQMark2(){
    qMark.value = "bi bi-question-square-fill"
}


const chopstickPillow = ref("黑色筷枕+筷子.png");

function changeChopstickPillow(){
    chopstickPillow.value = "40.png";
}

function changeChopstickPillow2(){
    chopstickPillow.value = "黑色筷枕+筷子.png"
}






const ricePic = ref("rice1.jpg");
const rice = ref();
const mainDish = ref();
const sideDish1 = ref();
const sideDish2 = ref();
const sideDish3 = ref();
const sideDish4 = ref();
let countChosenSideDish = 0;

const tempChickenOrders = ref(0);
const tempPorkOrders = ref(0);
const tempFishOrders = ref(0);
const tempBeefOrders = ref(0);
const tempLambOrders = ref(0);




let showOrNot = ref(false);
let showOrNotForMainDish = ref(false);
let showOrNotForSideDish1 = ref(false);
let showOrNotForSideDish2 = ref(false);
let showOrNotForSideDish3 = ref(false);
let showOrNotForSideDish4 = ref(false);
let lunchboxString = null;
const lunchboxPictures = ref(1);
const lunchboxClassContent = ref("originalLunchboxClass");

const variableForDate = ref();
console.log(variableForDate.value);
function printVariableForDate(){
    console.log(body.inventoryDate);
}

const randomRice = ref();
const randomMain = ref();
const randomSide1 = ref();
const randomSide2 = ref();
const randomSide3 = ref();
const randomSide4 = ref();

const lunchboxCount = ref(0);
let newLunchboxOrdersString = localStorage.getItem("putInShoppingCart");

let inputFromShoppingCart = JSON.parse(newLunchboxOrdersString);
console.log(JSON.parse(newLunchboxOrdersString));

if(inputFromShoppingCart === null){
    inputFromShoppingCart = [];
}else{
    inputFromShoppingCart = inputFromShoppingCart;
}

const selectedLunchBoxes = reactive([]);
for (let i = 0; i <= inputFromShoppingCart.length - 1; i++) {                         //把localStorage目前的值加進小購物車。
    selectedLunchBoxes.push(inputFromShoppingCart[i]);
}
const selectedLunchBox = reactive([
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,

    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },
    {
        riceChoice: null,
        main: null,
        side1: null,
        side2: null,
        side3: null,
        side4: null,
        price: null,
        pickupDate: null,
        id:1000,
        orderNumber:null,
    },

])

const body = reactive({

    inventoryDate:null

});

function findMainDishStockByDate(){
    callFindBy();
    selectedLunchBox[lunchboxCount.value].pickupDate = body.inventoryDate;
    console.log('取貨日: ' + selectedLunchBox[lunchboxCount.value].pickupDate);
}


const inventoryInfo = ref([]);



function changeWording() {
    if (selectedLunchBox[lunchboxCount.value].riceChoice === "rice1") {
        selectedLunchBox[lunchboxCount.value].riceChoice = "白飯"
    };
    if (selectedLunchBox[lunchboxCount.value].riceChoice === "rice2") {
        selectedLunchBox[lunchboxCount.value].riceChoice = "紫米飯"
    };
    if (selectedLunchBox[lunchboxCount.value].main === "main1") {
        selectedLunchBox[lunchboxCount.value].main = "雞"
        selectedLunchBox[lunchboxCount.value].price = 120
        tempChickenOrders.value = tempChickenOrders.value + 1;
    };
    if (selectedLunchBox[lunchboxCount.value].main === "main2") {
        selectedLunchBox[lunchboxCount.value].main = "豬"
        selectedLunchBox[lunchboxCount.value].price = 120
        tempPorkOrders.value = tempPorkOrders.value + 1;
    };
    if (selectedLunchBox[lunchboxCount.value].main === "main3") {
        selectedLunchBox[lunchboxCount.value].main = "魚"
        selectedLunchBox[lunchboxCount.value].price = 150
        tempFishOrders.value = tempFishOrders.value + 1;
    };
    if (selectedLunchBox[lunchboxCount.value].main === "main4") {
        selectedLunchBox[lunchboxCount.value].main = "牛"
        selectedLunchBox[lunchboxCount.value].price = 140
        tempBeefOrders.value = tempBeefOrders.value + 1;
    };
    if (selectedLunchBox[lunchboxCount.value].main === "main5") {
        selectedLunchBox[lunchboxCount.value].main = "羊"
        selectedLunchBox[lunchboxCount.value].price = 130
        tempLambOrders.value = tempLambOrders.value + 1;
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s1") {
        selectedLunchBox[lunchboxCount.value].side1 = "花椰菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s2") {
        selectedLunchBox[lunchboxCount.value].side1 = "胡蘿蔔炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s3") {
        selectedLunchBox[lunchboxCount.value].side1 = "青江菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s4") {
        selectedLunchBox[lunchboxCount.value].side1 = "炒竹筍"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s5") {
        selectedLunchBox[lunchboxCount.value].side1 = "荷包蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s6") {
        selectedLunchBox[lunchboxCount.value].side1 = "高麗菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s7") {
        selectedLunchBox[lunchboxCount.value].side1 = "豆乾"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s8") {
        selectedLunchBox[lunchboxCount.value].side1 = "海帶"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s9") {
        selectedLunchBox[lunchboxCount.value].side1 = "番茄炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side1 === "s10") {
        selectedLunchBox[lunchboxCount.value].side1 = "黃瓜佐甜不辣"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s1") {
        selectedLunchBox[lunchboxCount.value].side2 = "花椰菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s2") {
        selectedLunchBox[lunchboxCount.value].side2 = "胡蘿蔔炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s3") {
        selectedLunchBox[lunchboxCount.value].side2 = "青江菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s4") {
        selectedLunchBox[lunchboxCount.value].side2 = "炒竹筍"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s5") {
        selectedLunchBox[lunchboxCount.value].side2 = "荷包蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s6") {
        selectedLunchBox[lunchboxCount.value].side2 = "高麗菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s7") {
        selectedLunchBox[lunchboxCount.value].side2 = "豆乾"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s8") {
        selectedLunchBox[lunchboxCount.value].side2 = "海帶"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s9") {
        selectedLunchBox[lunchboxCount.value].side2 = "番茄炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side2 === "s10") {
        selectedLunchBox[lunchboxCount.value].side2 = "黃瓜佐甜不辣"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s1") {
        selectedLunchBox[lunchboxCount.value].side3 = "花椰菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s2") {
        selectedLunchBox[lunchboxCount.value].side3 = "胡蘿蔔炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s3") {
        selectedLunchBox[lunchboxCount.value].side3 = "青江菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s4") {
        selectedLunchBox[lunchboxCount.value].side3 = "炒竹筍"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s5") {
        selectedLunchBox[lunchboxCount.value].side3 = "荷包蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s6") {
        selectedLunchBox[lunchboxCount.value].side3 = "高麗菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s7") {
        selectedLunchBox[lunchboxCount.value].side3 = "豆乾"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s8") {
        selectedLunchBox[lunchboxCount.value].side3 = "海帶"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s9") {
        selectedLunchBox[lunchboxCount.value].side3 = "番茄炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side3 === "s10") {
        selectedLunchBox[lunchboxCount.value].side3 = "黃瓜佐甜不辣"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s1") {
        selectedLunchBox[lunchboxCount.value].side4 = "花椰菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s2") {
        selectedLunchBox[lunchboxCount.value].side4 = "胡蘿蔔炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s3") {
        selectedLunchBox[lunchboxCount.value].side4 = "青江菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s4") {
        selectedLunchBox[lunchboxCount.value].side4 = "炒竹筍"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s5") {
        selectedLunchBox[lunchboxCount.value].side4 = "荷包蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s6") {
        selectedLunchBox[lunchboxCount.value].side4 = "高麗菜"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s7") {
        selectedLunchBox[lunchboxCount.value].side4 = "豆乾"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s8") {
        selectedLunchBox[lunchboxCount.value].side4 = "海帶"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s9") {
        selectedLunchBox[lunchboxCount.value].side4 = "番茄炒蛋"
    };
    if (selectedLunchBox[lunchboxCount.value].side4 === "s10") {
        selectedLunchBox[lunchboxCount.value].side4 = "黃瓜佐甜不辣"
    };
}

function sendOrderedLunchbox() {
    lunchboxPictures.value = 2;
}

function placeNewLunchbox() {
    lunchboxPictures.value = 1;
    lunchboxClassContent.value = "animate__animated animate__bounceInLeft";
}

function changeLunchboxClass() {
    lunchboxClassContent.value = "animate__animated animate__bounceOutRight";
}

function clearCurrentLunchbox(){
    showOrNot.value = false;
    showOrNotForMainDish.value = false;
    showOrNotForSideDish1.value = false;
    showOrNotForSideDish2.value = false;
    showOrNotForSideDish3.value = false;
    showOrNotForSideDish4.value = false;
    countChosenSideDish = 0;
}



function addToShopCart() {
    if (
        selectedLunchBox[lunchboxCount.value].riceChoice === null ||
        selectedLunchBox[lunchboxCount.value].main === null ||
        selectedLunchBox[lunchboxCount.value].side1 === null ||
        selectedLunchBox[lunchboxCount.value].side2 === null ||
        selectedLunchBox[lunchboxCount.value].side3 === null ||
        selectedLunchBox[lunchboxCount.value].side4 === null
    ) {
        alert("請完成選菜");
    } else {

        changeWording();
        console.log(selectedLunchBox[lunchboxCount.value]);
        selectedLunchBoxes.push(selectedLunchBox[lunchboxCount.value]);
        console.log(selectedLunchBoxes);
        lunchboxCount.value = lunchboxCount.value + 1;

        console.log(remainingStock);
        console.log(selectedLunchBox);

        lunchboxString = JSON.stringify(selectedLunchBoxes);                    //轉成字串是為了上傳到localStorage，若用父元件傳子元件可直接傳JSON物件，不用先轉成字串。
        console.log(lunchboxString);
        localStorage.setItem("putInShoppingCart", lunchboxString);
        let lunchboxObject = JSON.parse(lunchboxString);
        console.log(lunchboxObject);

        showOrNot.value = false;
        showOrNotForMainDish.value = false;
        showOrNotForSideDish1.value = false;
        showOrNotForSideDish2.value = false;
        showOrNotForSideDish3.value = false;
        showOrNotForSideDish4.value = false;

        sendOrderedLunchbox();
        setTimeout(changeLunchboxClass, 900);
        setTimeout(placeNewLunchbox, 1500);

    }
}



function returnValueRice1() {
    showOrNot.value = true;
    rice.value = '1.jpg';
    selectedLunchBox[lunchboxCount.value].riceChoice = "rice1";
    console.log('hahaha: ' + selectedLunchBox[lunchboxCount.value].riceChoice);
}

function returnValueRice2() {
    showOrNot.value = true;
    rice.value = '2.jpg';
    console.log('hahaha');
    selectedLunchBox[lunchboxCount.value].riceChoice = "rice2"
    console.log('hahaha: ' + selectedLunchBox[lunchboxCount.value].riceChoice);
}

function returnValueMain1() {
    remainingStock.rChicken = chickenStock.value - tempChickenOrders.value;
    console.log(remainingStock.rChicken);
    if(remainingStock.rChicken === 0){
        alert('商品數量有限，下次請早');
    }else{
    showOrNotForMainDish.value = true;
    mainDish.value = 'm1.png';
    console.log('hehehe');
    selectedLunchBox[lunchboxCount.value].main = "main1"
    console.log('hahaha: ' + selectedLunchBox[lunchboxCount.value].main);
    }
}

function returnValueMain2() {
    remainingStock.rPork = porkStock.value - tempPorkOrders.value;
    console.log(remainingStock.rPork);
    if(remainingStock.rPork === 0){
        alert('商品數量有限，下次請早');
    }else{
    showOrNotForMainDish.value = true;
    mainDish.value = 'm2.png';
    console.log('hehehe');
    selectedLunchBox[lunchboxCount.value].main = "main2";
    }
}

function returnValueMain3() {
    remainingStock.rFish = fishStock.value - tempFishOrders.value;
    console.log(remainingStock.rFish);
    if(remainingStock.rFish === 0){
        alert('商品數量有限，下次請早');
    }else{
    showOrNotForMainDish.value = true;
    mainDish.value = 'm3.png';
    console.log('hehehe');
    selectedLunchBox[lunchboxCount.value].main = "main3";
    }
}

function returnValueMain4() {
    remainingStock.rBeef = beefStock.value - tempBeefOrders.value;
    console.log(remainingStock.rBeef);
    if(remainingStock.rBeef === 0){
        alert('商品數量有限，下次請早');
    }else{
    showOrNotForMainDish.value = true;
    mainDish.value = 'm4.png';
    console.log('hehehe');
    selectedLunchBox[lunchboxCount.value].main = "main4";
    }
}

function returnValueMain5() {
    remainingStock.rLamb = lambStock.value - tempLambOrders.value;
    console.log(remainingStock.rLamb);
    if(remainingStock.rLamb === 0){
        alert('商品數量有限，下次請早');
    }else{
    showOrNotForMainDish.value = true;
    mainDish.value = 'm5.png';
    console.log('hehehe');
    selectedLunchBox[lunchboxCount.value].main = "main5";
    }
}


function returnValueSide1() {                                            //秀配菜1在4個配菜格子之一。

    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's1.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s1";

    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's1.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s1";

    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's1.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s1";

    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's1.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s1";

    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide1();
    }

}

function returnValueSide2() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's2.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s2";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's2.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s2";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's2.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s2";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's2.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s2";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide2();
    }
}

function returnValueSide3() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's3.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s3";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's3.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s3";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's3.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s3";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's3.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s4";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide3();
    }
}

function returnValueSide4() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's4.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s4";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's4.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s4";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's4.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s4";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's4.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s4";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide4();
    }
}

function returnValueSide5() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's5.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s5";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's5.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s5";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's5.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s5";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's5.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s5";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide5();
    }
}

function returnValueSide6() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's6.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s6";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's6.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s6";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's6.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s6";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's6.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s6";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide6();
    }
}

function returnValueSide7() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's7.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s7";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's7.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s7";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's7.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s7";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's7.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s7";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide7();
    }
}

function returnValueSide8() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's8.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s8";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's8.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s8";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's8.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s8";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's8.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s8";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide8();
    }
}

function returnValueSide9() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's9.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s9";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's9.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s9";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's9.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s9";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's9.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s9";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide9();
    }
}

function returnValueSide10() {
    if (countChosenSideDish === 0) {                                      //注意等號不要只用一個。
        showOrNotForSideDish1.value = true;
        sideDish1.value = 's10.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('first' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side1 = "s10";
    } else if (countChosenSideDish === 1) {
        showOrNotForSideDish2.value = true;
        sideDish2.value = 's10.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('second' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side2 = "s10";
    } else if (countChosenSideDish === 2) {
        showOrNotForSideDish3.value = true;
        sideDish3.value = 's10.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('third' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side3 = "s10";
    } else if (countChosenSideDish === 3) {
        showOrNotForSideDish4.value = true;
        sideDish4.value = 's10.jpg';
        countChosenSideDish = countChosenSideDish + 1;
        console.log('fourth' + countChosenSideDish);
        selectedLunchBox[lunchboxCount.value].side4 = "s10";
    } else {
        countChosenSideDish = 0;                                        //注意等號只能用一個(這裡是在塞值)，不然會無限迴圈。
        console.log('fifth' + countChosenSideDish);
        returnValueSide10();
    }
}

function randomRicePic() {                                               //隨機選米飯
    randomRice.value = Math.floor(Math.random() * 2) + 1;
    rice.value = `${randomRice.value}.jpg`;
    selectedLunchBox[lunchboxCount.value].riceChoice = `rice${randomRice.value}`;
    console.log(randomRice.value);
}

function repeatingRiceChange() {
    for (let i = 1; i < 10; i++) {
        setTimeout(randomRicePic, i * 150);
    }
}

function randomChooseRice() {
    randomRicePic();
    repeatingRiceChange();
    showOrNot.value = true;
    console.log(rice.value);
}

function randomMainPic() {                                               //隨機選主菜
    randomMain.value = Math.floor(Math.random() * 5) + 1;
    mainDish.value = `m${randomMain.value}.png`;
    selectedLunchBox[lunchboxCount.value].main = `main${randomMain.value}`;
    console.log(randomMain.value);
}

function repeatingMainChange() {
    for (let i = 1; i < 15; i++) {
        setTimeout(randomMainPic, i * 150);
    }

}

function avoidInsufficientInventory(){
    while (remainingStock.rChicken === 0 && mainDish.value === 'm1.png' 
    || remainingStock.rPork === 0 && mainDish.value === 'm2.png' 
    || remainingStock.rFish === 0 && mainDish.value === 'm3.png'
    || remainingStock.rBeef === 0 && mainDish.value === 'm4.png' 
    || remainingStock.rLamb === 0 && mainDish.value === 'm5.png') {
        randomMainPic();
    }
}

function randomChooseMain() {
    randomMainPic();
    repeatingMainChange();
    showOrNotForMainDish.value = true;
    setTimeout(avoidInsufficientInventory, 16 * 150);
    console.log(mainDish.value);
}

function randomSide1Pic() {                                               //隨機選配菜1
    randomSide1.value = Math.floor(Math.random() * 10) + 1;
    sideDish1.value = `s${randomSide1.value}.jpg`;
    selectedLunchBox[lunchboxCount.value].side1 = `s${randomSide1.value}`;
    console.log(randomSide1.value);
}

function repeatingSide1Change() {
    for (let i = 1; i < 20; i++) {
        setTimeout(randomSide1Pic, i * 150);
    }
}

function randomChooseSide1() {
    randomSide1Pic();                                                   //先執行一次
    repeatingSide1Change();                                              //再重複執行
    showOrNotForSideDish1.value = true;
    console.log(sideDish1.value);
}

function randomSide2Pic() {                                               //隨機選配菜2
    randomSide2.value = Math.floor(Math.random() * 10) + 1;
    sideDish2.value = `s${randomSide2.value}.jpg`;
    selectedLunchBox[lunchboxCount.value].side2 = `s${randomSide2.value}`;
    console.log(randomSide2.value);
}

function avoidRepeatedDish2() {
    while (randomSide2.value === randomSide1.value) {
        randomSide2Pic();
    }
}

function repeatingSide2Change() {
    for (let i = 1; i < 25; i++) {
        setTimeout(randomSide2Pic, i * 150);
    }
    setTimeout(avoidRepeatedDish2, 26 * 150);
}

function randomChooseSide2() {
    randomSide2Pic();
    repeatingSide2Change();
    showOrNotForSideDish2.value = true;
    console.log(sideDish2.value);

}

function randomSide3Pic() {                                               //隨機選配菜3
    randomSide3.value = Math.floor(Math.random() * 10) + 1;
    sideDish3.value = `s${randomSide3.value}.jpg`;
    selectedLunchBox[lunchboxCount.value].side3 = `s${randomSide3.value}`;
    console.log(randomSide3.value);
}

function avoidRepeatedDish3() {
    while ((randomSide3.value === randomSide1.value) || (randomSide3.value === randomSide2.value)) {
        randomSide3Pic();
    }
}

function repeatingSide3Change() {
    for (let i = 1; i < 30; i++) {
        setTimeout(randomSide3Pic, i * 150);
    }
    setTimeout(avoidRepeatedDish3, 31 * 150);
}

function randomChooseSide3() {
    randomSide3Pic();
    repeatingSide3Change();
    showOrNotForSideDish3.value = true;
    console.log(sideDish3.value);
}

function randomSide4Pic() {                                               //隨機選配菜4
    randomSide4.value = Math.floor(Math.random() * 10) + 1;
    sideDish4.value = `s${randomSide4.value}.jpg`;
    selectedLunchBox[lunchboxCount.value].side4 = `s${randomSide4.value}`;
    console.log(randomSide4.value);
}

function avoidRepeatedDish4() {
    while ((randomSide4.value === randomSide1.value) || (randomSide4.value === randomSide2.value) || (randomSide4.value === randomSide3.value)) {
        randomSide4Pic();
    }
}

function repeatingSide4Change() {
    for (let i = 1; i < 35; i++) {
        setTimeout(randomSide4Pic, i * 150);
    }
    setTimeout(randomSide4Pic, (35 * 150) + 250);
    setTimeout(randomSide4Pic, (35 * 150) + 550);
    setTimeout(randomSide4Pic, (35 * 150) + 1000);
    setTimeout(avoidRepeatedDish4, (35 * 150) + 1450);
}

function randomChooseSide4() {
    randomSide4Pic();
    repeatingSide4Change();
    showOrNotForSideDish4.value = true;
    console.log(sideDish4.value);

}


function randomChooseAll() {
    randomChooseRice();
    randomChooseMain();
    randomChooseSide1();
    randomChooseSide2();
    randomChooseSide3();
    randomChooseSide4();
}


function saveInventoryNumber(){

    porkStock.value = 0;
    beefStock.value = 0;
    chickenStock.value = 0;
    fishStock.value = 0;
    lambStock.value = 0;


    for(let i = 0; i <= inventoryInfo.value.length - 1; i++){
    porkStock.value = porkStock.value + inventoryInfo.value[i].pork;
    beefStock.value = porkStock.value + inventoryInfo.value[i].beef;
    chickenStock.value = chickenStock.value + inventoryInfo.value[i].chicken;
    fishStock.value = fishStock.value + inventoryInfo.value[i].fish;
    lambStock.value = lambStock.value + inventoryInfo.value[i].lamb;

    }
    console.log('porkStock:' + porkStock.value
    +  ' beefStock:' + beefStock.value
    +  ' chickenStock:' + chickenStock.value
    +  ' fishStock:' + fishStock.value
    +  ' lambStock:' + lambStock.value
    );
}

const porkStock = ref(0);
const beefStock = ref(0);
const chickenStock = ref(0);
const fishStock = ref(0);
const lambStock = ref(0);

const remainingStock = reactive({
    rChicken: 1000,
    rPork: 1000,
    rFish: 1000,
    rBeef: 1000,
    rLamb: 1000,
})






console.log(chickenStock.value)
console.log(remainingStock.rChicken)


const URL = import.meta.env.VITE_API_whateverURL

async function callFindBy(){                                                            //查主菜庫存。
    const response = await axios.post(`${URL}inventory/find`, body)
    // const response = await axios.get(`${URL}member/` + id.value)
    // const response = await axios.get("http://localhost:8080/pages/member/1")
    console.log(response);
    // console.log(response.data.list[0].orderNumber);
    console.log(body.inventoryDate);
    inventoryInfo.value = response.data.list
    saveInventoryNumber();
 
    // totalPages.value = body.rows === 0 ? 1 : Math.ceil(response.data.count / body.rows) //分頁功能
};

callFindBy();


</script>





<style scoped>
.col {
    margin: 0;
    padding: 0;
}

.footer {
    /* background: rgb(203, 186, 150); */
    /* background: rgb(122, 122, 122); */
    background: rgb(225, 203, 186);
    text-align: center;
    padding: 0.5em;
    /* 添加 1 像素的内边距 */
}

.footer .credit span {
    color: rgb(60, 85, 184);
}


.social-container {
    display: flex;
    justify-content: space-around;
    /* 使用 space-between 来分散对齐 */
}


/* 回上層按鈕 */
.back-to-top {
    position: fixed;
    right: 20px;
    bottom: 20px;
    background-color: aquamarine;
    opacity: 0.3;
    color: #fff;
    border: none;
    border-radius: 50%;
    width: 60px;
    height: 60px;
    text-align: center;
    cursor: pointer;
    transition: background-color 0.8s;
}


.back-to-top i {
    font-size: 60px;
    line-height: 40px;
}

.back-to-top:hover {
    background-color: antiquewhite;
}


.rice1-image {
    width: 330px;
    /* 设置宽度 */
    height: 220px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.rice2-image {
    width: 330px;
    /* 设置宽度 */
    height: 220px;
    /* 设置高度 */
    /* border:6px rgb(230, 187, 152) solid; */
    border: 6px rgb(230, 187, 152) solid;
}

.main1-image {
    width: 200px;
    /* 设置宽度 */
    height: 200px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.basemap2-image {
    width: 200px;
    /* 设置宽度 */
    height: 200px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.main2-image {
    width: 250px;
    /* 设置宽度 */
    height: 250px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
}

.main3-image {
    width: 200px;
    /* 设置宽度 */
    height: 200px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.main4-image {
    width: 200px;
    /* 设置宽度 */
    height: 200px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.main5-image {
    width: 255px;
    /* 设置宽度 */
    height: 255px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
    left: 918px;
    z-index: 5;
    margin-top: -28px
}

.basemap3-image {
    width: 150px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;

}

/* dish圖片 */
.dish1-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.dish2-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.dish3-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.dish4-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 6px rgb(230, 187, 152) solid;
}

.dish5-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
}

.dish6-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
}

.dish7-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
}

.dish8-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
}

.dish9-image {
    width: 170px;
    /* 设置宽度 */
    height: 155px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
}

.dish10-image {
    width: 170px;
    /* 设置宽度 */
    height: 150px;
    /* 设置高度 */
    border: 0px rgb(230, 187, 152) solid;
}

.ricebox {
    position: relative;
}


.r {
    width: 265px;
    /* 设置宽度 */
    height: 320px;
    /* 设置高度 */
    left: 330px;
    top: 760px;
}


.m {
    width: 350px;
    /* 设置宽度 */
    height: 450px;
    /* 设置高度 */
    left: 290px;
    top: 675px;
}



.s {
    width: 140px;
    /* 设置宽度 */
    height: 160px;
    /* 设置高度 */
    left: 618px;
    top: 745px;
}

.d {
    width: 125px;
    /* 设置宽度 */
    height: 160px;
    /* 设置高度 */
    left: 765px;
    top: 745px;
}

.e {
    width: 125px;
    /* 设置宽度 */
    height: 160px;
    /* 设置高度 */
    left: 625px;
    top: 920px;
}

.f {
    width: 125px;
    /* 设置宽度 */
    height: 160px;
    /* 设置高度 */
    left: 765px;
    top: 920px;
}

/* .originalLunchboxClass {                    
    
} */

.datePicker{

font-size: 20px;
margin-left: 30px;

}

.buttonForRandom{
    font-size: 80px;
    padding: 20px;
    background-color: rgb(238, 212, 63);
    /* color: aqua; */
    border-radius: 20px; /* 外框的圆角 */
    border: 2px solid #000000; /* 外框的边框样式 */
    margin-left: 50px;
}

.buttonForRandom:hover {
  background-color: #fafd95; /* 按钮悬停时的背景颜色 */
}

.buttonForClearLunchBox{
    font-size: 30px;
    font-weight: bold;
  border: 2px solid #000; /* 外框的边框样式 */
  padding: 20px; /* 外框内边距 */
  background-color: rgb(238, 212, 63); /* 外框的背景颜色 */
  border-radius: 10px; /* 外框的圆角 */
  /* margin: 20px; 外框与其他内容的间距 */
  width: 300px;
  right: 350px;
margin-left: 40px;

}

.buttonForClearLunchBox:hover{

    background-color: #fafd95; /* 按钮悬停时的背景颜色 */ 
}

.buttonForAddToShoppingCart{
    font-size: 30px;
    font-weight: bold;
    border: 2px solid #000; /* 外框的边框样式 */
  padding: 20px; /* 外框内边距 */
  background-color: rgb(238, 212, 63); /* 外框的背景颜色 */
  border-radius: 10px; /* 外框的圆角 */
  /* margin: 20px; 外框与其他内容的间距 */
  width: 300px;
  right: 350px;
  margin-left: 40px;
}
.buttonForAddToShoppingCart:hover{
    background-color: #fafd95; /* 按钮悬停时的背景颜色 */ 
}

.frame001{
  
  border: 2px solid #000; /* 外框的边框样式 */
  padding: 20px; /* 外框内边距 */
  background-color: #ffffff; /* 外框的背景颜色 */
  border-radius: 10px; /* 外框的圆角 */
  /* margin: 20px; 外框与其他内容的间距 */
  width: 400px;
  right: 350px;
  position: absolute;
  z-index:20;
  top:710px;
 
}


.black{
    position: absolute;
    /* z-index:21; */
    right: 850px;
    top: 700px;
    
}



</style>