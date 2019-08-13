//package com.weixin.publicnation.controller;
//
//import com.weixin.publicnation.response.SimpleResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Slf4j
//@Controller
//public class LoginController {
//
//    @RequestMapping("/")
//    public SimpleResponse<String> showHome() {
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        log.info("当前登陆用户：" + name);
//
//        return SimpleResponse.success("登陆成功");
//    }
//
//    @RequestMapping("/admin")
//    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String printAdmin() {
//        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
//    }
//
//    @RequestMapping("/user")
//    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String printUser() {
//        return "如果你看见这句话，说明你有ROLE_USER角色";
//    }
//}
