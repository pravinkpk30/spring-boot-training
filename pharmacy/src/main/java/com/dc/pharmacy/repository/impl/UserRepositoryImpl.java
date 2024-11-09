package com.dc.pharmacy.repository.impl;

import com.dc.pharmacy.entity.User;
import com.dc.pharmacy.repository.IUserRepository;
import com.dc.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUser(String email) {
        return userRepository.findByEmail(email).get();
    }
}
