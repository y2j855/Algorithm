package com.tony.basis.class04;

/**
 * 大根堆实现
 *
 * @author chenhao
 */
public class MyMaxHeap {
    private int[] heap;
    private final int limit;
    private int heapSize = 0;

    public MyMaxHeap(int limit) {
        heap = new int[limit];
        this.limit = limit;
    }

    /**
     * 大根堆添加元素
     *
     * @param value
     */
    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    /**
     * 大根堆弹出最大元素，并保证剩余元素还是大根堆
     *
     * @return
     */
    public int pop() {
        //堆中最大值
        int value = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return value;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    /**
     * 添加元素
     * 进行上浮操作
     *
     * @param arr
     * @param index
     */
    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 进行下沉操作
     * 删除最大值，保证剩余元素还是大根堆
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    private void heapify(int[] arr, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            //如果有右节点并且右节点大于左节点，返回右节点index，否则返回左节点index
            int largest = leftIndex + 1 < heapSize && arr[leftIndex + 1] > arr[leftIndex] ? leftIndex + 1 : leftIndex;
            //父节点与左右节点大的只进行比较，如果父节点小，交换彼此位置
            largest = arr[index] < arr[largest] ? largest : index;
            //如果父节点大，停止循环
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            //再往堆下层遍历
            index = largest;
            leftIndex = index * 2 + 1;
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
            heapify(arr,i,heapSize);
        }

        swap(arr, 0, --heapSize);
        while (heapSize > 0) { //O(n)
            heapify(arr, 0, heapSize);//O(logn)
            swap(arr, 0, --heapSize);
        }
    }
}