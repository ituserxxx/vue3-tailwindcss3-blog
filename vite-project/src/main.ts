import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue';
import router from './router/index.js';
import 'ant-design-vue/dist/reset.css';
createApp(App).use(Antd).use(router).mount('#app')
