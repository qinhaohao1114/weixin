package com.weixin.publicnation.transfer;

import com.weixin.publicnation.bean.dto.UserDto;
import com.weixin.publicnation.bean.entity.User;
import org.springframework.beans.BeanUtils;

public class UserDtoTransfer {
    public static User DtoToEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }
}
