package com.weixin.publicnation.bean.vo;

import lombok.Data;

/**
 * 
 * 类名称: Button
 * 类描述: 按钮
 */
@Data
public class ButtonVo {

	/**
	 * 菜单标题
	 */
	private String name;
	/**
	 * 菜单的响应动作类型
	 */
	private String type;


	private ButtonVo[] sub_button;
 

	
	
}