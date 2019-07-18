package com.weixin.publicnation.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
 
import com.thoughtworks.xstream.XStream;
import com.weixin.publicnation.bean.entity.FileInfo;
import com.weixin.publicnation.bean.vo.ImageMessageVo;
import com.weixin.publicnation.bean.vo.ImageVo;
import com.weixin.publicnation.utils.BaseMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageMessageService implements BaseMessageUtil<ImageMessageVo> {

	@Autowired
	private FileInfoService fileInfoService;
	/**
	 * 将信息转为xml格式
	 */
	@Override
	public String messageToxml(ImageMessageVo imageMessage) {
		XStream xtream = new XStream();
		xtream.alias("xml", imageMessage.getClass());
		xtream.alias("Image", new ImageVo().getClass());
		return xtream.toXML(imageMessage);
	}
	/**
	 * 封装信息
	 */
	@Override
	public String initMessage(String FromUserName, String ToUserName,Long id) {
		FileInfo fileInfo = fileInfoService.getById(id);
		ImageVo image = new ImageVo();
		image.setMediaId(fileInfo.getMediaId());
		ImageMessageVo message = new ImageMessageVo();
		message.setFromUserName(ToUserName);
		message.setToUserName(FromUserName);
		message.setCreateTime(System.currentTimeMillis());
		message.setMsgType("image");
		message.setImage(image);
		return messageToxml(message);
	}

}