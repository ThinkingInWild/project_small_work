npm<template>
        <h6>Cart Page</h6>
        <div class="container">
        <div class="item_header">
            <div class="item_detail">商品</div>
            <div class="price">單價</div>
            <div class="count">數量</div> 
            <div class="amount">總計</div>
            <div class="operate">操作</div>
        </div>
        <div class="item_header item_body">
            <div class="item_detail">
                <img src="https://images.unsplash.com/photo-1534961880437-ce5ae2033053?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=668&q=80" alt="">
                <div class="name">優質短袖白T</div>
            </div>

            <div class="price"><span>$</span>300</div>
            <div class="count">
                <button>-</button>
                1
                <button>+</button>
            </div> 
            <div class="amount">300</div>
            <div class="operate">
                <button>刪除</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';

const itemList = reactive([
      {
        id:'1',
        itemName:'優質短袖白T',
        imgUrl:'https://images.unsplash.com/photo-1534961880437-ce5ae2033053?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=668&q=80',
        price:'500',
        count:'1'
      },
      {
        id:'2',
        itemName:'骷髏手短黑T',
        imgUrl:'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1500&q=80',
        price:'790',
        count:'1'
      },
      {
        id:'3',
        itemName:'超時尚牛仔庫',
        imgUrl:'https://images.unsplash.com/photo-1529391409740-59f2cea08bc6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1124&q=80',
        price:'1200',
        count:'1'
      },
      {
        id:'4',
        itemName:'質感褐色系大衣服',
        imgUrl:'https://images.unsplash.com/photo-1491998664548-0063bef7856c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1500&q=80',
        price:'2300',
        count:'1'
      }
])
</script>

<style scoped>
body{
    background-color: rgba(0, 0, 0,.2);
}
.item_header{
    display: flex;
    width: 1000px;
    margin: 0 auto;
    height: 30px;
    background-color: #fff;
    border-radius: 3px;
    padding-left: 10px;
}
.item_header div{
    width: 200px;
    color: #888;
    line-height: 30px;
}
.item_header .item_detail{
    width: 50%;
}
.item_body{
    margin-top: 20px;
    height: 100px;
    align-items: center;    
}
.item_detail img{
    width: 80px;
    height: 80px;
    border-radius: 3px;
    /* margin-top: 10px; */
    float: left;
}
.item_detail .name{
    margin-left: 100px;
    margin-top: 20px;
} 
</style>