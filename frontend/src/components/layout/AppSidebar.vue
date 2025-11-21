<template>
  <aside class="w-64 bg-white shadow-lg border-r border-gray-200 flex-shrink-0">
    <div class="p-6">
      <div class="flex items-center space-x-3 mb-10">
        <div class="w-10 h-10 bg-blue-500 rounded-lg flex items-center justify-center text-white font-bold">
          MB
        </div>
        <div>
          <p class="text-xs text-gray-500 font-semibold uppercase tracking-wide">MiniBite</p>
          <p class="text-lg font-bold text-gray-800">가치 있는 한 끼</p>
        </div>
      </div>

      <nav class="space-y-1">
        <RouterLink
          v-for="item in navItems"
          :key="item.path"
          :to="item.path"
          class="flex items-center space-x-3 px-4 py-3 rounded-lg font-medium transition-colors"
          :class="isActive(item.path) ? 'bg-blue-500 text-white' : 'text-gray-700 hover:bg-gray-100'"
        >
          <span class="w-5 text-center">{{ item.icon }}</span>
          <span>{{ item.label }}</span>
        </RouterLink>
      </nav>
    </div>
  </aside>
</template>

<script setup>
import { computed } from 'vue';
import { RouterLink, useRoute } from 'vue-router';

const route = useRoute();

const navItems = [
  { path: '/dashboard', label: '관리', icon: '📊' },
  { path: '/group', label: '그룹', icon: '👥' },
  { path: '/feed', label: '공유', icon: '🌐' },
  { path: '/my', label: 'MY', icon: '🙋' },
];

const isActive = (path) => computed(() => route.path.startsWith(path)).value;
</script>
