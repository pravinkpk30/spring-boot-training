package com.dc.pharmacy.service.impl;

import com.dc.pharmacy.dto.UserInfo;
import com.dc.pharmacy.entity.UserEntity;
import com.dc.pharmacy.exception.UserNotFoundException;
import com.dc.pharmacy.repository.UserRepository;
import com.dc.pharmacy.service.IUserService;
import com.dc.pharmacy.utility.DTOConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOConversion conversion;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void addUser(UserInfo userInfo) {
        UserEntity user = conversion.convertUserDtoToEntity(userInfo);
        userRepository.saveUser(user);
    }

    @Override
    public UserInfo findUser(String email) {
        UserEntity user = userRepository.findUser(email);
        return conversion.prepareUserResponse(user);
    }

    @Override
    public UserInfo signUp(UserInfo userInfo) {
        // check if username already present or not
        UserEntity entity = userRepository.findByUsername(userInfo.getUsername());
        if(entity != null) {
            throw new UserNotFoundException("Username is already taken !!");
        }
        UserEntity user = conversion.convertUserDtoToEntity(userInfo);
        user.setPassword(encoder.encode(userInfo.getPassword()));
        userRepository.saveUser(user);
        UserEntity response = userRepository.findUser(userInfo.getEmail());
        return conversion.prepareUserResponse(response);
    }

    @Override
    public UserInfo findUserById(Long userId) {
        UserEntity userEntity = userRepository.findUserById(userId);
        return conversion.prepareBasicUserResponse(userEntity);
    }
}
