package com.weixin.publicnation.business;

import com.weixin.publicnation.annotation.BusinessContent;
import com.weixin.publicnation.annotation.BusinessHandle;
import com.weixin.publicnation.constant.MsgType;
import com.weixin.publicnation.utils.TextMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@BusinessHandle(type = MsgType.EVENT)
@Slf4j
public class SubscribeBusiness {


    @BusinessContent(content = "subscribe")
    public String subscribe(Map<String,String> params,String fromUser,String toUser){

        log.info("获得新关注");

        return TextMessageUtil.initMessage(fromUser,toUser);
    }
    @BusinessContent(content = "unsubscribe")
    public String unsubscribe(Map<String,String> params,String fromUser,String toUser){

        log.info("取消关注");

        return TextMessageUtil.initMessage(fromUser,toUser);
    }
}
