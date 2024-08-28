<template>

  <div class="tag">
    <ul class="flex mx-auto justify-center flex-wrap max-w-xl gap-6 leading-8 p-5">
      <li v-for="(tag, index) in ViewData.tagList" :key="index">
        <a :href="`/tag/${tag.id}`" :class="tag.className" class="text-gray-800 hover:underline"
          @click.prevent="updateCurrentTagId(tag.id)">
          {{ tag.name }}
        </a>
      </li>
    </ul>
  </div>

  <div class="content pt-16 pb-16 bg-white shadow">
    <ArticleListComp :currentTagId="ViewData.currentTagId" />
  </div>
</template>
<script setup>
import ArticleListComp from '../components/ArticleListComp.vue'
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'; // Import Vue Router hook
import { tagListApi } from '../api/blog';

const route = useRoute();
const router = useRouter();

const ViewData = ref({
  currentTagId: Number(route.params.tagId),
  tagList: [],
  sizes: ['text-xs', 'text-sm', 'text-md', 'text-lg', 'text-xl', 'text-2xl', 'text-3xl', 'text-4xl'],
  colors: ['text-cyan-500', 'text-teal-500', 'text-red-500', 'text-green-500', 'text-orange-500', 'text-blue-500', 'text-indigo-500', 'text-gray-500'],
})

function updateCurrentTagId(tagId) {
  ViewData.value.currentTagId = tagId;
  // 跳转到新的 URL
  router.push(`/tag/${tagId}`);
}

const renderTagList = async () => {
  try {
    let data = await tagListApi();
    if (data.code > 0) {
      console.error('Error fetching the list:');
      return
    }
    data = data.data;
    ViewData.value.tagList = data.list.map(item => {
      return { ...item, className: getRandomClass() }
    })
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
};

onMounted(() => {
  renderTagList();
});
function getRandomClass() {
  const size = ViewData.value.sizes[Math.floor(Math.random() * ViewData.value.sizes.length)];
  const color = ViewData.value.colors[Math.floor(Math.random() * ViewData.value.colors.length)];
  return `${size} ${color}`;
}
</script>