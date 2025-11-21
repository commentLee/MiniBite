package com.ssafy.minibite.user.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.minibite.user.dto.LoginRequest;
import com.ssafy.minibite.user.dto.LoginResponse;
import com.ssafy.minibite.user.dto.SignupResponse;
import com.ssafy.minibite.user.dto.UserSignupRequest;
import com.ssafy.minibite.user.dto.UserSignupRequest.Preferences;
import com.ssafy.minibite.user.service.AuthService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    @DisplayName("/auth/signup 회원가입 API는 201과 사용자 ID를 반환한다")
    void signup_success() throws Exception {
        SignupResponse signupResponse = SignupResponse.builder()
                .userId("junwoo123")
                .build();

        when(authService.signup(any(UserSignupRequest.class))).thenReturn(signupResponse);

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

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("회원가입 완료")))
                .andExpect(jsonPath("$.data.userId", is("junwoo123")));
    }

    @Test
    @DisplayName("/auth/login 로그인 API는 200과 토큰을 반환한다")
    void login_success() throws Exception {
        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken("access-token")
                .refreshToken("refresh-token")
                .build();

        when(authService.login(any(LoginRequest.class))).thenReturn(loginResponse);

        LoginRequest request = new LoginRequest("junwoo123", "password");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.data.accessToken", is("access-token")))
                .andExpect(jsonPath("$.data.refreshToken", is("refresh-token")));
    }
}

