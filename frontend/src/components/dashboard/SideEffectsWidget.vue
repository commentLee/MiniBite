<template>
  <div id="side-effects-correlation" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6 space-y-4">
    <div class="flex items-center justify-between mb-2">
      <h3 class="text-lg font-bold text-gray-800">[AI] 부작용 상관관계</h3>
      <span class="text-blue-500 text-lg">🧠</span>
    </div>

    <div v-if="!insights.length" class="text-sm text-gray-500">부작용 상관관계 데이터가 없습니다.</div>
    <div v-else class="space-y-3">
      <div
        v-for="(item, idx) in insights"
        :key="idx"
        class="p-4 rounded-lg border-l-4"
        :class="item.level === 'high' ? 'bg-red-50 border-red-400' : 'bg-yellow-50 border-yellow-400'"
      >
        <div class="flex items-center space-x-2">
          <span>{{ item.level === 'high' ? '⚠️' : 'ℹ️' }}</span>
          <span
            class="font-medium"
            :class="item.level === 'high' ? 'text-red-700' : 'text-yellow-700'"
          >
            {{ item.title }}
          </span>
        </div>
        <p class="text-sm mt-1" :class="item.level === 'high' ? 'text-red-600' : 'text-yellow-600'">
          {{ item.detail }}
        </p>
      </div>
    </div>

    <button class="w-full mt-2 px-4 py-2 text-blue-600 border border-blue-200 rounded-lg hover:bg-blue-50 transition-colors">
      상세 리포트 보기
    </button>
  </div>
</template>

<script setup>
defineProps({
  insights: {
    type: Array,
    default: () => [],
  },
});
</script>
