<script setup>
import { computed } from 'vue';
import { RouterView, useRoute } from 'vue-router';
import AppShell from './components/layout/AppShell.vue';
import AuthLayout from './components/layout/AuthLayout.vue';

const route = useRoute();
const layoutComponent = computed(() => (route.meta.layout === 'auth' ? AuthLayout : AppShell));
</script>

<template>
  <component :is="layoutComponent">
    <RouterView />
  </component>
</template>
