package com.weixin.publicnation.controller;

import com.weixin.publicnation.bean.dto.UserDto;
import com.weixin.publicnation.bean.vo.UserVo;
import com.weixin.publicnation.response.SimpleResponse;
import com.weixin.publicnation.service.UserService;
import com.weixin.publicnation.transfer.UserVoTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public SimpleResponse<String> register(@RequestBody @Valid UserVo userVo, BindResult bindResult){
        UserDto userDto = UserVoTransfer.voToDto(userVo);
        userService.register(userDto);
        return null;
    }
}
