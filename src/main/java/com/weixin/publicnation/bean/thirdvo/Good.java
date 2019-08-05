package com.weixin.publicnation.bean.thirdvo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Good {

    private String goodsId;

    private String goodsName;

    private String shortName;

    private String imageUrl;

    private String hdThumbUrl;

    private String linkUrl;


}
