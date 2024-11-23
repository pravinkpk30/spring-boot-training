package com.dc.pharmacy.utility;

import com.dc.pharmacy.dto.UserInfo;
import com.dc.pharmacy.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DTOConversion {

    public UserEntity convertUserDtoToEntity(UserInfo userInfo) {
        UserEntity user = new UserEntity();
        user.setName(userInfo.getName());
        user.setSsn(userInfo.getSsn());
        user.setEmail(userInfo.getEmail());
        user.setUsername(userInfo.getUsername());
        user.setRoles(userInfo.getRoles());
        user.setAddress(userInfo.getAddress());
        return user;
    }

    public UserInfo prepareUserResponse(UserEntity user) {
        UserInfo info = new UserInfo();
        info.setId(user.getId());
        info.setName(user.getName());
        info.setEmail(user.getEmail());
        info.setSsn(user.getSsn());
        info.setUsername(user.getUsername());
        info.setRoles(user.getRoles());
        info.setAddress(user.getAddress());
        return info;
    }

    public UserInfo prepareBasicUserResponse(UserEntity user) {
        UserInfo info = new UserInfo();
        info.setId(user.getId());
        info.setName(user.getName());
        info.setEmail(user.getEmail());
        info.setSsn(user.getSsn());
        info.setUsername(user.getUsername());
        info.setRoles(user.getRoles());
        return info;
    }
}
