<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 13 }">

    <a-form-item ref="title" label="Title" name="title">
      <a-input v-model:value="formState.title" />
    </a-form-item>
    <a-form-item label="状态" name="status">
      <a-radio-group v-model:value="formState.status">
        <a-radio value="1">发布</a-radio>
        <a-radio value="2">草稿</a-radio>
      </a-radio-group>
    </a-form-item>
    <a-form-item label="文章标签" name="tagsId">
      <a-checkbox-group v-model:value="formState['tagsId']">
        <a-row>
          <a-col v-for="item in tagsList" :key="item.id" :span="8">
            <a-checkbox v-model:checked="selectedValues" :value="item.id" style="line-height: 32px">
              {{ item.name }}
            </a-checkbox>
          </a-col>
        </a-row>
      </a-checkbox-group>
    </a-form-item>

    <a-form-item label="Content">
      <br><br>
      <md-editor v-model="formState.content" />
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">Submit</a-button>
    </a-form-item>

  </a-form>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { MdEditor } from "md-editor-v3";
import { message } from 'ant-design-vue';
import "md-editor-v3/lib/style.css";
import { ApiArticlesAdd } from '../../api/articles.js';
import { ApiTagsList } from '../../api/tags.js';
const formRef = ref();
const selectedValues = ref(); // 存储选中的复选框值
const tagsList = ref([
  // { id: 1, name: 'A' },
  // { id: 2, name: 'B' },
  // { id: 3, name: 'C' },
  // { id: 4, name: 'C' },
  // { id: 5, name: 'C' },
  // { id: 6, name: 'C' },
  // { id: 7, name: 'C' },
  // { id: 8, name: 'C' },
]);
const formState = reactive({
  title: '',
  content: '',
  status: 1,
  tagsId: [],
});
const rules = {
  title: [
    {
      required: true,
      message: 'Please input Login name',
      trigger: 'change',
    },
    {
      min: 3,
      max: 50,
      message: 'Length should be than 3 character',
      trigger: 'blur',
    },
  ]
};
const onSubmit = () => {
  formRef.value
    .validate()
    .then(async () => {
      console.log(" form data", formState)
      let data = await ApiArticlesAdd(formState);
      console.log(" resp data", data.data)
      if (data.code === 0) {
        message.success('success');
      } else {
        message.error(data.msg);
      }
    })
    .catch(error => {
      console.log('error', error);
    });
};
// 渲染表格数据
const renderTagsList = async () => {
  let data = await ApiTagsList({
    page: -33,
  });
  tagsList.value = data.data.list
};

renderTagsList();

</script>

<style scoped></style>