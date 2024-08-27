<template>
    <div class="PageComp">
        <!-- 页码 -->
        <ul class="flex">
            
            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-500 rounded-lg">
                <a class="flex items-center font-bold" href="#">previous</a>
            </li>

            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-700 hover:bg-gray-700 hover:text-gray-200 rounded-lg">
                <a class="font-bold" href="#">1</a>
            </li>
            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-700 hover:bg-gray-700 hover:text-gray-200 rounded-lg">
                <a class="flex items-center font-bold" href="#">Next</a>
            </li>
        </ul>
    </div>
</template>
<script setup>
import { defineProps } from 'vue';
const props = defineProps({
  currPage: Number, // 或 Number, Object, etc. 根据实际需求
  total: Number
});
 
</script>

<style scoped>

</style>