import apiClient from './index.js';

export const getAiRecommendation = async (payload) => {
  const { data } = await apiClient.post('/dashboard/ai-recommendation', payload);
  return data?.data || {};
};

export const createMealRecord = async (payload) => {
  const { data } = await apiClient.post('/dashboard/meal-record', payload);
  return data?.data || data?.message || '식단 기록이 저장되었습니다.';
};

export const getDashboardStatistics = async () => {
  const { data } = await apiClient.get('/dashboard/statistics');
  return data?.data || {};
};
