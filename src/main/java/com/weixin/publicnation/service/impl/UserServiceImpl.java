package com.weixin.publicnation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.publicnation.bean.dto.UserDto;
import com.weixin.publicnation.bean.entity.User;
import com.weixin.publicnation.dao.UserMapper;
import com.weixin.publicnation.service.UserService;
import com.weixin.publicnation.transfer.UserDtoTransfer;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean register(UserDto userDto) {
        User user= UserDtoTransfer.DtoToEntity(userDto);
        return this.save(user);
    }
}
