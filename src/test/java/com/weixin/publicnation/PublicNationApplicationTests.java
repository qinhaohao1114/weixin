package com.weixin.publicnation;

import com.weixin.publicnation.service.MenuService;
import com.weixin.publicnation.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicNationApplicationTests {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MenuService menuService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testToken(){
        String access_token = tokenService.getAccess_Token();
        Assert.assertTrue(StringUtils.isNotEmpty(access_token));

    }

    @Test
    public void createMenu(){
        int result = menuService.createMenu(tokenService.getAccess_Token(), menuService.initMenu());
        Assert.assertTrue(result==0);
    }

    @Test
    public void deleteMenu(){

        int result = menuService.deleteMenu(tokenService.getAccess_Token());
        Assert.assertTrue(result==200);

    }

}
