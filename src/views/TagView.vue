<template>
  <div class="tag">
    <ul class="flex mx-auto justify-center flex-wrap max-w-xl gap-6 leading-8 p-5">
      <li><a href="#" class="text-3xl text-cyan-500">Country Names</a></li>
      <li><a href="#" class="text-xl text-teal-500">Chemistry</a></li>
      <li><a href="#" class="text-md text-red-500">File Type</a></li>
      <li><a href="#" class="text-lg text-green-500">Cryptocurrency</a></li>
      <li><a href="#" class="text-sm text-orange-500">Academic</a></li>
      <li><a href="#" class="text-3xl text-cyan-500">Softwares</a></li>
      <li><a href="#" class="text-md text-blue-500">General</a></li>
      <li><a href="#" class="text-2xl text-indigo-500">Web Technology</a></li>
      <li><a href="#" class="text-xl text-indigo-500">Business</a></li>
      <li><a href="#" class="text-md text-blue-500">Technology</a></li>
      <li><a href="#" class="text-xs text-cyan-500">Sports</a></li>
      <li><a href="#" class="text-4xl text-red-500">Law</a></li>
      <li><a href="#" class="text-lg text-gray-500">Internet Slangs</a></li>
      <li><a href="#" class="text-3xl text-cyan-500">Insurance</a></li>
      <li><a href="#" class="text-md text-blue-500">Space Science</a></li>
      <li><a href="#" class="text-4xl text-red-500">Jobs</a></li>
      <li><a href="#" class="text-lg text-gray-500">Certifications</a></li>
      <li><a href="#" class="text-sm text-orange-500">Electronics</a></li>
    </ul>
  </div>
  <div class="content pt-16 pb-16 bg-white shadow">
    <ArticleListComp />
  </div>
</template>
<script>
import ArticleListComp from '../components/ArticleListComp.vue'
export default {
  components: {
    ArticleListComp,
  }
}
</script>