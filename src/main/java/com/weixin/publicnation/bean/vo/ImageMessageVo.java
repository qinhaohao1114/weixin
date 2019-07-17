package com.weixin.publicnation.bean.vo;

import lombok.Data;

/**
 * 
 * 类名称: ImageMessage
 * 类描述: 图片消息
 */
@Data
public class ImageMessageVo extends BaseMessageVo{

	/**
	 * Image节点
	 */
	private ImageVo Image;
	
	
}