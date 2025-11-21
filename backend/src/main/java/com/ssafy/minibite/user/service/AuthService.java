package com.ssafy.minibite.user.service;

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
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PreferenceMapper preferenceMapper;

    @Transactional
    public SignupResponse signup(UserSignupRequest request) {
        if (userMapper.existsByUserId(request.getId())) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER_ID);
        }

        LocalDate birthDate = LocalDate.parse(request.getBirthDate());
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        LocalDate onboardingDate = LocalDate.now();

        User user = User.builder()
                .userId(request.getId())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .phone(request.getPhone())
                .height(request.getHeight())
                .weight(request.getWeight())
                .gender(request.getGender())
                .age(age)
                .personaType(null)
                .onboardingDate(onboardingDate)
                .build();

        userMapper.insertUser(user);

        Preferences preferencesDto = request.getPreferences();
        if (preferencesDto != null) {
            Preference preference = Preference.builder()
                    .userId(request.getId())
                    .allergies(joinList(preferencesDto.getAllergies()))
                    .dislikes(joinList(preferencesDto.getDislikes()))
                    .preferredTextures(joinList(preferencesDto.getTextures()))
                    .preferredFlavors(joinList(preferencesDto.getFlavors()))
                    .build();
            preferenceMapper.insertPreference(preference);
        }

        return SignupResponse.builder()
                .userId(request.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByUserId(request.getId());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String joinList(List<String> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        return String.join(",", values);
    }
}
