<template>
  <button
    class="fixed bottom-8 right-8 shadow-2xl rounded-full bg-blue-500 text-white w-16 h-16 flex items-center justify-center text-2xl hover:bg-blue-600 transition-transform hover:scale-105 focus:outline-none focus:ring-4 focus:ring-blue-200"
    @click="$emit('click')"
  >
    ＋
  </button>
</template>

<script setup>
defineEmits(['click']);
</script>
