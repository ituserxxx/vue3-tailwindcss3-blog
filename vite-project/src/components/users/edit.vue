<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">

    <a-form-item ref="name" label="Login Name" name="name">
      <a-input v-model:value="formState.name" />
    </a-form-item>

    <a-form-item label="Password" name="passwd">
      <a-input v-model:value="formState.passwd" />
    </a-form-item>

    <a-form-item label="Availability or not" name="status">
      <a-switch v-model:checked="formState.status" />
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
import { ApiUserInfo, ApiUserUpdate } from '../../api/blog.js';
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
  passwd: '',
  status: false,
});
const rules = {
  name: [
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
  ],
  passwd: [
    {
      required: true,
      message: 'Please input Login Password',
      trigger: 'change',
    },
    {
      min: 6,
      max: 50,
      message: 'Length should be than 6 character',
      trigger: 'blur',
    },
  ],
};


const renderUserInfo = async () => {
  let data = await ApiUserInfo({
    id: props.id
  });
  if (data.code === 0) {
    formState.name = data.data.name
    formState.passwd = data.data.passwd
    formState.status = data.data.status === 1 ? true : false
    // message.success('success');
  } else {
    message.error(data.data.msg);
  }

};
renderUserInfo();


const onSubmit = () => {
  formRef.value
    .validate()
    .then(async () => {
      console.log('values', formState, toRaw(formState));
      let data = await ApiUserUpdate(formState);
      if (data.code === 0) {
        console.log("修改succ ", data.data)
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