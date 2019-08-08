package com.weixin.publicnation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.publicnation.bean.entity.Good;
import com.weixin.publicnation.dao.GoodMapper;
import com.weixin.publicnation.service.GoodService;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper,Good> implements GoodService {

//    @Override
//    public IPage<Good> selectPage(Page page, Good good) {
//        return this.selectPage(page,new QueryWrapper<>(good));
//    }
//
//    @Override
//    public void insert(List<Good> goods) {
//        goods.forEach(g->{
//            this.insert(g);
//        });
//    }
}
