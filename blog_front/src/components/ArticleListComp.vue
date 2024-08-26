<template>
    <div class="ArticleListComp dark:bg-gray-100 dark:text-gray-900">

        <div v-for="article in ViewData.articleList" :key="article.id"
            class="max-w-4xl px-10 py-6 bg-white rounded-lg shadow-md">
            <div class="flex justify-between items-center">
                <span class="font-light text-gray-600">{{ article.date }}</span>
            </div>

            <div class="mt-2">
                <a class="text-2xl text-gray-700 font-bold hover:underline" :href="`/articleDetail/${article.id}`">
                    {{ article.title }}
                </a>
                <p class="mt-2 text-gray-600">{{ article.body }}</p>
            </div>

            <div class="flex justify-between items-center mt-4">
                <a class="text-blue-500 hover:underline" :href="`/articleDetail/${article.id}`">Read more</a>
            </div>
        </div>
        <!-- 页码 -->
        <PageComp :currPage="ViewData.currPage" :total="ViewData.total"  @pageChanged="handlePageChanged" />
    </div>

</template>
<script setup>
import { ref, onMounted } from 'vue';
import PageComp from './PageComp.vue';
import { articleListApi } from '../api/blog';

const ViewData = ref({
    currPage: 1,
    total: 10,
    articleList: []
});
const renderArticleList = async () => {
    try {
        let data = await articleListApi({
            page:ViewData.currPage,
        });
        data = data.data;
        if (data.articleList.length > 0) {
            ViewData.value.articleList = data.articleList
        }
        ViewData.value.total = data.total

    } catch (error) {
        console.error('Error fetching the list:', error);
    }
};
const handlePageChanged = (newPage) => {
  console.log('Current page changed to:', newPage);

  ViewData.value.currPage = newPage;
  renderArticleList()
};
onMounted(() => {
    renderArticleList();
});
</script>

<style scoped>
.ArticleListComp {
    display: flex;
    flex-direction: column;
    /* Stacks items vertically */
    justify-content: center;
    /* Centers horizontally */
    align-items: center;
    /* Centers vertically if needed */
    gap: 2rem;
    /* Sets the space between each article item */
    padding: 1rem;
    /* Adjust padding if necessary */
}
</style>