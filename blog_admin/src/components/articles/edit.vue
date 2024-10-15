<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 13 }">

    <a-form-item ref="title" label="Title" name="title">
      <a-input v-model:value="formState.title" />
    </a-form-item>
    <a-form-item label="Status" name="status">
      <a-radio-group v-model:value="formState.status">
        <a-radio value="1">发布</a-radio>
        <a-radio value="2">草稿</a-radio>
      </a-radio-group>
    </a-form-item>
    <a-form-item label="Tags" name="tagsId">
      <a-checkbox-group v-model:value="formState['tagsId']">
        <a-row>
          <a-col v-for="item in tagsList" :key="item.id" :span="8">
            <a-checkbox v-model:checked="formState.tagsId" :value="item.id" style="line-height: 32px">
              {{ item.name }}
            </a-checkbox>
          </a-col>
        </a-row>
      </a-checkbox-group>
    </a-form-item>
    <a-form-item label="Create time" required name="createTime">
      <a-date-picker v-model:value="formState.createTime" show-time format="YYYY-MM-DD HH:mm:ss"
        value-format="YYYY-MM-DD HH:mm:ss" type="date" style="width: 50%" />
    </a-form-item>
    <a-form-item label="Update time" name="updateTime">
      <a-date-picker v-model:value="formState.updateTime" show-time format="YYYY-MM-DD HH:mm:ss"
        value-format="YYYY-MM-DD HH:mm:ss" type="date" style="width: 50%" />
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
import { reactive, ref, defineProps } from 'vue';
import { MdEditor } from "md-editor-v3";
import { message } from 'ant-design-vue';
import "md-editor-v3/lib/style.css";
import { ApiArticlesUpdate, ApiArticlesInfo } from '../../api/articles.js';
import { ApiTagsList } from '../../api/tags.js';
const props = defineProps({
  id: {
    type: Number, // 或者 String，取决于你的需求
    required: true,
  },
});
const formRef = ref();

const tagsList = ref([]);
const formState = reactive({
  id: props.id,
  title: '',
  content: '',
  status: "",
  tagsId: [],
  createTime: "",
  updateTime: "",
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
      let data = await ApiArticlesUpdate(formState);
      console.log(" resp data", data.data)
      if (data.code === 0) {
        message.success('success');
      } else {
        message.error(data.data.msg);
      }
    })
    .catch(error => {
      console.log('error', error);
    });
};

const renderArticlesInfo = async () => {
  let data = await ApiArticlesInfo({
    id: props.id
  });
  if (data.code === 0) {
    formState.title = data.data.title
    formState.status = String(data.data.status)
    formState.content = data.data.content
    formState.tagsId = data.data.tagsIdList
    formState.createTime = data.data.createTime
    formState.updateTime = data.data.updateTime
  } else {
    message.error(data.msg);
  }
};
const renderTagsList = async () => {
  let data = await ApiTagsList({
    page: -33,
  });
  tagsList.value = data.data.list
};

renderTagsList();
renderArticlesInfo();


</script>

<style scoped></style>