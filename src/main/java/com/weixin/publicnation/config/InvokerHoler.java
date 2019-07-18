package com.weixin.publicnation.config;

import java.util.HashMap;
import java.util.Map;

public class InvokerHoler {

    /**
     * 命令调用器
     */
    private static Map<String, Map<String,Invoker>> invokers = new HashMap<String, Map<String, Invoker>>();

    /**
     * 添加命令调用
     * @param invoker
     */
    public static void addInvoker(String type,String content,Invoker invoker){
        Map<String,Invoker> map = invokers.get(type);
        if(map == null){
            map = new HashMap<String, Invoker>();
            invokers.put(type,map);
        }
        map.put(content,invoker);
    }

    /**
     * 获取命令调用
     * @return
     * @throws Exception
     */
    public static Invoker getInvoker(String type,String content)throws Exception{
        Invoker invoker = null;
        Map<String,Invoker> map = invokers.get(type);
        if(map!=null){
            invoker = map.get(content);
        }
        return invoker;
    }
}

