package com.ssafy.minibite.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Preference {

    private String userId;
    private String allergies;
    private String dislikes;
    private String preferredTextures;
    private String preferredFlavors;
}

