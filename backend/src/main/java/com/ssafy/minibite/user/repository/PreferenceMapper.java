package com.ssafy.minibite.user.repository;

import com.ssafy.minibite.user.domain.Preference;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PreferenceMapper {

    void insertPreference(Preference preference);

    Preference findByUserId(@Param("userId") String userId);
}

