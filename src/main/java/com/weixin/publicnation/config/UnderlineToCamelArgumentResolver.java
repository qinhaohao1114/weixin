package com.weixin.publicnation.config;

import com.weixin.publicnation.annotation.ParameterModel;
import com.weixin.publicnation.utils.StringHelpers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;

public class UnderlineToCamelArgumentResolver extends AbstractCustomizeResolver {


	/**
	 * Whether the given {@linkplain MethodParameter method parameter} is
	 * supported by this resolver.
	 * @param parameter the method parameter to check
	 * @return {@code true} if this resolver supports the supplied parameter;
	 * {@code false} otherwise
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(ParameterModel.class);
	}

	/**
	 * 装载参数
	 *
	 * @param methodParameter       方法参数
	 * @param modelAndViewContainer 返回视图容器
	 * @param nativeWebRequest      本次请求对象
	 * @param webDataBinderFactory  数据绑定工厂
	 * @return the resolved argument value, or {@code null}
	 * @throws Exception in case of errors with the preparation of argument values
	 */
	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		Object org=handleParameterNames(methodParameter, nativeWebRequest);
		valid(methodParameter,modelAndViewContainer,nativeWebRequest,webDataBinderFactory,org);
		return org;
	}

	//处理参数
	private Object handleParameterNames(MethodParameter parameter, NativeWebRequest webRequest) {
		Object obj = BeanUtils.instantiate(parameter.getParameterType());
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
		Iterator<String> paramNames = webRequest.getParameterNames();
		while (paramNames.hasNext()) {
			String paramName = paramNames.next();
			Object o = webRequest.getParameter(paramName);
			wrapper.setPropertyValue(StringHelpers.underLineToCamel(paramName), o);
		}
		return obj;
	}
}
