import './assets/tailwind.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'highlight.js/styles/atom-one-dark.css' //样式
 

const app = createApp(App)
app.use(router)


app.mount('#app')
