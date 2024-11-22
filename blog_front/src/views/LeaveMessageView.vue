<template>

  <!-- 留言框 -->
  <div class="border border-gray-300 p-4 rounded-lg max-w-xl mx-auto mt-20">
    <h2 class="text-lg font-medium text-center mb-4">Leave a message</h2>
    <form>
      <div class="flex items-center mb-3">
        <label class="text-gray-700 font-medium w-1/4 text-right mr-4" for="name">
          Name
        </label>
        <input v-model="formData.name"
          class="appearance-none border rounded w-3/4 py-1.5 px-2 text-gray-700 leading-tight focus:outline-none focus:border-gray-500"
          id="name" type="text" placeholder="Enter Your Individual Name">
      </div>
      <div class="flex items-center mb-3">
        <label class="text-gray-700 font-medium w-1/4 text-right mr-4" for="message">
          Message
        </label>
        <textarea rows="3" v-model="formData.message"
          class="appearance-none border rounded w-3/4 py-1.5 px-2 text-gray-700 leading-tight focus:outline-none focus:border-gray-500"
          id="message" placeholder="Enter Your Message Content"></textarea>
      </div>
      <div class="flex justify-end">
        <button
          class="bg-blue-500 hover:bg-blue-700 text-white font-medium py-1.5 px-3 rounded focus:outline-none focus:shadow-outline"
          type="button" @click="handleCommit">Commit</button>
      </div>
    </form>
  </div>

  <section class="bg-white dark:bg-gray-900 py-8 lg:py-16 antialiased">
    <div class="max-w-2xl mx-auto px-4">


      <article v-for="(leaveMessage, index) in ViewData.leaveMessageList" :key="index"
        class="p-6 text-base bg-white rounded-lg dark:bg-gray-900">
        <footer class="flex justify-between items-center mb-2">
          <div class="flex items-center">
            <p class="inline-flex items-center mr-3 text-sm text-gray-900 dark:text-white font-semibold">
              <img class="mr-2 w-6 h-6 rounded-full" src="https://flowbite.com/docs/images/people/profile-picture-2.jpg"
                alt="Michael Gough">{{ leaveMessage.name }}
            </p>

            <p class="text-sm text-gray-600 dark:text-gray-400">
              <time pubdate datetime=""  >{{ leaveMessage.createTime }}</time>
            </p>
          </div>
        </footer>

        <p class="text-gray-500 dark:text-gray-400">
          {{ leaveMessage.content }}
        </p>
      </article>

    </div>
    <article
      class="max-w-2xl mx-auto px-4 p-6 text-base bg-white border-t border-gray-200 dark:border-gray-700 dark:bg-gray-900">
      <button
        class="align-middle select-none font-sans font-bold text-center uppercase transition-all disabled:opacity-50 disabled:shadow-none disabled:pointer-events-none text-xs py-3 px-6 bg-gray-900 text-white shadow-md shadow-gray-900/10 hover:shadow-lg hover:shadow-gray-900/20 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none rounded-full"
        type="button" @click="handleMoreLeaveMessage">
        More
      </button>
    </article>
  </section>


  <div v-if="formData.showAlert"
    class="bg-red-200 border-red-600 text-red-600 border-l-4 p-4 fixed top-0 left-0 w-full z-50" role="alert">
    <p class="font-bold">
      Be Warned
    </p>
    <p>
      留言内容不能为空
    </p>
  </div>

</template>

<script setup>
import { ref, onMounted, } from 'vue';
import { leaveMessageListApi, leaveMessageAddApi } from '../api/blog';
const formData = ref({
  name: '',
  message: '',
  showAlert: false,
});
const ViewData = ref({
  currPage: 1,
  leaveMessageList: [],
})

onMounted(() => {
  renderLeaveMessageList();
});

const handleMoreLeaveMessage = async () => {
  ViewData.value.currPage +=1
  renderLeaveMessageList();
}

const handleCommit = async () => {
  if (!formData.value.message) {
    formData.value.showAlert = true;
    setTimeout(() => {
      formData.value.showAlert = false;
    }, 3000);
    return;
  }

  try {
    let data = await leaveMessageAddApi({
      name: formData.value.name,
      content: formData.value.message,
    });

    if (data.code > 0) {
      console.error('Error fetching the list:');
      return
    }
    data = data.data;
    ViewData.value.leaveMessageList.unshift({
      id: data.id,
      name: data.name,
      content: data.content,
      createTime: data.createTime,
    });
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
}

const renderLeaveMessageList = async () => {
  try {
    let data = await leaveMessageListApi({
      page: ViewData.value.currPage,
    });
    data = data.data;
    if (data.code > 0) {
      console.error('Error fetching the list:');
      return
    }
    ViewData.value.leaveMessageList.push(...data.list);
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
};

</script>