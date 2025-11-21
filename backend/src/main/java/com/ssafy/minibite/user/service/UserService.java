package com.ssafy.minibite.user.service;

import com.ssafy.minibite.global.exception.BusinessException;
import com.ssafy.minibite.global.exception.ErrorCode;
import com.ssafy.minibite.user.domain.Preference;
import com.ssafy.minibite.user.domain.User;
import com.ssafy.minibite.user.dto.ProfileResponse;
import com.ssafy.minibite.user.repository.PreferenceMapper;
import com.ssafy.minibite.user.repository.UserMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PreferenceMapper preferenceMapper;

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(String userId) {
        User user = userMapper.findByUserId(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        Preference preference = preferenceMapper.findByUserId(userId);

        List<String> allergies = preference != null ? splitToList(preference.getAllergies()) : Collections.emptyList();
        List<String> dislikes = preference != null ? splitToList(preference.getDislikes()) : Collections.emptyList();

        LocalDate birthDate = user.getBirthDate();
        String birthDateString = birthDate != null ? birthDate.toString() : null;

        return ProfileResponse.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .birthDate(birthDateString)
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .allergies(allergies)
                .dislikes(dislikes)
                .personaType(user.getPersonaType())
                .build();
    }

    private List<String> splitToList(String value) {
        if (value == null || value.isBlank()) {
            return Collections.emptyList();
        }
        return Stream.of(value.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}

