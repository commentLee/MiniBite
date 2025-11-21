import apiClient from './index.js';

export const getAiRecommendation = async (payload) => {
  const { data } = await apiClient.post('/dashboard/ai-recommendation', payload);
  return data?.data || {};
};
