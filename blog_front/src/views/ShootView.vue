<template>
  <div class="shoot">
    <div class="grid grid-cols-2 gap-4 md:grid-cols-4">
      <div v-for="(item, i) in ViewData.imgList" :key="i" class="relative h-full">
        <img class="h-auto w-auto object-cover rounded-lg object-center" :src="item.imgUrl" :alt="item.alt" />
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';

import { shootListApi } from '../api/blog';

const ViewData = ref({
  imgList: [
    // {
    //   "alt": "人物",
    //   "img": "https://images.unsplash.com/photo-1432462770865-65b70566d673?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&amp;ixlib=rb-1.2.1&amp;auto=format&amp;fit=crop&amp;w=1950&amp;q=80"
    // },
  ]
});
onMounted(() => {
  renderShootList()
});

const renderShootList = async () => {
  try {
    let data = await shootListApi({
      page: Number(1),
    });
    if (data.code > 0) {
      console.error('Error fetching the list:');
      return
    }
    data = data.data;
    ViewData.value.imgList = data
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
};
</script>
