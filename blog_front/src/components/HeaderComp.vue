<template>
  <div class="HeaderComp fixed top-0 inset-x-0 shadow z-10   bg-white dark:bg-gray-800 dark:text-gray-100 h-18 py-2">

    <div class="container mx-auto flex items-center justify-between h-full px-4">

      <div class="flex justify-between items-center">

        <div>
          <a class="text-gray-800 text-xl font-bold md:text-2xl" href="#"><span class="text-blue-500">Vtian</span></a>
        </div>

        <div>
          <button type="button" @click="isOpen = !isOpen"
            class="block text-gray-800 hover:text-gray-600 focus:text-gray-600 focus:outline-none md:hidden">
            <svg class="h-6 w-6 fill-current" viewBox="0 0 24 24">
              <path
                d="M4 5h16a1 1 0 0 1 0 2H4a1 1 0 1 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2z" />
            </svg>
          </button>
        </div>

      </div>

      <div class="lg:flex lg:gap-x-12  ">
        <a v-for="item in navigation" :key="item.name" :href="item.href"
          :class="{ 'bg-blue-500 text-white dark:bg-blue-900 dark:text-gray-200': currentBgColor === item.href, 'font-semibold leading-6 text-gray-900 hover:text-gray-600 dark:text-gray-100 dark:hover:text-gray-300 text-2xl': true }"
          @click="updatePage(item.href)">
          {{ item.name }}
        </a>
      </div>


      <div class="w-full max-w-sm min-w-[200px]">
        <div class="relative">
          <input v-model="searchInput"
            class="w-full pl-3 h-10 pr-28 py-2 bg-transparent placeholder:text-slate-400 text-slate-700 text-sm border border-slate-200 rounded transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-400 shadow-sm focus:shadow-md"
            placeholder="You want to ..." />
          <button @click="handleSearch"
            class="absolute py-1.5 text-white text-sm right-1 top-1 my-auto px-3 flex items-center bg-slate-800 rounded hover:bg-slate-700"
            type="button">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4 mr-2">
              <path fill-rule="evenodd"
                d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z"
                clip-rule="evenodd" />
            </svg>
            Search
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- 弹窗 -->
  <div v-if="showModal" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-6 rounded shadow-lg w-80">
      <h3 class="text-xl font-semibold mb-4">搜索结果</h3>

      <!-- 搜索结果列表 -->
      <ul>
        <li v-for="(item, index) in searchResults" :key="index" class="border-b border-gray-300 py-2">
          {{ item }}
        </li>
      </ul>

      <button @click="closeModal" class="mt-4 py-2 px-4 bg-slate-800 text-white rounded">关闭</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { searchApi } from '../api/blog';
const navigation = [
  { name: '首页', href: '/' },
  { name: '标签', href: '/tag' },
  { name: '留言板', href: '/leaveMessage' },
  { name: '相册', href: '/shoot' },
  { name: '关于', href: '/about' },
  { name: 'demo', href: '/demo' },
];
const currentBgColor = ref(window.location.pathname);
const searchInput = ref("")
const showModal = ref(false)
showModal.value = false

const searchResults = ref(false)
searchResults.value = [
  "首页",
  "首页",
  "首页",
  "首页",
  "首页",
]

function updatePage(href) {
  currentBgColor.value = href;
}
const handleSearch = async () => {
  console.log(searchInput.value)
  let data = await searchApi({
    content: searchInput.value,
  });
  console.log(data);
  if (data.code > 0) {
    console.error('Error le');
    return
  }
  searchResults.value = data.data
  showModal.value = true
}
const closeModal = async () => {
  showModal.value = false
}
</script>

<style scoped>
/* Add any additional styles here if needed */
/* .HeaderComp {
  height: 60px;
} */
a.bg-gray-200 {
  background-color: #e2e8f0;
  /* Light gray background for selected item */
}

a.dark\:bg-gray-700 {
  background-color: #c4cbd9;
  /* Dark gray background for selected item in dark mode */
}
</style>