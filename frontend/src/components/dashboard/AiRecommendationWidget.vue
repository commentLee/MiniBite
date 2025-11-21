<template>
  <section id="ai-recommendation-widget" class="bg-white rounded-2xl shadow-lg border border-gray-200 p-8">
    <div class="flex items-center space-x-3 mb-6">
      <div class="w-12 h-12 bg-blue-500 rounded-xl flex items-center justify-center text-white text-xl shadow-md">
        ✨
      </div>
      <div>
        <h2 class="text-xl font-bold text-gray-800">오늘의 가치 있는 한 끼 추천</h2>
        <p class="text-gray-600 text-sm">현재 상태를 입력하면 AI가 맞춤 메뉴를 제안합니다.</p>
      </div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
      <div class="space-y-3">
        <label class="block text-sm font-medium text-gray-700">현재 기분</label>
        <select
          v-model="form.mood"
          class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
        >
          <option value="">선택해주세요</option>
          <option>매우 좋음</option>
          <option>좋음</option>
          <option>보통</option>
          <option>나쁨</option>
          <option>매우 나쁨</option>
        </select>
      </div>

      <div class="space-y-3">
        <label class="block text-sm font-medium text-gray-700">식욕 정도</label>
        <div class="px-4 py-3 border border-gray-300 rounded-lg">
          <input
            v-model.number="form.appetite"
            type="range"
            class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer"
            min="0"
            max="100"
          />
          <div class="flex justify-between text-xs text-gray-500 mt-2">
            <span>적음</span>
            <span>보통</span>
            <span>많음</span>
          </div>
        </div>
      </div>

      <div class="space-y-3">
        <label class="block text-sm font-medium text-gray-700">컨디션</label>
        <select
          v-model="form.condition"
          class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
        >
          <option value="">선택해주세요</option>
          <option>매우 좋음</option>
          <option>좋음</option>
          <option>보통</option>
          <option>피곤함</option>
          <option>몸살 기운</option>
        </select>
      </div>
    </div>

    <div class="mt-6 text-center">
      <button
        class="px-8 py-3 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 transition-colors disabled:bg-gray-300 disabled:cursor-not-allowed"
        :disabled="!isValid || loading"
        @click="handleRecommend"
      >
        <span v-if="loading">추천 중...</span>
        <span v-else>AI 추천 받기</span>
      </button>
    </div>

    <div
      v-if="result"
      class="mt-8 grid grid-cols-1 lg:grid-cols-3 gap-6 bg-blue-50 border border-blue-100 rounded-xl p-6"
    >
      <div class="lg:col-span-2 space-y-2">
        <p class="text-sm font-semibold text-blue-600">추천 메뉴</p>
        <h3 class="text-xl font-bold text-gray-800">{{ result.menu }}</h3>
        <p class="text-gray-700 leading-relaxed">{{ result.reason }}</p>
      </div>
      <div>
        <p class="text-sm font-semibold text-blue-600 mb-2">대체 옵션</p>
        <ul class="space-y-2">
          <li
            v-for="alt in result.alternatives"
            :key="alt"
            class="px-4 py-2 bg-white rounded-lg border border-blue-100 text-gray-800 shadow-sm"
          >
            {{ alt }}
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script setup>
import { computed, reactive, ref } from 'vue';

const loading = ref(false);
const form = reactive({
  mood: '',
  appetite: 50,
  condition: '',
});

const result = ref(null);
const isValid = computed(() => form.mood && form.condition);

const handleRecommend = async () => {
  if (!isValid.value) return;
  loading.value = true;
  // TODO: API 연동 (/dashboard/ai-recommendation). 현재는 샘플 응답.
  setTimeout(() => {
    result.value = {
      menu: '닭가슴살 샐러드 + 고구마',
      alternatives: ['두부 스테이크', '연어 구이', '그릭요거트 볼'],
      reason:
        '저번 기록에서 고지방 식단 시 메스꺼움이 증가했어요. 단백질·식이섬유가 높고 소화가 편한 메뉴를 추천합니다.',
    };
    loading.value = false;
  }, 500);
};
</script>
