package com.weixin.publicnation.service;

import com.weixin.publicnation.bean.vo.AccessToken;
import com.weixin.publicnation.constant.Constant;
import com.weixin.publicnation.utils.RestTemplateUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {



    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${weixin.appId}")
    private String appId;

    @Value("${weixin.appsecret}")
    private String appsecret;

    @Value("${weixin.accessUrl}")
    private String accessUrl;


    private AccessToken getAccessToken(){
        System.out.println("从接口中获取");
        AccessToken token = new AccessToken();
        String url = accessUrl.replace("APPID", appId).replace("APPSECRET", appsecret);
        ResponseEntity<String> response = RestTemplateUtils.get(url, String.class);
        if (response.getStatusCodeValue()== HttpStatus.OK.value()){
            JSONObject  json = JSONObject.fromObject(response.getBody());
            token.setAccess_token(json.getString("access_token"));
            token.setExpires_in(json.getInt("expires_in"));
            redisTemplate.opsForValue().set(Constant.TOKEN_REDIS_KEY,json.getString(Constant.TOKEN_REDIS_KEY),60*60*2, TimeUnit.SECONDS);
        }
        return token;
    }
    /**
     * 获取凭证
     * @return
     */
    public  String getAccess_Token(){
        String access_token= (String) redisTemplate.opsForValue().get(Constant.TOKEN_REDIS_KEY);
        if(StringUtils.isEmpty(access_token)){
            AccessToken token = getAccessToken();
            access_token = token.getAccess_token();
        }
        return access_token;
    }

}
