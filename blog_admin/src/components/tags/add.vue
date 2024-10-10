<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">

    <a-form-item ref="name" label="Name" name="name">
      <a-input v-model:value="formState.name" />
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">Create</a-button>
    </a-form-item>

  </a-form>
</template>
<script setup>
import { reactive, ref, toRaw } from 'vue';
import { ApiTagsAdd } from '../../api/tags.js';
import { message } from 'ant-design-vue';
const formRef = ref();
const labelCol = {
  span: 5,
};
const wrapperCol = {
  span: 13,
};
const formState = reactive({
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
const onSubmit = () => {
  formRef.value
    .validate()
    .then(async () => {
      console.log(" form data", formState)
      let data = await ApiTagsAdd(formState);
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

</script>

<style scoped></style>