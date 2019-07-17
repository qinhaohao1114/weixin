package com.weixin.publicnation.bean.vo;

import lombok.Data;

/**
 * 
 * 类名称: BaseMessage
 * 类描述: 回复消息的基类
 */
@Data
public class BaseMessageVo {
 
	protected String ToUserName;
	protected String FromUserName;
	protected long CreateTime;
	protected String MsgType;

 
}