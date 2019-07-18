package com.weixin.publicnation.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publicnation.config.Invoker;
import com.weixin.publicnation.config.InvokerHoler;
import com.weixin.publicnation.constant.Constant;
import com.weixin.publicnation.constant.EventType;
import com.weixin.publicnation.constant.MsgType;
import com.weixin.publicnation.service.ImageMessageService;
import com.weixin.publicnation.utils.CheckUtil;
import com.weixin.publicnation.utils.MessageUtil;
import com.weixin.publicnation.utils.TextMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("wx")
@Slf4j
public class LoginController {


    @Value("${weixin.token}")
    private String token;

    @Autowired
    private ImageMessageService imageMessageService;

    @GetMapping
    public void login(HttpServletRequest request, HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(CheckUtil.checkSignal(token, timestamp, nonce,signature)){
                log.info("登陆成功");
                out.write(echostr);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            out.close();
        }

    }

    @PostMapping
    public void receive(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String,String> map = MessageUtil.xmlToMap(request);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String event = map.get("Event");
        String eventKey = map.get("EventKey");
        String content = map.get("Content");
        String message = null;
        log.info("FromUserName:{},ToUserName:{},MsgType:{},Content:{}",fromUserName,toUserName,msgType,content);
        String contentParams=null;
        if (msgType.equals(MsgType.EVENT)){
            if (event.equals(EventType.CLICK)){
                contentParams=eventKey;
            }else if (!event.equals(EventType.SCAN)){
                contentParams=event;
            }
        }else if (msgType.equals(MsgType.TEXT)){
             contentParams=content;
        }else {
            contentParams=msgType;
        }
        try {
            Invoker invoker = InvokerHoler.getInvoker(msgType, contentParams);
            if (invoker!=null){
                message = (String) invoker.invoke(map,fromUserName,toUserName);
            }else {
                message = TextMessageUtil.initMessage(fromUserName,toUserName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

}
