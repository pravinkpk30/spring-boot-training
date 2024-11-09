package com.dc.pharmacy.repository;

import com.dc.pharmacy.entity.User;

public interface UserRepository {

    void saveUser(User user);

    User findUser(String email);
}
