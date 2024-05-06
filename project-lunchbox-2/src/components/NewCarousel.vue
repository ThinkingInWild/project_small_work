<template>
    <div class="sticky">
        <el-carousel :interval="4000" type="card" height="420px">
            <el-carousel-item v-for="item in 3" :key="item">
                <img :src="'src/pictures/' + item + '.jpg'" style="width: 100%; height: 100%;">
            </el-carousel-item>
        </el-carousel>
        <!-- 回到最上層 -->
        <!-- <div class="back-to-top" @click="scrollToTop">
      <i class="bi bi-arrow-up-circle"></i>
    </div> -->
    </div>
    
    <!-- 这是你的链接 -->
    <div class="centered-button">
        <a href="#bottom-section" class="custom-button">&#8595; &#8595; 歡迎大家一起訂購便當喔&#8595; &#8595; </a>
    </div>
    
</template>
    
<script setup>

</script>
    
<style scoped>
.el-carousel__item h3.image-container {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}

/* 
.back-to-top {
  position: fixed;
  right: 20px;
  bottom: 20px;
  background-color:#000000 ;
  opacity: 0.3;
  color: #fff;
  border: none;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  text-align: center;
  cursor: pointer;
  transition: background-color 0.8s;
} */

/* .back-to-top i {
  font-size: 60px;
  line-height: 40px;
} */

/* .back-to-top:hover {
  background-color: #9fa0a1;
} */

.centered-button {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 40px;
    /* 调整按钮的垂直居中位置，根据需要更改 */
    height: 100px;
    margin: 50px;
}

.outer-frame {
    border: 2px solid #000;
    padding: 20px;
    background-color: #f0f0f0;
    border-radius: 10px;
    margin: 20px;
}

.custom-button {
    border: none;
    /* 外框的边框样式 */
    padding: 10px;
    /* 外框内边距 */
    background-color: #fff;
    /* 外框的背景颜色 */
    border-radius: 10px;
    /* 外框的圆角 */
    margin: 0;
    /* 外框与其他内容的间距 */
    color: #ac0707
}

.time-picker-wrapper {
    position: relative;
    width: 500px;
    float: right;
    font-size: larger;
    border: none;
}

.sticky {
    position: -webkit-sticky;
    position: sticky;
}</style>