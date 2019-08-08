package com.weixin.publicnation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weixin.publicnation.bean.entity.Good;
import com.weixin.publicnation.response.ListResponse;
import com.weixin.publicnation.service.GoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        Page<Good> p = new Page<>(page,size);
        IPage<Good> goodIPage = goodService.page(p, null);
        return ListResponse.success(goodIPage.getRecords(),goodIPage.getTotal(),page,size);
    }
}
