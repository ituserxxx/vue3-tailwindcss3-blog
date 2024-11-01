<template>
  <div class="flex flex-col min-h-screen">

    <header class="bg-gray-200 py-4">
      <HeaderComp />
    </header>
    <main class="flex-grow">
      <RouterView />
    </main>
    <FooterComp />
  </div>

</template>
<script>
import { RouterView } from 'vue-router'
import HeaderComp from './components/HeaderComp.vue'
import FooterComp from './components/FooterComp.vue'
export default {
  components: {
    HeaderComp,
    FooterComp
  }
}
</script>

<style scoped></style>
