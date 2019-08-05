package com.weixin.publicnation.controller;

import com.starsgroupchina.common.response.ListResponse;
import com.weixin.publicnation.bean.entity.Good;
import com.weixin.publicnation.bean.entity.GoodExample;
import com.weixin.publicnation.service.GoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "PDD-SWAGGER1", description = "商品 - good")
@RequestMapping("/goods")
public class GoodController {


    @Autowired
    private GoodService goodService;

    @GetMapping
    @ApiOperation("商品列表")
    public ListResponse<Good> getGoods(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "20") int size){
        GoodExample goodExample = new GoodExample();
        goodExample.setOffset((page-1)*size);
        goodExample.setLimit(size);
        List<Good> goods = goodService.query(goodExample).collect(Collectors.toList());
        long count = goodService.count(new GoodExample());
        return ListResponse.success(goods,count,page,size);
    }
}
