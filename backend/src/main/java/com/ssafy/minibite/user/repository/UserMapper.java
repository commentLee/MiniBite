package com.ssafy.minibite.user.repository;

import com.ssafy.minibite.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    boolean existsByUserId(@Param("userId") String userId);

    User findByUserId(@Param("userId") String userId);
}

