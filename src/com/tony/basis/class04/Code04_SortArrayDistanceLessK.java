package com.tony.basis.class04;

import java.util.PriorityQueue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/2 21:40
 * Description:
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * 请选择一个合适的排序策略，对这个数组进行排序。
 */
public class Code04_SortArrayDistanceLessK {

    public void sortByPriorityQueue(int[] array, int k) {
        /**
         * 1.获取k+1范围内的数组，用小根堆保存，这样array[0]就是这个范围内最小的值
         * 2.从k+1之后的下标位置的数，增加一个，获取一个最小值。
         */

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int index = 0;
        for (; index < Math.min(k + 1, array.length - 1); index++) {
            heap.add(array[index]);
        }
        int i = 0;
        for (; index < array.length; i++, index++) {
            heap.add(array[index]);
            array[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            array[i++] = heap.poll();
        }
    }

    //ToDo 自己实现的小顶堆/小根堆 不能实现这道算法题
    public void sortByMyMinHeap(int[] array, int k) {
        MyMinHeap heap = new MyMinHeap(k+1);
        int index = 0;
        for (; index < Math.min(k + 1, array.length - 1); index++) {
            heap.push(array[index]);
        }
        int i = 0;
        for (; index < array.length; i++, index++) {
            array[i] = heap.pop();
            heap.push(array[index]);
        }
        while (i < array.length) {
            array[i++] = heap.pop();
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,1,4,3,6,5,8,7,10,9,11,12};
        Code04_SortArrayDistanceLessK sort = new Code04_SortArrayDistanceLessK();
        sort.sortByMyMinHeap(arr,2);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
