<template>
  <div class="min-h-screen flex bg-white">
    <section class="hidden lg:flex flex-1 bg-gradient-to-br from-blue-50 to-blue-100 items-center justify-center p-16">
      <div class="max-w-lg text-center space-y-6">
        <div class="h-80 overflow-hidden rounded-2xl shadow-xl">
          <img
            class="w-full h-full object-cover"
            src="https://storage.googleapis.com/uxpilot-auth.appspot.com/84441fb7af-deeb1d31cf2b931cc6a4.png"
            alt="personalized healthcare illustration"
          />
        </div>
        <h1 class="text-4xl font-bold text-gray-800 leading-tight">
          AI로 시작하는<br />
          가장 가치 있는 한 끼
        </h1>
        <p class="text-lg text-gray-600 leading-relaxed">
          개인별 맞춤형 AI 분석으로 나에게 꼭 맞는 식단을 추천받고,<br />
          하루 한 끼를 더 가치 있게 채워보세요.
        </p>
        <div class="grid grid-cols-3 gap-4 text-sm text-gray-600">
          <div class="flex items-center justify-center space-x-2 bg-white/60 rounded-xl p-3 shadow-sm">
            <span class="text-blue-500">🧠</span>
            <span>AI 추천</span>
          </div>
          <div class="flex items-center justify-center space-x-2 bg-white/60 rounded-xl p-3 shadow-sm">
            <span class="text-blue-500">🥗</span>
            <span>세이프 푸드</span>
          </div>
          <div class="flex items-center justify-center space-x-2 bg-white/60 rounded-xl p-3 shadow-sm">
            <span class="text-blue-500">📈</span>
            <span>기록/통계</span>
          </div>
        </div>
      </div>
    </section>

    <section class="flex-1 flex items-center justify-center p-8">
      <div class="w-full max-w-md space-y-8">
        <div class="text-center space-y-3">
          <div class="inline-flex items-center justify-center w-16 h-16 bg-blue-500 rounded-2xl text-white text-2xl font-bold">
            MB
          </div>
          <h2 class="text-3xl font-bold text-gray-800">MiniBite</h2>
          <p class="text-gray-600">가치 있는 한 끼를 위한 로그인</p>
        </div>

        <form class="space-y-6" @submit.prevent="handleSubmit">
          <div class="space-y-2">
            <label class="block text-sm font-semibold text-gray-700" for="user-id">아이디</label>
            <input
              id="user-id"
              v-model.trim="form.id"
              type="text"
              class="w-full px-5 py-4 border-2 border-gray-200 rounded-lg focus:border-blue-500 focus:ring-2 focus:ring-blue-200 transition-colors"
              placeholder="아이디를 입력하세요"
              required
            />
          </div>

          <div class="space-y-2">
            <label class="block text-sm font-semibold text-gray-700" for="password">비밀번호</label>
            <input
              id="password"
              v-model.trim="form.password"
              type="password"
              class="w-full px-5 py-4 border-2 border-gray-200 rounded-lg focus:border-blue-500 focus:ring-2 focus:ring-blue-200 transition-colors"
              placeholder="비밀번호를 입력하세요"
              required
              minlength="4"
            />
          </div>

          <div v-if="errorMessage" class="flex items-center space-x-2 text-sm text-red-600 bg-red-50 border border-red-100 rounded-lg px-4 py-3">
            <span>⚠️</span>
            <span>{{ errorMessage }}</span>
          </div>

          <button
            type="submit"
            :disabled="!isValid || loading"
            class="w-full px-6 py-4 bg-blue-500 text-white font-semibold rounded-lg shadow-md transition-all duration-200 disabled:bg-gray-300 disabled:cursor-not-allowed hover:bg-blue-600"
          >
            <span v-if="loading">로그인 중...</span>
            <span v-else>로그인</span>
          </button>
        </form>

        <div class="space-y-4">
          <div class="flex items-center">
            <div class="flex-1 border-t border-gray-200"></div>
            <span class="px-4 text-sm text-gray-500">또는</span>
            <div class="flex-1 border-t border-gray-200"></div>
          </div>

          <div class="grid grid-cols-1 gap-3">
            <button class="w-full flex items-center justify-center px-6 py-4 bg-yellow-400 text-gray-900 font-semibold rounded-lg hover:bg-yellow-500 transition-colors">
              <span>카카오로 시작하기</span>
            </button>
            <button class="w-full flex items-center justify-center px-6 py-4 bg-green-500 text-white font-semibold rounded-lg hover:bg-green-600 transition-colors">
              <span>네이버로 시작하기</span>
            </button>
            <button class="w-full flex items-center justify-center px-6 py-4 bg-white text-gray-800 font-semibold rounded-lg border border-gray-200 hover:bg-gray-50 transition-colors">
              <span>이메일 회원가입</span>
            </button>
          </div>
        </div>

        <p class="text-xs text-center text-gray-500 leading-relaxed">
          계속 진행하면 <a class="text-blue-600 hover:underline">이용약관</a> 및
          <a class="text-blue-600 hover:underline">개인정보 처리방침</a>에 동의한 것으로 간주합니다.
        </p>
      </div>
    </section>
  </div>
</template>

<script setup>
import { computed, reactive, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../store/auth';

const router = useRouter();
const authStore = useAuthStore();

const form = reactive({
  id: '',
  password: '',
});

const loading = ref(false);
const errorMessage = ref('');

const isValid = computed(() => form.id.trim().length > 0 && form.password.trim().length >= 4);

watch(
  () => ({ ...form }),
  () => {
    errorMessage.value = '';
  },
);

const handleSubmit = async () => {
  if (!isValid.value || loading.value) {
    return;
  }

  loading.value = true;
  errorMessage.value = '';
  try {
    await authStore.login({ id: form.id, password: form.password });
    router.push('/dashboard');
  } catch (error) {
    errorMessage.value = error?.message || '로그인에 실패했습니다. 다시 시도해주세요.';
  } finally {
    loading.value = false;
  }
};
</script>
