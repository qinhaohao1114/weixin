package com.weixin.publicnation;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Que {


    public static void main(String[] args) {


    }
    /**
     * 普通数组队列
     */
    class ArrayQueue{

        private String[] items;
        private int capcity=0;
        private int head=0;
        private int tail=0;

        public ArrayQueue(int capcity){
            this.items=new String[capcity];
            this.capcity=capcity;
        }

        /**
         * 入队
         * @param item
         * @return
         */
        public boolean enqueue(String item){
            if (head==0&&tail==capcity){
                /**
                 * 队列已满
                 */
                return false;
            }
            /**
             *  数据搬移，整理空间，否则前面被空出来了
             */
            if (tail==capcity){
                for (int i=head;i<capcity;i++){
                    items[i-head]=items[i];
                }
                // 搬移完之后重新更新head和tail
                tail-=head;
                head=0;

            }
            items[tail]=item;
            tail++;
            return true;
        }

        /**
         * 出队
         */
        public String dequeue(){
            if (tail==head) return null;
            String ret=items[head];
            head=(head+1)%capcity;
            return ret;
        }

    }

    /**
     * 循环队列
     */
    class CircularQueue{

        private String[] items;
        private int capcity=0;
        private int head=0;
        private int tail=0;

        public CircularQueue(int capcity){
            this.items=new String[capcity];
            this.capcity=capcity;
        }

        /**
         * 入队
         * @param item
         * @return
         */
        public boolean enqueue(String item){
            if ((tail+1)%capcity==head){
                /**
                 * 队列已满
                 */
                return false;
            }
            items[tail]=item;
            tail=(tail+1)%capcity;
            return true;
        }

        /**
         * 出队
         */
        public String dequeue(){
            if (tail==head) return null;
            String ret=items[head];
            head=(head+1)%capcity;
            return ret;
        }

    }
}
