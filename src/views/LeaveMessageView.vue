<template>

  <!-- 留言框 -->
  <div class="border border-gray-300 p-4 rounded-lg max-w-xl mx-auto mt-20">
    <h2 class="text-lg font-medium text-center mb-4">Leave a message</h2>
    <form>
      <div class="flex items-center mb-3">
        <label class="text-gray-700 font-medium w-1/4 text-right mr-4" for="name">
          Name
        </label>
        <input
          class="appearance-none border rounded w-3/4 py-1.5 px-2 text-gray-700 leading-tight focus:outline-none focus:border-gray-500"
          id="name" type="text" placeholder="Enter Your Individual Name">
      </div>
      <div class="flex items-center mb-3">
        <label class="text-gray-700 font-medium w-1/4 text-right mr-4" for="message">
          Message
        </label>
        <textarea rows="3"
          class="appearance-none border rounded w-3/4 py-1.5 px-2 text-gray-700 leading-tight focus:outline-none focus:border-gray-500"
          id="message" placeholder="Enter Your Message Content"></textarea>
      </div>
      <div class="flex justify-end">
        <button
          class="bg-blue-500 hover:bg-blue-700 text-white font-medium py-1.5 px-3 rounded focus:outline-none focus:shadow-outline"
          type="button">Commit</button>
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
              <time pubdate datetime="2022-02-08" title="February 8th, 2022">{{ leaveMessage.date }}</time>
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
        type="button">
        More
      </button>
    </article>
  </section>
</template>

<script setup>
const ViewData = {
  leaveMessageList: [
    {
      id: 1,
      name: "Michael Gough",
      content: `   Very straight-to-point article. Really worth time reading. Thank
          you! But tools are just the
          instruments for the UX designers. The knowledge of the design tools are as important as the
          creation of the design strategy.`,
      date: "2022-03-04 15:12:33",
    },
    {
      id: 1,
      name: "Michael Gough2",
      content: `   Very straight-to-point article. Really worth time reading. Thank you!  `,
      date:  "2024-03-04 15:11:33",
    }
  ],
}

</script>