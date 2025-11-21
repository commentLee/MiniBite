import { describe, expect, it, vi } from 'vitest';
import { mount } from '@vue/test-utils';
import MealRecordModal from '../MealRecordModal.vue';

const createWrapper = (props = {}) =>
  mount(MealRecordModal, {
    props: { open: true, ...props },
  });

const createMealRecordMock = vi.fn();
vi.mock('../../../services/dashboardService', () => ({
  createMealRecord: (...args) => createMealRecordMock(...args),
}));

describe('MealRecordModal', () => {
  it('저장 성공 시 saved 이벤트를 발생시킨다', async () => {
    createMealRecordMock.mockResolvedValue('ok');
    const wrapper = createWrapper();

    await wrapper.get('#meal-time').setValue('2025-11-21T12:00');
    await wrapper.get('#satisfaction').setValue('4');
    await wrapper.find('form').trigger('submit.prevent');

    expect(createMealRecordMock).toHaveBeenCalled();
    expect(wrapper.emitted().saved).toBeTruthy();
  });

  it('API 에러 시 메시지를 표시한다', async () => {
    createMealRecordMock.mockRejectedValue(new Error('잘못된 요청'));
    const wrapper = createWrapper();

    await wrapper.get('#meal-time').setValue('2025-11-21T12:00');
    await wrapper.find('form').trigger('submit.prevent');

    expect(wrapper.text()).toContain('잘못된 요청');
  });
});
