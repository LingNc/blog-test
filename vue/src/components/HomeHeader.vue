<script setup>
import { ref } from "vue";
import { ElRow, ElCol, ElButton } from "element-plus";
const isLoggedIn = ref(false);

function login() {
  isLoggedIn.value = true;
}

function logout() {
  isLoggedIn.value = false;
}
</script>

<template>
  <header class="forum-header">
    <div class="logo">
      <img src="@/assets/logo.svg" alt="Forum Logo" />
    </div>
    <nav>
      <el-row class="ha">
        <el-col :span="8"><router-link to="/">首页</router-link></el-col>
        <el-col :span="8"><router-link to="/forum">论坛</router-link></el-col>
        <el-col :span="8"><router-link to="/about">关于我们</router-link></el-col>
      </el-row>
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
.ha {
  width: 50vw;
}

.logo img {
  height: 40px;
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
