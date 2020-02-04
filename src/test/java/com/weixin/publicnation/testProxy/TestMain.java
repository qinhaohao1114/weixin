package com.weixin.publicnation.testProxy;

import javax.security.auth.Subject;

/**
 * @Author qinhaohao
 * @Date 2020-02-04 18:14
 **/
public class TestMain {
    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // jdk动态代理测试
        UserHandler proxy = new JDKDynamicProxy(new UserHandlerImpl()).getProxy();
        User huqiao = proxy.setUser("huqiao", 22);
        proxy.setName(huqiao,"huqiao1");
        proxy.setAge(huqiao,24);
    }

}
