<template>
  <div v-if="open" class="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
    <div class="bg-white rounded-2xl shadow-2xl w-full max-w-lg p-6">
      <div class="flex items-start justify-between mb-4">
        <h3 class="text-lg font-bold text-gray-800">{{ title }}</h3>
        <button class="text-gray-400 hover:text-gray-600" @click="$emit('close')">✕</button>
      </div>
      <div class="text-gray-700">
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  open: {
    type: Boolean,
    default: false,
  },
  title: {
    type: String,
    default: '',
  },
});

defineEmits(['close']);
</script>
