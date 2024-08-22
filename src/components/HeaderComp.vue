<template>
    <div class="HeaderComp fixed top-0 inset-x-0 bg-white shadow z-10">
      <div class="container mx-auto flex items-center justify-center h-full">
        <div class="lg:flex lg:gap-x-12">
          <a
            v-for="item in navigation"
            :key="item.name"
            :href="item.href"
            class="font-semibold leading-6 text-gray-900 hover:text-gray-600"
          >
            {{ item.name }}
          </a>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  const navigation = [
    { name: 'Product', href: '#' },
    { name: 'Features', href: '#' },
    { name: 'Marketplace', href: '#' },
    { name: 'Company', href: '#' },
  ];
  </script>
  
  <style scoped>
  /* Add any additional styles here if needed */
  .HeaderComp {
    height: 60px; /* Adjust height if needed */
  }
  </style>
  