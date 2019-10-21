package com.weixin.publicnation;

import com.weixin.publicnation.bean.entity.Good;
import com.weixin.publicnation.service.MenuService;
import com.weixin.publicnation.service.TokenService;
import com.weixin.publicnation.thirddata.PddDataService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PublicNationApplicationTests {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PddDataService pddDataService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testToken(){
        String access_token = tokenService.getAccess_Token();
        Assert.assertTrue(StringUtils.isNotEmpty(access_token));

    }

    @Test
    public void createMenu(){
        int result = menuService.createMenu(tokenService.getAccess_Token(), menuService.initMenu());
        Assert.assertTrue(result==0);
    }

    @Test
    public void deleteMenu(){

        int result = menuService.deleteMenu(tokenService.getAccess_Token());
        Assert.assertTrue(result==200);

    }
    @Test
    public void getData(){
        pddDataService.test();

    }

    public int[] arr={2,5,11};
    @Test
    public void test(){
        HashMap<Integer, Integer> map = new HashMap<>();
        lls(7,map);
        System.out.println(map);
    }

    public void lls(int num, HashMap<Integer,Integer> map){
        for (int i = arr.length-1; i >= 0; i--) {
            ls(num,i,map);
            if (map.size()>0){
                break;
            }
        }
    }

    public void ls(int num,int index,HashMap<Integer,Integer> map){
            if (index<0){
                map.clear();
                return;
            }
            Integer s=num%arr[index];
            if (s!=num){
                map.put(arr[index],num/arr[index]);
            }
            if (s==0){
                return;
            }
            ls(s,index-1,map);
    }



    @Test
    public void tt(){
        int i = 15 % 11;
        System.out.println(i);
    }
    public void ll(int leftNum,List<Integer> list){
        if (leftNum==0){
            System.out.println(list);
            return;
        }
        for (int i : arr) {
            int c=leftNum-i;
            if (c>=0){
                ArrayList<Integer> lll = new ArrayList<>(list);
                lll.add(i);
                ll(c,lll);
            }
        }
    }
}
