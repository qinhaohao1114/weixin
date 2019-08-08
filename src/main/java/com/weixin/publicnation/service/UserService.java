package com.weixin.publicnation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.publicnation.bean.dto.UserDto;
import com.weixin.publicnation.bean.entity.User;
import org.springframework.boot.context.properties.bind.BindResult;

public interface UserService extends IService<User> {

    boolean register(UserDto userDto);
}
