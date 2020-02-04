package com.weixin.publicnation.testProxy;

/**
 * @Author qinhaohao
 * @Date 2020-02-04 18:09
 **/
public interface UserHandler {

    User setUser(String name,Integer age);

    void setAge(User user,Integer age);

    void setName(User user,String name);
}
