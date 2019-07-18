package com.weixin.publicnation.config;

import com.weixin.publicnation.annotation.BusinessContent;
import com.weixin.publicnation.annotation.BusinessHandle;
import com.weixin.publicnation.utils.TextMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
public class HandlerScanner implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<? extends Object> clazz = bean.getClass();
                //获取类注解
                BusinessHandle businessHandle = clazz.getAnnotation(BusinessHandle.class);
                if (businessHandle == null){
                    return bean;
                }
                    String type = businessHandle.type();
                    Method[] methods = clazz.getMethods();
                    if(methods != null && methods.length>0){
                        for (Method method:methods){
                            //获取自定义方法注解
                            BusinessContent businessContent = method.getAnnotation(BusinessContent.class);
                            if (businessContent == null){
                                continue;
                            }
                            String content = businessContent.content();
                            try {
                                //将符合条件的加入到map中
                                if (InvokerHoler.getInvoker(type,content)==null){
                                    InvokerHoler.addInvoker(type,content, Invoker.valueOf(method,bean));
                                }else {
                                    log.error("重复命令:"+"type:"+type+" "+"content: "+content);
                                }
                                if (type.equals("text")){
                                    TextMessageUtil.keyBuffer.append(content+",");
                                }
                            } catch (Exception e) {
                                log.error("获取失败: "+e.getMessage());
                            }
                        }
                }
                    if (TextMessageUtil.keyBuffer.length()>0){
                        TextMessageUtil.keyBuffer = TextMessageUtil.keyBuffer.deleteCharAt(TextMessageUtil.keyBuffer.length() - 1);
                    }
        return bean;
    }
}