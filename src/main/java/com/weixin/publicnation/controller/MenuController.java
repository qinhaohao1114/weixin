package com.weixin.publicnation.controller;

import com.starsgroupchina.common.response.SimpleResponse;
import com.weixin.publicnation.service.MenuService;
import com.weixin.publicnation.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public SimpleResponse<String> createMenu(){

        int menu = menuService.createMenu(tokenService.getAccess_Token(), menuService.initMenu());
        if (menu==0){
            return SimpleResponse.success();
        }
        return SimpleResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
}
