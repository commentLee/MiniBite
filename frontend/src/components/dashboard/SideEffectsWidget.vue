<template>
  <div id="side-effects-correlation" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-6 space-y-4">
    <div class="flex items-center justify-between mb-2">
      <h3 class="text-lg font-bold text-gray-800">[AI] 부작용 상관관계</h3>
      <span class="text-blue-500 text-lg">🧠</span>
    </div>

    <div class="space-y-3">
      <div class="p-4 bg-red-50 rounded-lg border-l-4 border-red-400">
        <div class="flex items-center space-x-2">
          <span>⚠️</span>
          <span class="font-medium text-red-700">고지방 식단 시 메스꺼움 70%↑</span>
        </div>
        <p class="text-sm text-red-600 mt-1">최근 2주 데이터 기준 AI 분석</p>
      </div>

      <div class="p-4 bg-yellow-50 rounded-lg border-l-4 border-yellow-400">
        <div class="flex items-center space-x-2">
          <span>ℹ️</span>
          <span class="font-medium text-yellow-700">식사 속도 빠름 → 더부룩함 45%↑</span>
        </div>
        <p class="text-sm text-yellow-600 mt-1">최근 1주 기준</p>
      </div>
    </div>

    <button class="w-full mt-2 px-4 py-2 text-blue-600 border border-blue-200 rounded-lg hover:bg-blue-50 transition-colors">
      상세 리포트 보기
    </button>
  </div>
</template>
