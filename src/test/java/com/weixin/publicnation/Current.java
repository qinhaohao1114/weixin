package com.weixin.publicnation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Current {



    class testMsea{
        final Lock lock=new ReentrantLock();
        // 条件变量：队列不满
        final Condition notFull = lock.newCondition();

        final Condition notEmpty = lock.newCondition();

        private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        void enq(Integer i){
            lock.lock();
            try {
                while (queue.offer(i)){
                    notFull.await();
                    }
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        void deq(){
            lock.lock();
            try {
                while (queue.isEmpty()){
                    notEmpty.await();
                }
                queue.poll();
                notFull.signal();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }

    }
}
