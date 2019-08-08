package com.weixin.publicnation.transfer;

import com.weixin.publicnation.bean.dto.UserDto;
import com.weixin.publicnation.bean.vo.UserVo;
import org.springframework.beans.BeanUtils;

public class UserVoTransfer {

    public static UserDto voToDto(UserVo userVo){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userVo,userDto);
        return userDto;
    }
}
