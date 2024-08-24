<template>
  <div class="tag">
    <ul class="flex mx-auto justify-center flex-wrap max-w-xl gap-6 leading-8 p-5">
      <li v-for="(tag, index) in ViewData.tags" :key="index">
        <a :href="`/tag/${tag.id}`" :class="getRandomClass()" class="text-gray-800 hover:underline">
          {{ tag.name }}
        </a>
      </li>
    </ul>
  </div>
  <div class="content pt-16 pb-16 bg-white shadow">
    <ArticleListComp />
  </div>
</template>
<script setup>
import ArticleListComp from '../components/ArticleListComp.vue'
const ViewData = {
  tags: [
    { name: 'Country Names', id: 1 },
    { name: 'Chemistry', id: 1 },
    { name: 'File Type', id: 1 },
    { name: 'Cryptocurrency', id: 1 },
    { name: 'Academic', id: 1},
    { name: 'Softwares', id: 1 },
    { name: 'General', id: 1 },
    { name: 'Web Technology', id: 1 },
    { name: 'Business',id: 1},
    { name: 'Technology', id: 1},
    { name: 'Sports', id: 1 },
    { name: 'Law', id: 1 },
    { name: 'Internet Slangs', id: 1 },
    { name: 'Insurance', id: 1 },
    { name: 'Space Science', id: 1 },
    { name: 'Jobs', id: 1 },
    { name: 'Certifications', id: 1 },
    { name: 'Electronics', id: 1 },
  ],
  sizes: ['text-xs', 'text-sm', 'text-md', 'text-lg', 'text-xl', 'text-2xl', 'text-3xl', 'text-4xl'],
  colors: ['text-cyan-500', 'text-teal-500', 'text-red-500', 'text-green-500', 'text-orange-500', 'text-blue-500', 'text-indigo-500', 'text-gray-500'],
}
function getRandomClass() {
  const size = ViewData.sizes[Math.floor(Math.random() * ViewData.sizes.length)];
  const color = ViewData.colors[Math.floor(Math.random() * ViewData.colors.length)];
  return `${size} ${color}`;
}
</script>