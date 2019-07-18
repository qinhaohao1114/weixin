package com.weixin.publicnation.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@Data
public class Invoker {


    /**
     * 方法
     */
    private Method method;

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 获取实体类
     * @param method
     * @param target
     * @return
     */
    public static Invoker valueOf(Method method,Object target){
        Invoker invoker = new Invoker();
        invoker.setMethod(method);
        invoker.setTarget(target);
        return invoker;
    }

    public Object invoke(Object... paramValues){
        try {
            return method.invoke(target,paramValues);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        } catch (InvocationTargetException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}