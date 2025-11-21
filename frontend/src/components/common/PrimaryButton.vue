<template>
  <button
    :type="type"
    :disabled="disabled"
    class="inline-flex items-center justify-center px-4 py-3 rounded-lg font-semibold transition-all duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2"
    :class="buttonClasses"
  >
    <slot />
  </button>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  type: {
    type: String,
    default: 'button',
  },
  disabled: {
    type: Boolean,
    default: false,
  },
  variant: {
    type: String,
    default: 'primary', // primary | ghost
  },
});

const buttonClasses = computed(() => {
  if (props.variant === 'ghost') {
    return props.disabled
      ? 'bg-gray-100 text-gray-400 cursor-not-allowed'
      : 'bg-white text-gray-700 border border-gray-200 hover:bg-gray-50';
  }
  return props.disabled
    ? 'bg-gray-300 text-white cursor-not-allowed'
    : 'bg-blue-500 text-white hover:bg-blue-600';
});
</script>
