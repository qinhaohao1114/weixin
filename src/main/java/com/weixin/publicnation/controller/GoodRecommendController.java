package com.weixin.publicnation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weixin.publicnation.bean.entity.Good;
import com.weixin.publicnation.bean.entity.GoodRecommend;
import com.weixin.publicnation.response.ListResponse;
import com.weixin.publicnation.service.GoodRecommendService;
import com.weixin.publicnation.service.GoodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qinhaohao
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/good-recommend")
public class GoodRecommendController {


    @Autowired
    private GoodRecommendService goodService;

    @GetMapping
    @ApiOperation("商品列表")
    public ListResponse<GoodRecommend> getGoods(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "20") int size){

        Page<GoodRecommend> p = new Page<>(page,size);
        IPage<GoodRecommend> goodIPage = goodService.page(p, null);
        return ListResponse.success(goodIPage.getRecords(),goodIPage.getTotal(),page,size);
    }
}
