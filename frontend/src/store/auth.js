import { defineStore } from 'pinia';
import { login as loginApi, signup as signupApi } from '../services/authService';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    accessToken: '',
    refreshToken: '',
    userId: '',
  }),
  actions: {
    async login(credentials) {
      const data = await loginApi(credentials);
      this.accessToken = data?.accessToken || '';
      this.refreshToken = data?.refreshToken || '';
      this.userId = credentials.id;
      return data;
    },
    async signup(payload) {
      const data = await signupApi(payload);
      this.userId = data?.userId || payload.id;
      return data;
    },
    logout() {
      this.accessToken = '';
      this.refreshToken = '';
      this.userId = '';
    },
  },
});
