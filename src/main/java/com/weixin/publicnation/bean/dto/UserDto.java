package com.weixin.publicnation.bean.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    private String userName;

    private String passWord;


}
