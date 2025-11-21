<template>
  <Modal :open="open" title="식단 기록" @close="$emit('close')">
    <form class="space-y-4" @submit.prevent="onSubmit">
      <div class="space-y-2">
        <label class="block text-sm font-semibold text-gray-700" for="meal-time">식사 시간</label>
        <input
          id="meal-time"
          v-model="form.mealTime"
          type="datetime-local"
          class="w-full px-4 py-3 border-2 border-gray-200 rounded-lg focus:border-blue-500 focus:ring-2 focus:ring-blue-200"
          required
        />
      </div>

      <div class="space-y-2">
        <label class="block text-sm font-semibold text-gray-700" for="satisfaction">만족도 (1-5)</label>
        <input
          id="satisfaction"
          v-model.number="form.satisfaction"
          type="number"
          min="1"
          max="5"
          class="w-full px-4 py-3 border-2 border-gray-200 rounded-lg focus:border-blue-500 focus:ring-2 focus:ring-blue-200"
          required
        />
      </div>

      <div class="space-y-2">
        <p class="text-sm font-semibold text-gray-700">부작용</p>
        <div class="flex items-center space-x-3">
          <label class="inline-flex items-center space-x-2 text-sm text-gray-700">
            <input v-model="form.nausea" type="checkbox" />
            <span>메스꺼움</span>
          </label>
          <label class="inline-flex items-center space-x-2 text-sm text-gray-700">
            <input v-model="form.bloating" type="checkbox" />
            <span>더부룩함</span>
          </label>
        </div>
      </div>

      <div class="space-y-2">
        <label class="block text-sm font-semibold text-gray-700" for="note">메모 (선택)</label>
        <textarea
          id="note"
          v-model="form.note"
          rows="3"
          class="w-full px-4 py-3 border-2 border-gray-200 rounded-lg focus:border-blue-500 focus:ring-2 focus:ring-blue-200"
          placeholder="맛, 포만감, 특이사항 등을 남겨주세요."
        />
      </div>

      <div v-if="errorMessage" class="text-sm text-red-600 bg-red-50 border border-red-100 rounded-lg px-3 py-2">
        {{ errorMessage }}
      </div>

      <div class="flex justify-end space-x-2 pt-2">
        <PrimaryButton variant="ghost" type="button" @click="$emit('close')">취소</PrimaryButton>
        <PrimaryButton type="submit" :disabled="loading">
          <span v-if="loading">저장 중...</span>
          <span v-else>저장</span>
        </PrimaryButton>
      </div>
    </form>
  </Modal>
</template>

<script setup>
import { reactive, ref, watch } from 'vue';
import { createMealRecord } from '../../services/dashboardService';
import PrimaryButton from '../common/PrimaryButton.vue';
import Modal from '../common/Modal.vue';

const props = defineProps({
  open: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(['close', 'saved']);

const baseForm = () => ({
  mealTime: '',
  satisfaction: 3,
  nausea: false,
  bloating: false,
  note: '',
});

const form = reactive(baseForm());
const loading = ref(false);
const errorMessage = ref('');

watch(
  () => props.open,
  (isOpen) => {
    if (isOpen) {
      Object.assign(form, baseForm());
      errorMessage.value = '';
    }
  },
);

const onSubmit = async () => {
  if (loading.value) return;
  loading.value = true;
  errorMessage.value = '';
  try {
    await createMealRecord({
      mealTime: form.mealTime,
      satisfaction: form.satisfaction,
      sideEffects: ['nausea', 'bloating'].filter((key) => form[key]),
      note: form.note,
    });
    emit('saved');
    emit('close');
  } catch (error) {
    errorMessage.value = error?.message || '식단 기록 저장에 실패했습니다.';
  } finally {
    loading.value = false;
  }
};
</script>
