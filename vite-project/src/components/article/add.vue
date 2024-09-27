<template>
  <a-form ref="formRef" :model="formState" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">

    <a-form-item ref="title" label="Title" name="title">
      <a-input v-model:value="formState.title" />
    </a-form-item>
    <a-form-item label="状态" name="status">
      <a-radio-group v-model:value="formState.status">
        <a-radio value="1">发布</a-radio>
        <a-radio value="2">草稿</a-radio>
      </a-radio-group>
    </a-form-item>
    <a-form-item label="文章标签"  name="tagsId">
      <a-checkbox-group v-model:value="formState['tagsId']">
        <a-row>
          <a-col :span="8">
            <a-checkbox value="A" style="line-height: 32px">A</a-checkbox>
          </a-col>
          <a-col :span="8">
            <a-checkbox value="B" style="line-height: 32px">B</a-checkbox>
          </a-col>
          <a-col :span="8">
            <a-checkbox value="C" style="line-height: 32px">C</a-checkbox>
          </a-col>
          <a-col :span="8">
            <a-checkbox value="D" style="line-height: 32px">D</a-checkbox>
          </a-col>
          <a-col :span="8">
            <a-checkbox value="E" style="line-height: 32px">E</a-checkbox>
          </a-col>
          <a-col :span="8">
            <a-checkbox value="F" style="line-height: 32px">F</a-checkbox>
          </a-col>
        </a-row>
      </a-checkbox-group>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">Create</a-button>
    </a-form-item>


  </a-form>
</template>
<script setup>
import { reactive, ref} from 'vue';
import { ApiUserAdd } from '../../api/users.js';

const formRef = ref();
const labelCol = {
  span: 5,
};
const wrapperCol = {
  span: 13,
};
const formState = reactive({
  title: '',
  content: '',
  status: '',
  tagsId: [],
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
  resource: [
    {
      required: true,
      message: 'Please select activity resource',
      trigger: 'change',
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