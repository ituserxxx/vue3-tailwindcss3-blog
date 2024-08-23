<template>
  
    <HeaderComp />
    <div class="content">
      <RouterView />
    </div>
    <FooterComp />
   
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

<style scoped>
/* Ensure HeaderComp is fixed and not overlapping content */
.HeaderComp {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 10;
}

/* Ensure FooterComp is fixed at the bottom */
.FooterComp {
  position: relative;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 10;
}
.content {
  margin-top: 4rem; /* Adjust top margin based on HeaderComp height */
  margin-bottom: 4rem; /* Adjust bottom margin based on FooterComp height */
}
</style>
