import { describe, expect, it, vi } from 'vitest';
import apiClient from '../index.js';
import { getAiRecommendation, getDashboardStatistics } from '../dashboardService.js';

vi.mock('../index.js', () => ({
  default: {
    post: vi.fn(),
    get: vi.fn(),
  },
}));

describe('dashboardService', () => {
  it('AI 추천 API를 호출하고 응답을 반환한다', async () => {
    const payload = { mood: '좋음', appetite: 50, condition: '보통' };
    const resp = { menu: '닭가슴살', alternatives: ['두부'], reason: '단백질 필요' };
    apiClient.post.mockResolvedValue({ data: { data: resp } });

    const result = await getAiRecommendation(payload);

    expect(apiClient.post).toHaveBeenCalledWith('/dashboard/ai-recommendation', payload);
    expect(result).toEqual(resp);
  });

  it('대시보드 통계 API를 호출하고 응답을 반환한다', async () => {
    const stats = { weightTrend: [70, 69.5], favoriteFoods: [], averageSatisfaction: 4.2 };
    apiClient.get.mockResolvedValue({ data: { data: stats } });

    const result = await getDashboardStatistics();

    expect(apiClient.get).toHaveBeenCalledWith('/dashboard/statistics');
    expect(result).toEqual(stats);
  });
});
