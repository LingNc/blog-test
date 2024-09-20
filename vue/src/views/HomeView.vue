<script setup>
import { ref } from 'vue';

const isLoggedIn = ref(false);

function login(){
  isLoggedIn.value = true;
};

function logout(){
  isLoggedIn.value = false;
};
</script>

<template>
  <header class="forum-header">
    <div class="logo">
      <img src="@/assets/logo.svg" alt="Forum Logo" />
    </div>
    <nav class="nav-links">
      <ul>
        <li><router-link to="/">首页</router-link></li>
        <li><router-link to="/forum">论坛</router-link></li>
        <li><router-link to="/about">关于我们</router-link></li>
        <li><router-link to="/contact">联系我们</router-link></li>
      </ul>
    </nav>
    <div class="user-actions">
      <button v-if="!isLoggedIn" @click="login">登录</button>
      <button v-else @click="logout">退出</button>
    </div>
  </header>
</template>

<style scoped>
.forum-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #333;
  color: white;

  position: relative;
  top: 0;
  left: 0;
}

.logo img {
  height: 40px;
}

.nav-links ul {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
}

.nav-links li {
  margin-right: 20px;
}

.nav-links a {
  color: white;
  text-decoration: none;
}

.nav-links a:hover {
  text-decoration: underline;
}

.user-actions button {
  background-color: #555;
  color: white;
  border: none;
  padding: 8px 16px;
  cursor: pointer;
}

.user-actions button:hover {
  background-color: #777;
}
</style>
