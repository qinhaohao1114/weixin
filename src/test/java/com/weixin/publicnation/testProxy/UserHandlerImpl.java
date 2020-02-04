package com.weixin.publicnation.testProxy;

/**
 * @Author qinhaohao
 * @Date 2020-02-04 18:12
 **/
public class UserHandlerImpl implements UserHandler
{
    @Override
    public User setUser(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Override
    public void setAge(User user, Integer age) {
        user.setAge(age);
    }

    @Override
    public void setName(User user, String name) {
        user.setName(name);
    }
}
