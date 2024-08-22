<template>
  <main>
    <HeaderComp />
    <div class="content fixed top-10 inset-x-0 bg-white shadow z-10">
      <h1 class="text-3xl font-bold underline">
        Hello world!
      </h1>
    </div>
    <FooterComp />

  </main>
</template>
<script>
import HeaderComp from '../components/HeaderComp.vue'
import FooterComp from '../components/FooterComp.vue'
export default {
  components: {
    HeaderComp,
    FooterComp
  }
}
</script>