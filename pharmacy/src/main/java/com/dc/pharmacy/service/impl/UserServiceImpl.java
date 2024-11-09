package com.dc.pharmacy.service.impl;

import com.dc.pharmacy.dto.UserInfo;
import com.dc.pharmacy.entity.User;
import com.dc.pharmacy.repository.UserRepository;
import com.dc.pharmacy.service.IUserService;
import com.dc.pharmacy.utility.DTOConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOConversion conversion;

    @Override
    public void addUser(UserInfo userInfo) {
        User user = conversion.convertUserDtoToEntity(userInfo);
        userRepository.saveUser(user);
    }

    @Override
    public UserInfo findUser(String email) {
        User user = userRepository.findUser(email);
        return conversion.prepareUserResponse(user);
    }
}
