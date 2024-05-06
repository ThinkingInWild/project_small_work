<template>
    <div>
        <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>庫存日期</th>
                <th>雞肉庫存</th>
                <th>豬肉庫存</th>
                <th>魚肉庫存</th>
                <th>牛肉庫存</th>
                <th>羊駝庫存</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="inventory in inventoryInfo" :key = id>
                <td>{{inventory.id}}</td>
                <td>{{inventory.inventoryDate}}</td>
                <td>{{ inventory.chicken }}</td>
                <td>{{inventory.pork}}</td>
                <td>{{inventory.fish }}</td>
                <td>{{inventory.beef}}</td>
                <td>{{ inventory.lamb }}</td>
            </tr>
           
        </tbody>
       </table>
    </div>
</template>
<script setup>

import {ref, reactive} from 'vue';
import axios from 'axios'


const body = reactive({

    id:null,
    inventoryDate:null,
    chicken:null,
    pork:null,
    fish:null,
    beef:null,
    lamb:null

})

const inventoryInfo = ref([]); 





const URL = import.meta.env.VITE_API_whateverURL

async function callFindAllInventory(){                                                            //查詢庫存。
    const response = await axios.post(`${URL}inventory/find`, body);

    console.log(response);
    // console.log(response.data.list[0].orderNumber);
    inventoryInfo.value = response.data.list
 
};

callFindAllInventory();




</script>
<style scoped>
    
</style>