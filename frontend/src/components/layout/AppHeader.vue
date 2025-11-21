<template>
  <header class="bg-white border-b border-gray-200 px-8 py-6">
    <div class="flex items-center justify-between">
      <div>
        <p class="text-xs text-blue-500 font-semibold uppercase tracking-wide mb-1">Today</p>
        <h1 class="text-2xl font-bold text-gray-800">{{ pageTitle }}</h1>
        <p class="text-gray-600 mt-1 text-sm">{{ pageSubtitle }}</p>
      </div>
      <div class="flex space-x-3">
        <button
          class="flex items-center space-x-2 px-4 py-2 bg-blue-100 text-blue-700 rounded-lg hover:bg-blue-200 transition-colors"
        >
          <span>➕</span>
          <span>기록 추가</span>
        </button>
        <button
          class="flex items-center space-x-2 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors"
        >
          <span>📄</span>
          <span>리포트 생성</span>
        </button>
      </div>
    </div>
  </header>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const pageTitle = computed(() => {
  switch (route.name) {
    case 'dashboard':
      return '관리 대시보드';
    case 'group':
      return '그룹';
    case 'feed':
      return '공유 피드';
    case 'my':
      return 'MY 프로필';
    default:
      return 'MiniBite';
  }
});

const pageSubtitle = computed(() => {
  switch (route.name) {
    case 'dashboard':
      return "AI 추천 · 기록 · 통계를 한눈에 확인하세요.";
    case 'group':
      return '동기 커뮤니티와 함께 챌린지를 이어가요.';
    case 'feed':
      return '가치 인증 피드를 살펴보세요.';
    case 'my':
      return '내 프로필과 건강 정보를 관리하세요.';
    default:
      return '가치 있는 한 끼, MiniBite.';
  }
});
</script>
