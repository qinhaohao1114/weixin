package com.weixin.publicnation.utils;

import com.thoughtworks.xstream.XStream;
import com.weixin.publicnation.bean.vo.MessageTextVo;

import java.util.Date;

public class TextMessageUtil{
	/**
	 * 将发送消息封装成对应的xml格式
	 */
	public static String messageToxml(MessageTextVo message) {
		XStream xstream  = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	/**
	 * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
	 * @param FromUserName
	 * @param ToUserName
	 */
	public static String initMessage(String FromUserName, String ToUserName) {
		MessageTextVo text = new MessageTextVo();
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		text.setContent("欢迎进入当前微信号测试");
		text.setCreateTime(System.currentTimeMillis());
		text.setMsgType("text");
		return messageToxml(text);
	}
    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param FromUserName
     * @param ToUserName
     */
    public static String initMessage(String FromUserName, String ToUserName,String content) {
        MessageTextVo text = new MessageTextVo();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent("您输入的内容是："+content);
        text.setCreateTime(System.currentTimeMillis());
        text.setMsgType("text");
        return messageToxml(text);
    }
}