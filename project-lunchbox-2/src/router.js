import { createWebHistory,createRouter } from "vue-router";
import Home from './views/Home.vue'
import ShoppingCart from './components/NewShoppingCart.vue'
import Login from './views/Login.vue'

//明毅
import MemberFind from './views/MemberFind.vue'  //後台-會員查找
import MemberFind2 from './views/memberFind2.vue' //後台-會員查找
import MemberModifyModal from './views/MemberModifyModal.vue' //個人資料修改
import MemberRegister from './views/MemberRegister.vue' //會員註冊
import MemberRegister2 from './views/MemberRegister2.vue' //會員註冊
import MemberRewardWindow from './views/MemberRewardWindow.vue' //會員紅利歷史
import LoginModal from './views/LoginModal.vue'  //登入浮出畫面
import Logout from './views/Logout.vue' //登出畫面
import Billboard from './views/Billboard.vue'   //留言板
import Backend from './views/Backend.vue' //後台框

import Reward from './views/Reward.vue'//改菁純的
import Reward2 from './views/Reward2.vue'//改菁純的 //棄用
import Cart from './views/Cart.vue' //測試用-空檔
import Purchase from './views/Purchase.vue' //測試用-空檔
import OrderContent from './views/OrderContent.vue' //測試用-空檔
import Inventory from './components/inventory.vue' //Jimmy
import OrderQueryAdmin from './components/order-query-admin.vue'
import OrderDetail from './components/order-detail.vue'
import Report from './views/Report.vue' //測試用-空檔

const routers = [
    {path: "/",component:Home},
    {path: "/shoppingcart",component:ShoppingCart},
    {path: "/login",component:Login},
    
    //明毅
    {path:'/memberFind', component:MemberFind},  //http://localhost:5173/memberFind
    {path:'/memberFind2', component:MemberFind2},  //http://localhost:5173/memberFind2
    {path:'/memberModifyModal', component:MemberModifyModal},  //http://localhost:5173/memberModifyModal
    {path:'/memberRegister', component:MemberRegister},  //http://localhost:5173/memberRegister
    {path:'/memberRegister2', component:MemberRegister2},  //http://localhost:5173/memberRegister2
    {path:'/memberRewardWindow', component:MemberRewardWindow, //http://localhost:5173/memberRewardWindow
        children:[
            {path:'', component:Purchase},                  //http://localhost:5173/memberRewardWindow/purchase
            {path:'purchase', component:Purchase},          //http://localhost:5173/memberRewardWindow/purchase
            {path:'orderContent', component:OrderContent},  //http://localhost:5173/memberRewardWindow/orderContent
            
        ]},  //http://localhost:5173/memberWindow
    {path:'/loginModal', component:LoginModal},  //http://localhost:5173/loginModal
    {path:'/logout', component:Logout},  //http://localhost:5173/logout
    {path:'/billboard', component:Billboard},  //http://localhost:5173/billboard
    {path:'/cart', component:Cart},  //http://localhost:5173/cart                       
    {path:'/purchase', component:Purchase},  //http://localhost:5173/purchase 
    {path:'/reward', component:Reward},  //http://localhost:5173/reward
    {path:'/backend', component:Backend,     //http://localhost:5173/backend 
        children:[    
        {path:'orderQueryAdmin', component:OrderQueryAdmin}, 
        {path:'orderDetail', component:OrderDetail}, 
        {path:'memberFind', component:MemberFind}, 
        {path:'memberFind2', component:MemberFind2, 
                children:[
                {path:'', component:Purchase},
                {path:'purchase', component:Purchase},
                {path:'orderContent', component:OrderContent},
                {path:'reward', component:Reward},
        ]}, 
        {path:'inventory', component:Inventory}, 
        {path:'report', component:Report}, 
    ]}, 
]

    const router = createRouter({
    history: createWebHistory(),
    routes:routers  
    })                                           
        
    export default router;