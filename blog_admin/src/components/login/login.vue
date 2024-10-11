<template>
  <div class="form-container">
    <a-form :model="formState" name="basic" :label-col="{ span: 8 }" :wrapper-col="{ span: 16 }" autocomplete="off"
      @finish="onFinish" @finishFailed="onFinishFailed">
      <a-form-item label="Username" name="username"
        :rules="[{ required: true, message: 'Please input your username!' }]">
        <a-input v-model:value="formState.username" />
      </a-form-item>

      <a-form-item label="Password" name="password"
        :rules="[{ required: true, message: 'Please input your password!' }]">
        <a-input-password v-model:value="formState.password" />
      </a-form-item>

      <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
        <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button type="primary" html-type="submit">Submit</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { ApiUserLogin } from '../../api/users.js';
import { message } from 'ant-design-vue';
const store = useStore();

const formState = reactive({
  username: '',
  password: '',
  remember: true,
});

const router = useRouter();

const onFinish = async values => {
  console.log('login req info:', values);

  let data = await ApiUserLogin({
    username: formState.username,
    passwd: formState.password
  });
  if (data.code === 0) {
    message.success('登录成功（^_^）');
    store.commit("update_uid", data.data)
    // 跳转到目标页面
    router.push('/home'); // 替换为你想跳转的页面路径
  } else {
    message.error(data.data.msg);
  }

};

const onFinishFailed = errorInfo => {
  console.log('Failed:', errorInfo);
};
</script>

<style scoped>
.form-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  /* 使容器高度为视口高度 */
}
</style>
