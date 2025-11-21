<template>
  <div id="dashboard-content" class="p-8 space-y-8">
    <AiRecommendationWidget />

    <section id="analytics-dashboard" class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <WeightChart />
      <SideEffectsWidget />
      <NutritionChart />
      <SafeFoodsWidget />
    </section>

    <FabButton @click="handleFabClick" />
  </div>
</template>

<script setup>
import AiRecommendationWidget from '../components/dashboard/AiRecommendationWidget.vue';
import WeightChart from '../components/dashboard/WeightChart.vue';
import SideEffectsWidget from '../components/dashboard/SideEffectsWidget.vue';
import NutritionChart from '../components/dashboard/NutritionChart.vue';
import SafeFoodsWidget from '../components/dashboard/SafeFoodsWidget.vue';
import FabButton from '../components/common/FabButton.vue';

const handleFabClick = () => {
  // TODO: 식단/건강 상태 기록 모달 연결
  alert('기록 모달을 연결해 주세요.');
};
</script>
