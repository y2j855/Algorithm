package com.tony.basis.class04;

import java.util.Arrays;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/2 11:37
 * Description: 小根堆(小顶堆)
 */
public class MyMinHeap {
    private int[] heap;
    private final int limit;
    private int heapSize;

    public MyMinHeap(int limit) {
        heap = new int[limit];
        this.limit = limit;
    }

    /**
     * 添加元素
     *
     * @param value
     */
    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("the heap full");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }


    /**
     * 弹出当前堆中最小值,但不删除元素
     *
     * @return
     */

    public int peek() {
        int value = heap[0];
        swap(heap, 0, heapSize - 1);
        heapify(heap, 0, heapSize);
        return value;
    }

    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("heap is empty");
        }
        int value = heap[0];
        swap(heap, 0, heapSize - 2);
//        heapify(heap, 0, heapSize);
        heapSize--;
        return value;
    }

    /**
     * 添加新元素
     * 进行上浮操作
     *
     * @param array
     * @param index
     */
    private void heapInsert(int[] array, int index) {
        while (array[index] < array[(index - 1) / 2]) {
            swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 进行下沉操作
     *
     * @param array
     * @param index
     * @param heapSize
     */
    private void heapify(int[] array, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //先比较左右节点
            int smallest = left + 1 < heapSize && array[left + 1] < array[left] ? left + 1 : left;
            //然后和父节点进行比较
            smallest = array[smallest] < array[index] ? smallest : index;
            //如果index没有发生变化直接退出
            if (index == smallest) {
                break;
            }
            //交换位置
            swap(array, index, smallest);
            //继续往下遍历
            index = smallest;
            left = index * 2 + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 额外空间复杂度O(1)
     * 堆排序
     *
     * @param arr
     */
    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        for (int i = 0; i < arr.length; i++) {  //O(n)
//            heapInsert(arr, i); //O(logn)
//        }

        int heapSize = arr.length;
        //等比数列，时间复杂度为O(n)
        for (int i = heapSize - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }

        swap(arr, 0, --heapSize);
        while (heapSize > 0) { //O(n)
            heapify(arr, 0, heapSize);//O(logn)
            swap(arr, 0, --heapSize);
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }
}
