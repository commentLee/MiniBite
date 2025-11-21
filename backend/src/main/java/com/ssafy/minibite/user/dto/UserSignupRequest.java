package com.ssafy.minibite.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String phone;

    @NotBlank
    private String birthDate;

    @NotBlank
    private String gender;

    @NotNull
    private Integer height;

    @NotNull
    private Float weight;

    @Valid
    private Preferences preferences;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Preferences {

        private List<String> allergies;
        private List<String> dislikes;
        private List<String> textures;
        private List<String> flavors;
    }
}

