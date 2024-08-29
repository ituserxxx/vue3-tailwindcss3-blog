<template>
  <div class="PageComp">
    <!-- 页码 -->
    <ul class="flex">
      <!-- Previous Button -->
      <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-500 rounded-lg" :class="{ 'cursor-not-allowed': currPage === 1 }"
        @click="prevPage">
        <a class="flex items-center font-bold" href="#">Previous</a>
      </li>

      <!-- 页码链接 -->
      <li v-for="page in pageNumbers" :key="page" class="mx-1 px-3 py-2 bg-gray-200 rounded-lg"
        :class="{ 'text-gray-700': currPage !== page, 'bg-gray-700 text-gray-200': currPage === page }"
        @click="setPage(page)">
        <a class="font-bold" href="#">{{ page }}</a>
      </li>

      <!-- Next Button -->
      <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-700 hover:bg-gray-700 hover:text-gray-200 rounded-lg"
        :class="{ 'cursor-not-allowed': currPage === totalPages }" @click="nextPage">
        <a class="flex items-center font-bold" href="#">Next</a>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { computed,defineProps } from 'vue';

const props = defineProps({
  currPage: Number,
  total: Number
});

// 计算总页数
const totalPages = computed(() => Math.ceil(props.total / 5)); // 假设每页显示5个页码

// 计算要显示的页码
const pageNumbers = computed(() => {
  const pages = [];
  const start = Math.max(1, props.currPage - 2);
  const end = Math.min(totalPages.value, props.currPage + 2);

  for (let i = start; i <= end; i++) {
    pages.push(i);
  }

  return pages;
});

// 点击下一页
const nextPage = () => {
  if (props.currPage < totalPages.value) {
    emit('update:currPage', props.currPage + 1);
  }
};

// 点击上一页
const prevPage = () => {
  if (props.currPage > 1) {
    emit('update:currPage', props.currPage - 1);
  }
};

// 点击指定页码
const setPage = (page) => {
  emit('update:currPage', page);
};

// 发射事件到父组件更新当前页码
const emit = defineEmits(['update:currPage']);
</script>

<style scoped>
/* 你可以在这里添加自定义的样式 */
</style>