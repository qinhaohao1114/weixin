package com.weixin.publicnation.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhangfeng on 2018-5-10.
 */
@Data
public class BaseModel {
    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("创建人姓名")
    private String createUser;

    @ApiModelProperty("创建人ID")
    private Integer createUserId;
//
//    @ApiModelProperty("0,正常，1禁用，-1删除")
//    private Short status;

    @ApiModelProperty("修改时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    @ApiModelProperty("修改人姓名")
    private String modifyUser;

    @ApiModelProperty("修改人ID")
    private Integer modifyUserId;
}
