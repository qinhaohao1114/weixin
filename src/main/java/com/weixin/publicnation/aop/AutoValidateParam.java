package com.weixin.publicnation.aop;

import com.weixin.publicnation.response.SimpleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * 自动验证controller层的参数
 *
 * @author qhh
 * @date 2019/4/1
 */
@Slf4j
@Aspect
@Component
@Order(2)
public class AutoValidateParam {

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.weixin.publicnation.service.impl..*.*(..))")
    public void cutService() {
    }


    /**
     * 在切入点开始处切入内容
     *
     * @param joinPoint
     */
    @Around("cutService()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        // 验证结果
        // 获取所有的请求参数
        Object[] args = joinPoint.getArgs();
        ValidateResult validateResult = ValidateResult.builder().passed(true).message("校验通过").build();
        if (null != args && args.length > 0) {
            for (Object obj : args) {
                if (obj instanceof BindingResult) {
                    // 参数验证
                    validate((BindingResult) obj,validateResult);
                    break;
                }
            }
        }
        // 验证通过执行拦截方法，否则不执行
        if (validateResult.passed) {
            try {
                // 执行拦截方法
                result = joinPoint.proceed();
            } catch (Throwable ex) {
                log.error("AOP执行拦截方法时异常, {}", ex);
            }
        } else {
            result = SimpleResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), validateResult.getMessage());
        }
        return result;
    }

    private void validate(BindingResult obj, ValidateResult validateResult) {
        if (obj.hasErrors()){
            validateResult.setPassed(false);
            validateResult.setMessage(obj.getFieldError().getDefaultMessage());
        };
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class ValidateResult{
        private boolean passed;
        private String message;
    }

}

