<template>
  <form action="">
    <div class="flex items-center gap-0 mb-4">
      <label for="username" class="font-semibold w-24">用户名/邮箱</label>
      <InputText id="username" inp class="flex-auto" autocomplete="off" required />
    </div>
    <div class="flex items-center gap-0 mb-16">
      <label for="email" class="font-semibold w-24">密码</label>
      <Password
        id="password"
        class="flex-auto"
        v-model="value"
        promptLabel="选择一个密码"
        weakLabel="再难点呢"
        mediumLabel="一般般吧"
        strongLabel="可以了"
        required
        toggle-mask
      />
    </div>
    <div class="flex justify-end gap-2">
      <Button
        type="button"
        @click="sign_up"
        label="没有账号？注册"
        severity="secondary"
      ></Button>
      <Button class="w-24" type="submit" label="登录"></Button>
    </div>
  </form>
</template>

<script setup></script>

<style lang="scss" scoped></style>
