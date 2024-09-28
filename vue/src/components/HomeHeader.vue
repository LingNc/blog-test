<script setup>
import { ref } from "vue";
import Login from "./Login.vue";
import Menubar from "primevue/menubar";
import Badge from "primevue/badge";
import InputText from "primevue/inputtext";
import "primeicons/primeicons.css";
const isLoggedIn = ref(false);

function login() {
  isLoggedIn.value = true;
}

function logout() {
  isLoggedIn.value = false;
}
const items = ref([
  {
    label: "主页",
    icon: "pi pi-home",
  },
  {
    label: "文章",
    icon: "pi pi-star",
  },
  {
    label: "项目",
    icon: "pi pi-envelope",
  },
  {
    label: "设置",
    icon: "pi pi-search",
    items: [
      {
        label: "核心",
        icon: "pi pi-bolt",
        shortcut: "⌘+S",
      },
      {
        label: "应用",
        icon: "pi pi-server",
        shortcut: "⌘+B",
      },
      {
        label: "框架",
        icon: "pi pi-pencil",
        shortcut: "⌘+U",
      },
      {
        separator: true,
      },
      {
        label: "风格",
        icon: "pi pi-palette",
        items: [
          {
            label: "1",
            icon: "pi pi-palette",
          },
          {
            label: "2",
            icon: "pi pi-palette",
          },
        ],
      },
    ],
  },
]);
</script>

<template>
  <div class="card" style="width: 100vw">
    <Menubar :model="items" class="card1">
      <template #start>
        <span class="font-bold text-color">Blog</span>
      </template>
      <template #item="{ item, props, hasSubmenu, root }">
        <a v-ripple class="flex items-center" v-bind="props.action">
          <span :class="item.icon" />
          <span class="ml-2">{{ item.label }}</span>
          <Badge
            v-if="item.badge"
            :class="{ 'ml-auto': !root, 'ml-2': root }"
            :value="item.badge"
          />
          <span
            v-if="item.shortcut"
            class="ml-auto border border-surface rounded bg-emphasis text-muted-color text-xs p-1"
            >{{ item.shortcut }}</span
          >
          <i
            v-if="hasSubmenu"
            :class="[
              'pi pi-angle-down',
              { 'pi-angle-down ml-2': root, 'pi-angle-right ml-auto': !root },
            ]"
          ></i>
        </a>
      </template>
      <template #end>
        <div class="flex items-center gap-2">
          <InputText placeholder="搜索" type="text" class="w-32 sm:w-auto" />
          <Login></Login>
        </div>
      </template>
    </Menubar>
  </div>
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

.setting {
  display: flex;
  justify-content: center;
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
