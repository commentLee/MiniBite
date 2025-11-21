import { nextTick } from 'vue';
import { mount } from '@vue/test-utils';
import { describe, expect, it, vi, beforeEach } from 'vitest';
import LoginView from '../LoginView.vue';

const pushMock = vi.fn();
const loginMock = vi.fn();

vi.mock('../../store/auth', () => ({
  useAuthStore: () => ({
    login: loginMock,
  }),
}));

vi.mock('vue-router', () => ({
  useRouter: () => ({ push: pushMock }),
}));

describe('LoginView', () => {
  beforeEach(() => {
    pushMock.mockReset();
    loginMock.mockReset();
  });

  it('입력값이 부족하면 버튼이 비활성화된다', () => {
    const wrapper = mount(LoginView);
    const submitButton = wrapper.get('button[type="submit"]');
    expect(submitButton.attributes('disabled')).toBeDefined();
  });

  it('로그인 성공 시 대시보드로 이동한다', async () => {
    loginMock.mockResolvedValue({ accessToken: 'a', refreshToken: 'r' });
    const wrapper = mount(LoginView);

    await wrapper.get('#user-id').setValue('junwoo123');
    await wrapper.get('#password').setValue('securePW');
    await wrapper.get('form').trigger('submit.prevent');
    await nextTick();

    expect(loginMock).toHaveBeenCalledWith({ id: 'junwoo123', password: 'securePW' });
    expect(pushMock).toHaveBeenCalledWith('/dashboard');
  });

  it('로그인 실패 시 에러 메시지를 표시한다', async () => {
    loginMock.mockRejectedValue(new Error('아이디 또는 비밀번호가 올바르지 않습니다'));
    const wrapper = mount(LoginView);

    await wrapper.get('#user-id').setValue('wrong');
    await wrapper.get('#password').setValue('fail');
    await wrapper.get('form').trigger('submit.prevent');
    await nextTick();

    expect(wrapper.text()).toContain('아이디 또는 비밀번호가 올바르지 않습니다');
  });
});
