<template>
  <div id="weight-medication-chart" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6">
    <div class="flex items-center justify-between mb-4">
      <div>
        <h3 class="text-lg font-bold text-gray-800">체중 & 투약량 추이</h3>
        <p class="text-sm text-gray-500">최근 데이터를 요약해 보여줍니다.</p>
      </div>
      <span class="text-blue-500 text-lg">📈</span>
    </div>

    <div v-if="!hasData" class="text-sm text-gray-500">체중 데이터가 없습니다.</div>
    <div v-else class="space-y-4">
      <div class="flex items-center justify-between text-sm text-gray-600">
        <span>체중 (kg)</span>
        <span class="font-semibold text-blue-600">{{ trendLabel }}</span>
      </div>
      <div class="space-y-3">
        <div
          v-for="point in normalizedData"
          :key="point.week"
          class="flex items-center space-x-3"
        >
          <div class="w-12 text-xs text-gray-500">W{{ point.week }}</div>
          <div class="flex-1 h-2 bg-gray-100 rounded-full overflow-hidden">
            <div
              class="h-full bg-gradient-to-r from-blue-300 to-blue-600"
              :style="{ width: `${point.weightPct}%` }"
            ></div>
          </div>
          <div class="w-16 text-sm font-semibold text-gray-800 text-right">{{ point.weight }}kg</div>
        </div>
      </div>

      <div class="pt-4 border-t border-gray-100" v-if="showDose">
        <div class="flex items-center justify-between text-sm text-gray-600">
          <span>투약량 (mg)</span>
          <span class="font-semibold text-blue-600">유지 {{ lastDose }}mg</span>
        </div>
        <div class="grid grid-cols-6 gap-2 mt-3">
          <div
            v-for="point in normalizedData"
            :key="`dose-${point.week}`"
            class="h-8 rounded-md flex items-center justify-center text-xs font-semibold"
            :class="point.dose > 0.5 ? 'bg-blue-500 text-white' : 'bg-blue-100 text-blue-800'"
          >
            {{ point.dose }}mg
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  weights: {
    type: Array,
    default: () => [],
  },
  doses: {
    type: Array,
    default: () => [],
  },
});

const hasData = computed(() => props.weights && props.weights.length > 0);
const maxWeight = computed(() => (hasData.value ? Math.max(...props.weights) : 0));
const normalizedData = computed(() =>
  (props.weights || []).map((w, idx) => ({
    week: idx + 1,
    weight: w,
    weightPct: maxWeight.value ? Math.round((w / maxWeight.value) * 100) : 0,
    dose: props.doses[idx] ?? 0.5,
  })),
);

const trendLabel = computed(() => {
  if (!hasData.value) return '';
  const diff = props.weights[0] - props.weights[props.weights.length - 1];
  const symbol = diff > 0 ? '▼' : diff < 0 ? '▲' : '—';
  return `${symbol} ${diff.toFixed(1)} kg`;
});

const showDose = computed(() => props.doses && props.doses.length > 0);
const lastDose = computed(() => (showDose.value ? props.doses[props.doses.length - 1] : 0.5));
</script>
