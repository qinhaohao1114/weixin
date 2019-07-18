package com.weixin.publicnation.business;

import com.weixin.publicnation.annotation.BusinessContent;
import com.weixin.publicnation.annotation.BusinessHandle;
import com.weixin.publicnation.constant.MsgType;
import com.weixin.publicnation.service.ImageMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@BusinessHandle(type = MsgType.TEXT)
public class TextBusiness {

    @Autowired
    private ImageMessageService imageMessageService;

    @BusinessContent(content = "图片")
    public String contentOne(Map<String,String> params,String fromUser,String toUser){

      return imageMessageService.initMessage(fromUser,toUser,1L);
    }

    @BusinessContent(content = "菊花")
    public String contentFlower(Map<String,String> params,String fromUser,String toUser){

        return imageMessageService.initMessage(fromUser,toUser,4L);
    }
}
