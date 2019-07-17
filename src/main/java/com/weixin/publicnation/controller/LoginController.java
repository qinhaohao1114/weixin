package com.weixin.publicnation.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publicnation.service.ImageMessageService;
import com.weixin.publicnation.utils.CheckUtil;
import com.weixin.publicnation.utils.MessageUtil;
import com.weixin.publicnation.utils.TextMessageUtil;
import lombok.extern.slf4j.Slf4j;
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
            // TODO Auto-generated catch block
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
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String message = null;
        log.info("FromUserName:{},ToUserName:{},MsgType:{},Content:{}",FromUserName,ToUserName,MsgType,Content);
        //处理文本类型，实现输入1，回复相应的封装的内容
        if("text".equals(MsgType)){
            if("1".equals(Content)){
                message = TextMessageUtil.initMessage(FromUserName, ToUserName);
            }else if("图片".equals(Content)){
                message=imageMessageService.initMessage(FromUserName,ToUserName);
            }else {
                message = TextMessageUtil.initMessage(FromUserName, ToUserName,Content);
            }
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
