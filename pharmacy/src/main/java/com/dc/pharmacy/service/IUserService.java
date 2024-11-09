package com.dc.pharmacy.service;

import com.dc.pharmacy.dto.UserInfo;

public interface IUserService {

    void addUser(UserInfo userInfo);

    UserInfo findUser(String email);
}
