<template>
  <div id="nutrition-balance" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6 space-y-4">
    <div class="flex items-center justify-between">
      <h3 class="text-lg font-bold text-gray-800">[AI] 영양 밸런스</h3>
      <span class="text-blue-500 text-lg">🥗</span>
    </div>

    <div class="space-y-3">
      <div v-for="item in nutritionComputed" :key="item.label" class="space-y-1">
        <div class="flex items-center justify-between text-sm text-gray-700">
          <span>{{ item.label }}</span>
          <span class="font-semibold" :class="item.statusColor">{{ item.status }}</span>
        </div>
        <div class="h-2 bg-gray-100 rounded-full overflow-hidden">
          <div
            class="h-full rounded-full"
            :class="item.barColor"
            :style="{ width: `${item.ratio}%` }"
          ></div>
        </div>
      </div>
    </div>

    <div class="text-sm text-gray-600 pt-2 border-t border-gray-100">
      {{ insightText }}
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  averageSatisfaction: {
    type: Number,
    default: 0,
  },
});

const nutritionComputed = computed(() => [
  { label: '단백질', status: '충분', statusColor: 'text-green-600', barColor: 'bg-green-400', ratio: 90 },
  { label: '탄수화물', status: '적정', statusColor: 'text-yellow-600', barColor: 'bg-yellow-400', ratio: 65 },
  { label: '식이섬유', status: '충분', statusColor: 'text-green-600', barColor: 'bg-green-400', ratio: 85 },
]);

const insightText = computed(() =>
  props.averageSatisfaction > 0
    ? `최근 평균 만족도 ${props.averageSatisfaction.toFixed(1)}점입니다. 부족한 영양소를 점검해 주세요.`
    : '영양 밸런스 데이터가 충분하지 않습니다.'
);
</script>
