<template>
  <div class="flex min-h-screen bg-gray-50 text-gray-900">
    <AppSidebar />
    <div class="flex flex-col flex-1">
      <AppHeader />
      <main class="flex-1 overflow-y-auto">
        <slot />
      </main>
    </div>
  </div>
</template>

<script setup>
import AppSidebar from './AppSidebar.vue';
import AppHeader from './AppHeader.vue';
</script>
