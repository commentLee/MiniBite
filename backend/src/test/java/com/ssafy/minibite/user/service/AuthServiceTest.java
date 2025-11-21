package com.ssafy.minibite.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ssafy.minibite.global.exception.BusinessException;
import com.ssafy.minibite.global.exception.ErrorCode;
import com.ssafy.minibite.user.domain.Preference;
import com.ssafy.minibite.user.domain.User;
import com.ssafy.minibite.user.dto.LoginRequest;
import com.ssafy.minibite.user.dto.LoginResponse;
import com.ssafy.minibite.user.dto.SignupResponse;
import com.ssafy.minibite.user.dto.UserSignupRequest;
import com.ssafy.minibite.user.dto.UserSignupRequest.Preferences;
import com.ssafy.minibite.user.repository.PreferenceMapper;
import com.ssafy.minibite.user.repository.UserMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private PreferenceMapper preferenceMapper;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("회원가입 성공 시 사용자 ID를 반환한다")
    void signup_success() {
        Preferences preferences = new Preferences(
                List.of("우유"),
                List.of("고수"),
                List.of("부드러운"),
                List.of("짭짤한")
        );
        UserSignupRequest request = new UserSignupRequest(
                "junwoo123",
                "password",
                "작은포만감",
                "010-0000-0000",
                "1996-05-12",
                "male",
                175,
                67.0f,
                preferences
        );

        when(userMapper.existsByUserId("junwoo123")).thenReturn(false);

        SignupResponse response = authService.signup(request);

        assertThat(response.getUserId()).isEqualTo("junwoo123");
        verify(userMapper).insertUser(any(User.class));
        verify(preferenceMapper).insertPreference(any(Preference.class));
    }

    @Test
    @DisplayName("이미 존재하는 ID로 회원가입 시 예외가 발생한다")
    void signup_duplicateId() {
        UserSignupRequest request = new UserSignupRequest(
                "dupUser",
                "password",
                "닉네임",
                "010-0000-0000",
                "1996-05-12",
                "female",
                160,
                50.0f,
                null
        );

        when(userMapper.existsByUserId("dupUser")).thenReturn(true);

        assertThatThrownBy(() -> authService.signup(request))
                .isInstanceOf(BusinessException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.DUPLICATE_USER_ID);
    }

    @Test
    @DisplayName("로그인 성공 시 액세스 토큰과 리프레시 토큰을 반환한다")
    void login_success() {
        User user = User.builder()
                .userId("testUser")
                .password("password")
                .build();

        when(userMapper.findByUserId("testUser")).thenReturn(user);

        LoginRequest request = new LoginRequest("testUser", "password");

        LoginResponse response = authService.login(request);

        assertThat(response.getAccessToken()).isNotBlank();
        assertThat(response.getRefreshToken()).isNotBlank();
    }

    @Test
    @DisplayName("잘못된 비밀번호로 로그인 시 예외가 발생한다")
    void login_invalidPassword() {
        User user = User.builder()
                .userId("testUser")
                .password("password")
                .build();

        when(userMapper.findByUserId("testUser")).thenReturn(user);

        LoginRequest request = new LoginRequest("testUser", "wrongPassword");

        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(BusinessException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.INVALID_CREDENTIALS);
    }
}

