package com.weixin.publicnation;

import org.junit.Test;

public class Sort {

    /**
     * 1、冒泡排序
     */

    public void bubbleSort(int[] a){

        int length = a.length;
        if (length<1){
            return;
        }
        for (int i = 0; i < length; i++) {
            boolean flag=false;
            for (int j=0;j<length-i-1;j++){
                if (a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag=true;
                }
            }
            /**
             * 如果没有发生数据交换，则证明已经有序
             */
            if (!flag){
                break;
            }
        }

    }

    /**
     * 插入排序
     */

    public void insertionSort(int[] a){
        int length = a.length;
        if (length<1){
            return;
        }
        for (int i = 1; i < length; ++i) {
           int value=a[i];
           int j=i-1;
           for (;j>=0;j--){
               /**
                * 左边已经有序，小的在左边，所以如果右边比左边大，直接跳出循环
                */
               if (a[j]>value){
                   a[j+1]=a[j];
               }else {
                   break;
               }
           }
           a[j+1]=value;
        }
    }

    @Test
    public void testMerge(){
        int[] A={49,38,65,97,76,13,27};
        mergeSort(A);
        for (int i : A) {
            System.out.print(i+" ");
        }
    }

    /**
     * 归并排序
     * 分解子问题，从最小的子问题开始求解，然后归并
     *
     **/
    public void mergeSort(int[] A){
        mergeSorts(A,0,A.length-1);
    }

    public void mergeSorts(int[] A,int p,int r){
        if (p>=r){
            return;
        }
        int q=(p+r)/2;
        mergeSorts(A,p,q);
        mergeSorts(A,q+1,r);
        merge(A,p,q,r);
    }
    public void merge(int[] A,int  low,int mid,int high){
        int[] temp=new int[high-low+1];
        int i=low;
        int j=mid+1;
        int k=0;
        /**
         * 将两片区域合并成一片区域
         * 两片区域分别遍历，一对一比较大小，最后会有一边先放入到临时数组，将另一边剩下的全部放入临时数组
         * 再把临时数组值放回愿数组
         **/
        while (i<=mid&&j<=high){
            if (A[i]<=A[j]){
               temp[k++]=A[i++];
            }else {
                temp[k++]=A[j++];
            }
        }
        while (i<=mid){
            temp[k++]=A[i++];
        }
        while (j<=high){
            temp[k++]=A[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            A[x+low]=temp[x];
        }
    }

}
