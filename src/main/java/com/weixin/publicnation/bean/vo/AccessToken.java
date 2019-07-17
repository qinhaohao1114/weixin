package com.weixin.publicnation.bean.vo;

import lombok.Data;

@Data
public class AccessToken {

	/**
	 * 获取到的凭证
	 */
	private String access_token;

	/**
	 * 凭证有效时间
	 */
	private int expires_in;
	
	
}	