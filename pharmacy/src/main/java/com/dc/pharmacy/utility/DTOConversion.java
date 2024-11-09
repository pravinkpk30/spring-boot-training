package com.dc.pharmacy.utility;

import com.dc.pharmacy.dto.UserInfo;
import com.dc.pharmacy.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DTOConversion {

    public User convertUserDtoToEntity(UserInfo userInfo) {
        User user = new User();
        user.setName(userInfo.getName());
        user.setSsn(userInfo.getSsn());
        user.setEmail(userInfo.getEmail());
        return user;
    }

    public UserInfo prepareUserResponse(User user) {
        UserInfo info = new UserInfo();
        info.setId(user.getId());
        info.setName(user.getName());
        info.setEmail(user.getEmail());
        info.setSsn(user.getSsn());
        return info;
    }
}
