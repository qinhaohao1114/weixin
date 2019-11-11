package com.weixin.publicnation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DynamicPlanning {

    /**
     * 背包问题，已知n种物品和一个
     */

    public static void main(String[] args) {

        ThreadPoolExecutor executor=new ThreadPoolExecutor(4,4,1000, TimeUnit.MICROSECONDS, new ArrayBlockingQueue(20));
        executor.execute(()-> System.out.println(1));




        class  CustomRejectedExecutionHandler implements RejectedExecutionHandler {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        }
    }
}
