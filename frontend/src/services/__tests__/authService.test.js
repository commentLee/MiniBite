import { describe, expect, it, vi } from 'vitest';
import apiClient from '../index.js';
import { login, signup } from '../authService.js';

vi.mock('../index.js', () => ({
  default: {
    post: vi.fn(),
  },
}));

describe('authService', () => {
  it('로그인 API를 호출하고 토큰을 반환한다', async () => {
    const tokens = { accessToken: 'access', refreshToken: 'refresh' };
    apiClient.post.mockResolvedValue({ data: { data: tokens } });

    const result = await login({ id: 'user', password: 'pw' });

    expect(apiClient.post).toHaveBeenCalledWith('/auth/login', { id: 'user', password: 'pw' });
    expect(result).toEqual(tokens);
  });

  it('회원가입 API를 호출한다', async () => {
    const response = { userId: 'new-user' };
    apiClient.post.mockResolvedValue({ data: { data: response } });

    const result = await signup({ id: 'new-user', password: 'pw' });

    expect(apiClient.post).toHaveBeenCalledWith('/auth/signup', { id: 'new-user', password: 'pw' });
    expect(result).toEqual(response);
  });
});
