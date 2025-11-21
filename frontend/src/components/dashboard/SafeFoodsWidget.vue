<template>
  <div id="safe-foods" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6 space-y-4">
    <div class="flex items-center justify-between">
      <h3 class="text-lg font-bold text-gray-800">[AI] 세이프 / 기피 음식</h3>
      <span class="text-blue-500 text-lg">🛡️</span>
    </div>

    <div class="space-y-3">
      <div
        v-for="safe in safeFoods"
        :key="safe.name"
        class="flex items-center space-x-3 p-3 bg-green-50 rounded-lg border border-green-100"
      >
        <span>✅</span>
        <div>
          <p class="font-semibold text-green-800">{{ safe.name }}</p>
          <p class="text-xs text-green-600">{{ safe.note }}</p>
        </div>
      </div>
    </div>

    <div class="pt-2 border-t border-gray-100">
      <h4 class="font-semibold text-gray-700 mb-2">기피 음식</h4>
      <div class="space-y-2">
        <div
          v-for="avoid in avoidFoods"
          :key="avoid"
          class="flex items-center space-x-3 p-3 bg-red-50 rounded-lg border border-red-100"
        >
          <span>❌</span>
          <span class="text-red-700 font-medium">{{ avoid }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const safeFoods = [
  { name: '고구마 + 닭가슴살', note: '부작용 발생률 5% 이하' },
  { name: '두부 스테이크', note: '포만감 유지 92%' },
  { name: '현미밥 + 나물', note: '소화 부담 낮음' },
];

const avoidFoods = ['기름진 튀김류', '매운 음식'];
</script>
