package com.dc.pharmacy.repository.impl;

import com.dc.pharmacy.entity.UserEntity;
import com.dc.pharmacy.exception.UserNotFoundException;
import com.dc.pharmacy.repository.IUserRepository;
import com.dc.pharmacy.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity findUser(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findUserById(Long userId) {
        try {
            return userRepository.findById(userId).orElseThrow(() -> new Exception("User not found!!"));
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }
}
