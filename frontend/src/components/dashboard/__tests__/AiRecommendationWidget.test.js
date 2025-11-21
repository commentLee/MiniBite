import { describe, expect, it, vi, beforeEach } from 'vitest';
import { mount, flushPromises } from '@vue/test-utils';
import AiRecommendationWidget from '../AiRecommendationWidget.vue';

const recommendMock = vi.fn();

vi.mock('../../../services/dashboardService', () => ({
  getAiRecommendation: (...args) => recommendMock(...args),
}));

const fillForm = async (wrapper) => {
  await wrapper.get('[data-test="mood-select"]').setValue('좋음');
  await wrapper.get('[data-test="condition-select"]').setValue('보통');
};

describe('AiRecommendationWidget', () => {
  beforeEach(() => {
    recommendMock.mockReset();
  });

  it('성공 응답 시 추천 결과를 표시한다', async () => {
    recommendMock.mockResolvedValue({
      menu: '닭가슴살 샐러드',
      alternatives: ['두부', '연어'],
      reason: '저지방 식단이 적합합니다',
    });
    const wrapper = mount(AiRecommendationWidget);

    await fillForm(wrapper);
    await wrapper.get('[data-test="recommend-btn"]').trigger('click');
    await flushPromises();

    expect(recommendMock).toHaveBeenCalled();
    expect(wrapper.text()).toContain('닭가슴살 샐러드');
    expect(wrapper.text()).toContain('두부');
  });

  it('에러 발생 시 메시지를 노출한다', async () => {
    recommendMock.mockRejectedValue(new Error('필수 파라미터가 누락되었습니다'));
    const wrapper = mount(AiRecommendationWidget);

    await fillForm(wrapper);
    await wrapper.get('[data-test="recommend-btn"]').trigger('click');
    await flushPromises();

    expect(wrapper.text()).toContain('필수 파라미터가 누락되었습니다');
  });
});
