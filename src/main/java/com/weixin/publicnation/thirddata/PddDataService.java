package com.weixin.publicnation.thirddata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.weixin.publicnation.bean.entity.Good;
import com.weixin.publicnation.bean.entity.GoodRecommend;
import com.weixin.publicnation.service.GoodRecommendService;
import com.weixin.publicnation.service.GoodService;
import com.weixin.publicnation.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class PddDataService {

    private String hotGoodsUrl="http://mobile.yangkeduo.com/proxy/api/api/alexa/goods/hub?list_update_time=true&platform=1&assist_allowed=1&page=2&size=60&list_id=aE80qPJUhJ&antiContent=0anAfxnUmycYY9dV_mpYTdr9jtTdfwtIpYR6OO0svdFw_YYs1VnjL4xaKbaQQvh6sOnHU9HzW9ED6CU8CkIvtfGnhuPwNrbYOde990CtR9mLcPVC2148j9PjRhi195rCFMxXj8Di1yOsyvUm-yOeDMXOlS1t3JtiL5wZzL4zZbyzXK4pRf1kmIwgNAS4m6gs_6VIZXrq68Bc2N-CAKciaL6WT1hxLRo3srdERSEKeJIcl0VfejAFNAIputlq01Xefan499hEfLj08oDCUgt7Ul57yfXltAX4urwCK00VaD2W4EmtH9Ap4XJ9l9nG6tguAoIaZTFCUTfFKT3Xdcmj6mbdxGZJ2FmaElxFb8HeTNcvV2HoTRE3cN2OpnbG-ycEp_A-DGD6tBpd5zvdk585O2vm6C-XQ3X8Md-vRDHmU6fW2iuy6zcD8vYEv1DXrNZGVKWMXi0jT6tpkDw-E3qkVNnY3E1Ol8yvwDqQBowV3GCETOYfwQe-XmYlP0DN7Vw1LH_7RHGIlbxIlwZBO3mrSNrRWvpHfqhhMT8j_GNr6LvYy8L8RTWpk4kRo&pdduid=2776961672086&is_back=1";

    private String recomendGoodUrl="http://mobile.yangkeduo.com/proxy/api/api/barrow/query?app_name=rectab_sim_gyl&support_types=0_1&offset=0&count=60&list_id=5OYP4rGW4n&dp_list_id=5OYP4rGW4n_dp&pdduid=2776961672086";

    @Autowired
    private GoodService goodService;

    @Autowired
    private GoodRecommendService goodRecommendService;

    public void HotGoods(){

        ResponseEntity<String> stringResponseEntity = RestTemplateUtils.get(hotGoodsUrl, String.class);
        String body = stringResponseEntity.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
//        JSONObject jsonObject= (JSONObject) JSONObject.toJSON(body);
        String goodsList = jsonObject.getString("goods_list");
        List<Good> goods = JSONObject.parseArray(goodsList, Good.class);
        goodService.saveBatch(goods);
    }

    public void recomendGood(){
        ResponseEntity<String> stringResponseEntity = RestTemplateUtils.get(recomendGoodUrl, String.class);
        String body = stringResponseEntity.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        String goodsList = jsonObject.getString("data");
        log.info(body);
        List<GoodRecommend> goods = JSONObject.parseArray(goodsList, GoodRecommend.class);
        goodRecommendService.saveBatch(goods);
    }

    public void test(){
        String url="https://iwms.cloud.cmbchina.com/leasing/api/1.0/buildings?pageIndex=1";
        HashMap<String, String> header = Maps.newHashMap();
        header.put("1208mocecapsucofD","21");
        ResponseEntity<String> stringResponseEntity = RestTemplateUtils.get(url, header, String.class);
        String body = stringResponseEntity.getBody();
        System.out.println(body);

    }
}
