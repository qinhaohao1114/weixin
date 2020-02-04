package com.weixin.publicnation.service;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publicnation.bean.vo.ButtonVo;
import com.weixin.publicnation.bean.vo.ClickButtonVo;
import com.weixin.publicnation.bean.vo.MenuVo;
import com.weixin.publicnation.bean.vo.ViewButtonVo;
import com.weixin.publicnation.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 类名称: MemuUtil
 * 类描述: 菜单工具
 */
@Service
public class MenuService {


	@Value("${weixin.menuUrl}")
	private String menuUrl;

	@Value("${weixin.menuDeleteUrl}")
	private String menuDeleteUrl;
	/**
	 * 创建菜单
	 * @param accessToken
	 * @param menu 菜单json格式字符串
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int createMenu(String accessToken,String menu){
		int result = Integer.MIN_VALUE;
		String url = menuUrl.replaceAll("ACCESS_TOKEN", accessToken);
		ResponseEntity<String> post = RestTemplateUtils.post(url, menu, String.class);
		if (post.getStatusCodeValue()== HttpStatus.OK.value()){
			JSONObject json=JSONObject.parseObject(post.getBody());
			result = json.getInteger("errcode");

		}
		return result;
	}

	public int deleteMenu(String accessToken){
		int result = Integer.MIN_VALUE;
		String url = menuDeleteUrl.replaceAll("ACCESS_TOKEN", accessToken);
		ResponseEntity<String> response = RestTemplateUtils.get(url,String.class);
		return response.getStatusCodeValue();
	}

	public String initMenu(){
		String result = "";
		//创建点击一级菜单
		ClickButtonVo button11 = new ClickButtonVo();
		button11.setName("old active");
		button11.setKey("11");
		button11.setType("click");

		//创建跳转型一级菜单
		ViewButtonVo button21 = new ViewButtonVo();
		button21.setName("pinduoduo");
		button21.setType("view");
		button21.setUrl("http://47.100.55.164/qhhpdd");

		//创建其他类型的菜单与click型用法一致
		ClickButtonVo button31 = new ClickButtonVo();
		button31.setName("photograph");
		button31.setType("pic_photo_or_album");
		button31.setKey("31");

		ClickButtonVo button32 = new ClickButtonVo();
		button32.setName("Sending location");
		button32.setKey("32");
		button32.setType("location_select");

		//封装到一级菜单
		ButtonVo button = new ButtonVo();
		button.setName("menu");
		button.setType("click");
		button.setSub_button(new ButtonVo[]{button31,button32});

		//封装菜单
		MenuVo menu = new MenuVo();
		menu.setButton(new ButtonVo[]{button11,button21,button});
		return JSONObject.toJSONString(menu);
	}
}
