package com.weixin.publicnation.testProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDKDynamicProxy
 * jdkd动态代理
 *
 * @author
 * @create 2018-03-29 16:17
 **/
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        System.out.println(name+" before");
        Object result = method.invoke(target, args);
        System.out.println(name+" after");
        return result;
    }
}
