<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">

    <a-form-item ref="username" label="Login Name" name="username">
      <a-input v-model:value="formState.username" />
    </a-form-item>

    <a-form-item label="Password" name="passwd">
      <a-input v-model:value="formState.passwd" />
    </a-form-item>

    <!-- <a-form-item label="Availability or not" name="status">
      <a-switch v-model:checked="formState.status" />
    </a-form-item> -->

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">Create</a-button>
    </a-form-item>

  </a-form>
</template>
<script setup>
import { reactive, ref, toRaw } from 'vue';
import { ApiUserAdd } from '../../api/users.js';
import { message } from 'ant-design-vue';
const formRef = ref();
const labelCol = {
  span: 5,
};
const wrapperCol = {
  span: 13,
};
const formState = reactive({
  username: '',
  passwd: '',
  // status: false,
});
const rules = {
  username: [
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
const onSubmit = () => {
  formRef.value
    .validate()
    .then(async () => {
      console.log(" form data", formState)
      // console.log('values', formState, toRaw(formState));
      let data = await ApiUserAdd(formState);
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