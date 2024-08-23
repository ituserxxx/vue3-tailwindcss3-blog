<template>
    <div class="ArticleListComp dark:bg-gray-100 dark:text-gray-900">

        <div v-for="article in articles" :key="article.id" class="max-w-4xl px-10 py-6 bg-white rounded-lg shadow-md">
            <div class="flex justify-between items-center">
                <span class="font-light text-gray-600">{{ article.date }}</span>
            </div>
            <div class="mt-2">
                <a class="text-2xl text-gray-700 font-bold hover:underline" :href="article.link">
                    {{ article.title }}
                </a>
                <p class="mt-2 text-gray-600">{{ article.body }}</p>
            </div>
            <div class="flex justify-between items-center mt-4">
                <a class="text-blue-500 hover:underline" :href="article.readMoreLink">Read more</a>
                <div>
                    <a v-for="tag in article.tags" :key="tag"
                        class="px-2 py-1 bg-gray-600 text-gray-100 font-bold rounded hover:bg-gray-500"
                        :href="tag.link">
                        {{ tag.name }}
                    </a>
                </div>
            </div>
        </div>
        <ul class="flex">
            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-500 rounded-lg">
                <a class="flex items-center font-bold" href="#">previous</a>
            </li>
            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-700 hover:bg-gray-700 hover:text-gray-200 rounded-lg">
                <a class="font-bold" href="#">1</a>
            </li>
            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-700 hover:bg-gray-700 hover:text-gray-200 rounded-lg">
                <a class="font-bold" href="#">2</a>
            </li>
            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-700 hover:bg-gray-700 hover:text-gray-200 rounded-lg">
                <a class="font-bold" href="#">3</a>
            </li>
            <li class="mx-1 px-3 py-2 bg-gray-200 text-gray-700 hover:bg-gray-700 hover:text-gray-200 rounded-lg">
                <a class="flex items-center font-bold" href="#">Next</a>
            </li>
        </ul>
    </div>
</template>
<script>
export default {
    data() {
        return {
            articles: [
                {
                    id: 1,
                    date: 'Jun 1, 2024',
                    title: 'Build Your New Idea with Vue 3 Framework.',
                    body: 'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Tempora expedita dicta totam aspernatur doloremque. Excepturi iste iusto eos enim reprehenderit nisi, accusamus delectus nihil quis facere in modi ratione libero!',
                    link: '/articleDetail',
                    readMoreLink: '#',
                    tags: [
                        { name: 'Vue 3', link: '#' },
                        { name: 'JavaScript', link: '#' }
                    ]
                },
                {
                    id: 2,
                    date: 'Jul 15, 2024',
                    title: 'Explore the Latest Features of Vue 3',
                    body: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
                    link: '/articleDetail',
                    readMoreLink: '#',
                    tags: [
                        { name: 'Features', link: '#' },
                        { name: 'Updates', link: '#' }
                    ]
                },
                {
                    id: 2,
                    date: 'Jul 15, 2024',
                    title: 'Explore the Latest Features of Vue 3',
                    body: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
                    link: '/articleDetail',
                    readMoreLink: '#',
                    tags: [
                        { name: 'Features', link: '#' },
                        { name: 'Updates', link: '#' }
                    ]
                },
                {
                    id: 2,
                    date: 'Jul 15, 2024',
                    title: 'Explore the Latest Features of Vue 3',
                    body: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
                    link: '/articleDetail',
                    readMoreLink: '#',
                    tags: [
                        { name: 'Features', link: '#' },
                        { name: 'Updates', link: '#' }
                    ]
                }
            ]
        }
    }
}
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