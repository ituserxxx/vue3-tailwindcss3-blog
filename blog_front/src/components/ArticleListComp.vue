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
        <PageComp :currPage="ViewData.currPage" :total="ViewData.total" @update:currPage="handlePageChange" />
    </div>
</template>
<script setup>
import { ref, defineProps, onMounted, watch } from 'vue';
import PageComp from './PageComp.vue';
import { articleListApi } from '../api/blog';
const props = defineProps({
    currentTagId: Number,
});
const ViewData = ref({
    currentTagId: Number(props.currentTagId == undefined ? 0 : props.currentTagId),
    currPage: 1,
    total: 10,
    articleList: []
});
watch(() => props.currentTagId, (newId) => {
    ViewData.value.currentTagId = newId;
    renderArticleList()
});
const renderArticleList = async () => {
    try {
        let data = await articleListApi({
            page: ViewData.value.currPage,
            tagId: ViewData.value.currentTagId,
        });
        console.log(data);
        if (data.code > 0) {
            console.error('Error fetching the list:');
            return
        }
        ViewData.value.articleList = data.data.list
        ViewData.value.total = data.data.total
    } catch (error) {
        console.error('Error fetching the list:', error);
    }
};

// 处理页码变化
const handlePageChange = (newPage) => {
    ViewData.value.currPage = newPage;
    renderArticleList()
}

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