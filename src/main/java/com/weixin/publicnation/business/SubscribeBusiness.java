package com.weixin.publicnation.business;

import com.weixin.publicnation.annotation.BusinessContent;
import com.weixin.publicnation.annotation.BusinessHandle;
import com.weixin.publicnation.constant.MsgType;
import com.weixin.publicnation.utils.TextMessageUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@BusinessHandle(type = MsgType.EVENT)
public class SubscribeBusiness {


    @BusinessContent(content = "subscribe")
    public String subscribe(Map<String,String> params,String fromUser,String toUser){

        return TextMessageUtil.initMessage(fromUser,toUser);
    }

}
