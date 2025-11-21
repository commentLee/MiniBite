package com.ssafy.minibite.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String password;
    private String nickname;
    private String phone;
    private Integer height;
    private Float weight;
    private String gender;
    private Integer age;
    private String personaType;
    private LocalDate onboardingDate;
}
