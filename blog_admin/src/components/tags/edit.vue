<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">

    <a-form-item ref="name" label="Name" name="name">
      <a-input v-model:value="formState.name" />
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">Commit</a-button>
    </a-form-item>
    <span v:model:value="formState.id" hidden></span>
  </a-form>


</template>
<script setup>
import { reactive, ref, toRaw, defineProps } from 'vue';
import { message } from 'ant-design-vue';
import { ApiTagsInfo, ApiTagsUpdate } from '../../api/tags.js';
const props = defineProps({
  id: {
    type: Number, // 或者 String，取决于你的需求
    required: true,
  },
});
const formRef = ref();
const labelCol = {
  span: 5,
};
const wrapperCol = {
  span: 13,
};
const formState = reactive({
  id: props.id,
  name: '',
});
const rules = {
  name: [
    {
      required: true,
      message: 'Please input  name',
      trigger: 'change',
    },
    {
      min: 3,
      max: 50,
      message: 'Length should be than 3 character',
      trigger: 'blur',
    },
  ],
};


const renderTagsInfo = async () => {
  let data = await ApiTagsInfo({
    id: props.id
  });
  if (data.code === 0) {
    formState.name = data.data.name
  } else {
    message.error(data.data.msg);
  }

};
renderTagsInfo();

const onSubmit = () => {
  formRef.value
    .validate()
    .then(async () => {
      let data = await ApiTagsUpdate(formState);
      if (data.code === 0) {
        console.log("修改succ ", data.data)
        message.success("success");
      } else {
        message.error(data.data.msg);
      }
    })
    .catch(error => {
      console.log('error', error);
    });
};


</script>

<style scoped></style>