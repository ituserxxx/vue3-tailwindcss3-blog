import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://172.16.9.103:6008', // 目标 API 的地址
        changeOrigin: true, // 支持跨域
        rewrite: (path) => path.replace(/^\/api/, ''), // 重写路径
      },
    },
  }
})
