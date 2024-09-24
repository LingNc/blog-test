<template>
  <div class="card flex justify-center">
    <Button class="w-16" label="Show" @click="vis_sign_in = true" />
    <Dialog v-model:visible="vis_sign_in" modal :style="{ width: '25rem' }">
      <template #header>
        <div class="inline-flex items-center justify-center gap-2">
          <span class="font-bold whitespace-nowrap text-2xl">Blog-test</span>
        </div>
      </template>
      <span class="text-surface-500 dark:text-surface-400 block mb-8">登录账号</span>
      <sign_in />
    </Dialog>
    <Dialog v-model:visible="vis_sign_up" modal :style="{ width: '25rem' }">
      <template #header>
        <div class="inline-flex items-center justify-center gap-2">
          <span class="font-bold whitespace-nowrap text-2xl">Blog-test</span>
        </div>
      </template>
      <span class="text-surface-500 dark:text-surface-400 block mb-8">注册账号</span>
      <sign_up />
    </Dialog>
  </div>
</template>

<script setup>
import { ref } from "vue";
import Dialog from "primevue/dialog";
import Button from "primevue/button";
import sign_in from "./Login/sign_in.vue";
import sign_up from "./Login/sign_up.vue";

const vis_sign_in = ref(false);
const vis_sign_up = ref(false);
const value = ref("");
</script>
