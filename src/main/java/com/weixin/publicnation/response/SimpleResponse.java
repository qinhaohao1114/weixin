package com.weixin.publicnation.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhangfeng on 16/7/20.
 */
@Data
@ApiModel("单值返回")
public class SimpleResponse<T> implements Serializable {

    protected static final int SUCCESS_STATUS = 200;
    protected static final String SUCCESS_MESSAGE = "success";

    T data;
    @ApiModelProperty(value = "返回消息,成功为success", example = "success")
    String message;
    @ApiModelProperty(value = "返回状态,成功为200", example = "200")
    int status;

    public static <T> SimpleResponse<T> success() {
        return success((T) "");
    }

    public static <T> SimpleResponse<T> success(T data) {
        return success(data, SUCCESS_MESSAGE, "");
    }

    public static <T> SimpleResponse<T> success(T data, String message, String moreInfo) {
        return success(data, message, SUCCESS_STATUS, moreInfo);
    }

    public static <T> SimpleResponse<T> success(T data, String message, int responseStatus, String moreInfo) {
        SimpleResponse response = new SimpleResponse();
        response.setData(data);
        response.setStatus(responseStatus);
        response.setMessage(message);
        return response;
    }

    public static <T> SimpleResponse<T> error(int responseStatus, String message, String moreInfo) {
        SimpleResponse response = new SimpleResponse();
        response.setStatus(responseStatus);
        response.setMessage(message);
        return response;
    }

    public static <T> SimpleResponse<T> error(int responseStatus, String message) {
        return error(responseStatus, message, null);
    }

    public static <T> SimpleResponse<T> error(int responseStatus) {
        return error(responseStatus, System.getProperty(String.valueOf(responseStatus)), null);
    }
}
