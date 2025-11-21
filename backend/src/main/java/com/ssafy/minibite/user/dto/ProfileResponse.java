package com.ssafy.minibite.user.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponse {

    private final String userId;
    private final String nickname;
    private final String birthDate;
    private final String gender;
    private final Integer height;
    private final Float weight;
    private final List<String> allergies;
    private final List<String> dislikes;
    private final String personaType;
}

