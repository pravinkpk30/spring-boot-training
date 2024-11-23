package com.dc.pharmacy.repository;

import com.dc.pharmacy.entity.UserEntity;

public interface UserRepository {

    void saveUser(UserEntity user);

    UserEntity findUser(String email);

    UserEntity findByUsername(String username);

    UserEntity findUserById(Long userId);
}
