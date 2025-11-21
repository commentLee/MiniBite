<template>
  <div id="safe-foods" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6 space-y-4">
    <div class="flex items-center justify-between">
      <h3 class="text-lg font-bold text-gray-800">[AI] 세이프 / 기피 음식</h3>
      <span class="text-blue-500 text-lg">🛡️</span>
    </div>

    <div v-if="!safeFoodsComputed.length" class="text-sm text-gray-500">추천된 세이프 푸드가 없습니다.</div>
    <div v-else class="space-y-3">
      <div
        v-for="safe in safeFoodsComputed"
        :key="safe"
        class="flex items-center space-x-3 p-3 bg-green-50 rounded-lg border border-green-100"
      >
        <span>✅</span>
        <div>
          <p class="font-semibold text-green-800">{{ safe }}</p>
          <p class="text-xs text-green-600">최근 기록 기준 안정적인 식단</p>
        </div>
      </div>
    </div>

    <div class="pt-2 border-t border-gray-100">
      <h4 class="font-semibold text-gray-700 mb-2">기피 음식</h4>
      <div class="space-y-2">
        <div
          v-for="avoid in avoidFoodsComputed"
          :key="avoid"
          class="flex items-center space-x-3 p-3 bg-red-50 rounded-lg border border-red-100"
        >
          <span>❌</span>
          <span class="text-red-700 font-medium">{{ avoid }}</span>
        </div>
        <p v-if="!avoidFoodsComputed.length" class="text-sm text-gray-500">기피 음식 데이터가 없습니다.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  safeFoods: {
    type: Array,
    default: () => [],
  },
  avoidFoods: {
    type: Array,
    default: () => [],
  },
});

const safeFoodsComputed = computed(() => props.safeFoods || []);
const avoidFoodsComputed = computed(() => props.avoidFoods || ['기름진 튀김류', '매운 음식']);
</script>
