<template>
  <div id="nutrition-balance" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6 space-y-4">
    <div class="flex items-center justify-between">
      <h3 class="text-lg font-bold text-gray-800">[AI] 영양 밸런스</h3>
      <span class="text-blue-500 text-lg">🥗</span>
    </div>

    <div class="space-y-3">
      <div v-for="item in nutrition" :key="item.label" class="space-y-1">
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
      단백질/식이섬유는 충분, 비타민D가 부족합니다. 섭취 빈도를 조절해 주세요.
    </div>
  </div>
</template>

<script setup>
const nutrition = [
  { label: '단백질', status: '충분', statusColor: 'text-green-600', barColor: 'bg-green-400', ratio: 92 },
  { label: '탄수화물', status: '적정', statusColor: 'text-yellow-600', barColor: 'bg-yellow-400', ratio: 65 },
  { label: '지방', status: '과다', statusColor: 'text-red-600', barColor: 'bg-red-400', ratio: 78 },
  { label: '비타민 D', status: '부족', statusColor: 'text-red-600', barColor: 'bg-red-400', ratio: 35 },
  { label: '식이섬유', status: '충분', statusColor: 'text-green-600', barColor: 'bg-green-400', ratio: 88 },
];
</script>
