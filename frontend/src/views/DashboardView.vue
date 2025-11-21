<template>
  <div id="dashboard-content" class="p-8 space-y-8">
    <div v-if="statsError" class="p-4 bg-red-50 border border-red-100 text-red-700 rounded-lg">
      {{ statsError }}
    </div>
    <AiRecommendationWidget />

    <section id="analytics-dashboard" class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <WeightChart :weights="dashboardStats.weightTrend" />
      <SideEffectsWidget :insights="sideEffectInsights" />
      <NutritionChart :average-satisfaction="dashboardStats.averageSatisfaction" />
      <SafeFoodsWidget :safe-foods="dashboardStats.safeFoodList" />
    </section>

    <FabButton @click="handleFabClick" />
    <MealRecordModal :open="showRecordModal" @close="showRecordModal = false" @saved="onRecordSaved" />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import AiRecommendationWidget from '../components/dashboard/AiRecommendationWidget.vue';
import WeightChart from '../components/dashboard/WeightChart.vue';
import SideEffectsWidget from '../components/dashboard/SideEffectsWidget.vue';
import NutritionChart from '../components/dashboard/NutritionChart.vue';
import SafeFoodsWidget from '../components/dashboard/SafeFoodsWidget.vue';
import FabButton from '../components/common/FabButton.vue';
import MealRecordModal from '../components/dashboard/MealRecordModal.vue';
import { getDashboardStatistics } from '../services/dashboardService';

const handleFabClick = () => {
  showRecordModal.value = true;
};

const onRecordSaved = () => {
  // TODO: 저장 후 통계/위젯 갱신 연동
  fetchStats();
};

const showRecordModal = ref(false);
const statsError = ref('');
const dashboardStats = ref({
  weightTrend: [],
  averageSatisfaction: 0,
  safeFoodList: [],
  favoriteFoods: [],
});

const sideEffectInsights = ref([
  {
    title: '고지방 식단 시 메스꺼움 70%↑',
    detail: '최근 2주 데이터 기준',
    level: 'high',
  },
  {
    title: '식사 속도 빠름 → 더부룩함 45%↑',
    detail: '최근 1주 기준',
    level: 'mid',
  },
]);

const fetchStats = async () => {
  statsError.value = '';
  try {
    const data = await getDashboardStatistics();
    dashboardStats.value = {
      weightTrend: data.weightTrend || [],
      averageSatisfaction: data.averageSatisfaction || 0,
      safeFoodList: data.safeFoodList || [],
      favoriteFoods: data.favoriteFoods || [],
    };
  } catch (error) {
    statsError.value = error?.message || '통계 데이터를 불러오지 못했습니다.';
  }
};

onMounted(fetchStats);
</script>
