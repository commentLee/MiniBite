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
    <MealRecordModal :open="showRecordModal" @close="showRecordModal = false" @saved="onRecordSaved" />
  </div>
</template>

<script setup>
import AiRecommendationWidget from '../components/dashboard/AiRecommendationWidget.vue';
import WeightChart from '../components/dashboard/WeightChart.vue';
import SideEffectsWidget from '../components/dashboard/SideEffectsWidget.vue';
import NutritionChart from '../components/dashboard/NutritionChart.vue';
import SafeFoodsWidget from '../components/dashboard/SafeFoodsWidget.vue';
import FabButton from '../components/common/FabButton.vue';
import MealRecordModal from '../components/dashboard/MealRecordModal.vue';
import { ref } from 'vue';

const handleFabClick = () => {
  showRecordModal.value = true;
};

const onRecordSaved = () => {
  // TODO: 저장 후 통계/위젯 갱신 연동
  // 현재는 알림 없이 모달만 닫습니다.
};

const showRecordModal = ref(false);
</script>
