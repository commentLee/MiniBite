<template>
  <div id="weight-medication-chart" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6">
    <div class="flex items-center justify-between mb-4">
      <div>
        <h3 class="text-lg font-bold text-gray-800">체중 & 투약량 추이</h3>
        <p class="text-sm text-gray-500">최근 6주 데이터를 요약해 보여줍니다.</p>
      </div>
      <span class="text-blue-500 text-lg">📈</span>
    </div>

    <div class="space-y-4">
      <div class="flex items-center justify-between text-sm text-gray-600">
        <span>체중 (kg)</span>
        <span class="font-semibold text-blue-600">▼ -3.3 kg</span>
      </div>
      <div class="space-y-3">
        <div
          v-for="point in weightData"
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

      <div class="pt-4 border-t border-gray-100">
        <div class="flex items-center justify-between text-sm text-gray-600">
          <span>투약량 (mg)</span>
          <span class="font-semibold text-blue-600">유지 1.0mg</span>
        </div>
        <div class="grid grid-cols-6 gap-2 mt-3">
          <div
            v-for="point in weightData"
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
const weightData = [
  { week: 1, weight: 72.5, weightPct: 90, dose: 0.25 },
  { week: 2, weight: 71.8, weightPct: 88, dose: 0.5 },
  { week: 3, weight: 71.2, weightPct: 86, dose: 0.5 },
  { week: 4, weight: 70.5, weightPct: 84, dose: 0.75 },
  { week: 5, weight: 69.8, weightPct: 82, dose: 1.0 },
  { week: 6, weight: 69.2, weightPct: 80, dose: 1.0 },
];
</script>
