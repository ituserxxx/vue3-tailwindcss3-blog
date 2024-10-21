<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">

    <a-form-item ref="name" label="Name" name="name">
      <a-input v-model:value="formState.name" />
    </a-form-item>

    <a-form-item label="Content" name="content">
      <a-input v-model:value="formState.content" />
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">Create</a-button>
    </a-form-item>

  </a-form>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { message } from 'ant-design-vue';
import { ApiLeaveMessageAdd } from '../../api/leaveMessage.js';
const formRef = ref();
const labelCol = {
  span: 5,
};
const wrapperCol = {
  span: 13,
};
const formState = reactive({
  name: '',
  content: '',
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
  content: [
    {
      required: true,
      message: 'Please input content',
      trigger: 'change',
    },
    {
      min: 4,
      max: 200,
      message: 'Length should be than 6 character',
      trigger: 'blur',
    },
  ],
};
const onSubmit = () => {
  formRef.value
    .validate()
    .then(async () => {
      let data = await ApiLeaveMessageAdd(formState);
      console.log(data);
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

</script>

<style scoped></style>