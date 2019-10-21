package com.weixin.publicnation;

import org.junit.Test;

import java.util.*;

public class AlgorithmTest {

    /**
     * 动态规划进阶
     * 暴力递归->带备忘录->动态规划
     * 1，1，2，3，5，8，13.... f(n)=f(n-1)+f(n-2)
     */
    @Test
    public void testBaoLiDiGui(){

//        int fib = fib(3);
//        int fib = fib1(20);
        int fib = fib2(20);
        System.out.println(fib);
    }


    /**
     * 1 暴力递归 导致重复计算
     * @param N
     * @return
     */
    int fib(int N){
        if (N==1 || N==2) return 1;
        return fib(N-1)+fib(N-2);
    }

    /**
     *带备忘录的递归解法 将计算值存储起来，直接计算获得
     */
    int fib1(int N){
        if (N<1) return 0;
        return helper(new HashMap<>(),N);

    }
    int helper(Map<Integer,Integer> map, int n){
        if (n==1 || n==2) return 1;
        Integer x = map.get(n);
        if (x!=null&&x!=0) return map.get(n);
        map.put(n,helper(map,n-1)+helper(map,n-2));
        return map.get(n);
    }

    int fib2(int N){
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(1,1);
        map.put(2,1);
        for (int i=3;i<=N;i++){
            map.put(i,map.get(i-1)+map.get(i-2));
        }
        return map.get(N);
    }
}
