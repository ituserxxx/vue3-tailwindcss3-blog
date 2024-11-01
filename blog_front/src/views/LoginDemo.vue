<template>
    <div class="login-container">
      <header>
        <img src="@/assets/logo.svg" alt="Logo" class="logo" />
        <h1>禅道Bug统计分析</h1>
      </header>
      <main>
        <div class="login-box">
          <h2>登录</h2>
          <input type="text" placeholder="请输入账号" v-model="username" />
          <input type="password" placeholder="请输入密码" v-model="password" />
          <button @click="login">登录</button>
        </div>
      </main>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        username: "",
        password: ""
      };
    },
    methods: {
      login() {
        // 在这里处理登录逻辑，比如发送请求到服务器
        console.log("登录尝试，用户名:", this.username, "密码:", this.password);
        
        // 模拟登录成功
        if (this.username === "user" && this.password === "pass") {
          alert("登录成功！");
        } else {
          alert("登录失败，请检查用户名和密码。");
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background-color: #f5f5f5;
  }
  
  .logo {
    width: 100px;
    margin-bottom: 20px;
  }
  
  .login-box {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  .login-box h2 {
    margin-bottom: 20px;
  }
  
  .login-box input {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  .login-box button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .login-box button:hover {
    background-color: #0056b3;
  }
  </style>