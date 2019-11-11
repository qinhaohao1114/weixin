package com.weixin.publicnation;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
public class Linked {

    @Data
    @NoArgsConstructor
   static class ListNode{
        private String val;
        private ListNode next;
        public ListNode(String val){
            this.val=val;
        }
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode("a");
        ListNode node2=new ListNode("b");
        ListNode node3=new ListNode("c");
        ListNode node4=new ListNode("d");
        ListNode node5=new ListNode("e");
        ListNode node6=new ListNode("f");
        ListNode node7=new ListNode("e");
        ListNode node8=new ListNode("d");
        ListNode node9=new ListNode("c");
        ListNode node10=new ListNode("b");
        ListNode node11=new ListNode("a");
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node10);
        node10.setNext(node11);
        boolean huiWen = isHuiWen(node1);
        System.out.println(huiWen);
    }

    /**
     * 单链表反转
     * @param headNode
     */
    static void reverse(ListNode headNode){
        ListNode pre=null;
        ListNode nowHead=headNode;
        while (nowHead!=null&&nowHead.getNext()!=null){
            ListNode next = nowHead.getNext();
            nowHead.setNext(pre);
            pre=nowHead;
            nowHead=next;
        }
    }
    /**、\
     * 判断一个单链表存储的字符串是不是回文字符串
     * @param head
     * @return
     */
    static boolean isHuiWen(ListNode head){

        ListNode slow=head;
        ListNode fast=head;
        /**
         * 记录slow的前驱节点，用来反转链表，并且最后形成反转部分的链表头
         */
        ListNode pre=null;

        /**
         * 遍历链表，通过快慢指针找到中位节点，慢指针走一步快指针走两步，则当快指针走完时慢指针刚好走到中位点，偶数的时候处于下中位点，奇数的时候处于中位点
         *
         */
        while (fast!=null&&fast.getNext()!=null){
            fast=fast.getNext().getNext();
            ListNode next = slow.getNext();
            slow.setNext(pre);
            pre=slow;
            slow=next;
        }
        /**
         * 奇数的时候，slow往下移一位，跨过中位节点 a b c d e f e d c b a ，跳过f 比较 此时 slow 在f位置，所以下移到e位置开始比较
         *
         * 偶数的时候刚好处于下中位节点（处于第二个e的位置），直接比较即可a b c d e e d c b a ，直接从第二个e开始比较
         */
        if (fast!=null){
            slow=slow.getNext();
        }

        while (slow!=null){
            if (slow.getVal()!=pre.getVal()){
                return false;
            }
            slow=slow.getNext();
            pre=pre.getNext();
        }
        return true;
    }

    @Data
    static class LRU{
        private int capcity;
        private int useCapcity;
        private ListNode headNode;
        public LRU(int capcity){
            this.capcity=capcity;
        }
        public void add(ListNode node){
            if (useCapcity==0){
                headNode=node;
                capcity+=1;
                return;
            }
            boolean isHave = checkExsits(node);
            if (useCapcity==1&&isHave){
                return;
            }
            if (useCapcity<capcity){
                node.setNext(headNode);
                headNode=node;
                useCapcity++;
                return;
            }
            if (useCapcity==capcity){
                /**
                 * 移除最后一个节点，把当前节点放在首位
                 */

            }
        }

        private boolean checkExsits(ListNode node){
            ListNode temp=headNode;
            boolean have=false;
            ListNode pre=headNode;
            while (temp.getNext()!=null){
                if (temp.getVal().equals(node.getVal())){
                    have=true;
                    if (temp==pre){
                        /**
                         * 说明在首节点，不操作
                         */
                        break;
                    }else {
                        pre.setNext(temp.getNext());
                        useCapcity--;
                    }
                    break;
                }
                pre=temp;
                temp=temp.getNext();
            }
            return have;
        }

    }
}
