package com.weixin.publicnation.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 类名称: MessageTest
 * 类描述: 消息内容实体
 * @author yuanjun
 * 创建时间:2017年12月5日上午10:41:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTextVo extends BaseMessageVo {

	/**
	 * 	文本消息内容
	 */
	private String Content;

	/**
	 * 	消息id，64位整型
	 */
	private String MsgId;

}