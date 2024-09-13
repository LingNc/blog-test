<template>
  <div class="about">
    <h1>This is an about page</h1>
    <LoginForm />
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue';

export default {
  components: {
    LoginForm
  }
};
</script>