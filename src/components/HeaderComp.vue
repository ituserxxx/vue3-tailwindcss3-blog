<template>
  <div class="HeaderComp fixed top-0 inset-x-0 shadow z-10 dark:bg-gray-800 dark:text-gray-100">

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

      <div class="lg:flex lg:gap-x-12">
        <a v-for="item in navigation" :key="item.name" :href="item.href"
          class="font-semibold leading-6 text-gray-900 hover:text-gray-600 dark:text-gray-100 dark:hover:text-gray-300">
          {{ item.name }}
        </a>
      </div>

      <div class="relative">
        <input type="search" name="Search" placeholder="Search..."
          class="w-32 py-2 pl-10 pr-4 text-sm rounded-md sm:w-auto border border-gray-300 dark:border-gray-600 dark:bg-gray-700 dark:text-gray-100 focus:outline-none focus:ring-2 focus:ring-blue-500 dark:focus:ring-blue-300">
        <svg class="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400 dark:text-gray-500"
          fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
            d="M21 21l-4.35-4.35M10 16a6 6 0 1 0 0-12 6 6 0 0 0 0 12z"></path>
        </svg>
      </div>

    </div>
  </div>
</template>

<script setup>
const navigation = [
  { name: 'Home', href: '/' },
  { name: 'LeaveMessage', href: '/leaveMessage' },
  { name: 'Tag', href: '/tag' },
  { name: 'About', href: '/about' },
];
</script>

<style scoped>
/* Add any additional styles here if needed */
.HeaderComp {
  height: 60px;
  /* Adjust height if needed */
}
</style>