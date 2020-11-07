package com.tony.basis.class04;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author: Tony.Chen
 * Create Time : 2020/10/31 10:39
 * Description:
 * 根据业务实现自己的heap，保证如果修改已存放到堆中对象的值，堆还是能够正确排序
 */
public class Code02_Heap02 {

    public static class MyMaxHeap<T> {
        private T[] heap;
        private Map<T, Integer> indexMap;
        private int heapSize;
        //? super T 类型下界，表示参数化的类型可能是所指定的类型，或者是此类型的父类型，直接Object
        private Comparator<? super T> comparator;

        public MyMaxHeap(Comparator<? super T> comparator) {
            this.heap = (T[]) new Object[10];
            this.indexMap = new HashMap<>();
            this.comparator = comparator;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T key) {
            return indexMap.containsKey(key);
        }

        /**
         * 添加元素
         * 1.将元素添加到数组尾部
         * 2.记录元素的位置
         * 3.将新增元素变为大根堆
         *
         * @param value
         */
        public void push(T value) {
            heap[heapSize] = value;
            indexMap.put(value, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T value = heap[0];
            swap(0, --heapSize);
            heapify(0, heapSize);
            indexMap.remove(value);
            return value;
        }

        /**
         * 添加元素
         *
         * @param index
         */
        private void heapInsert(int index) {
            while (comparator.compare(heap[index], heap[(index - 1) / 2]) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 保证剩余元素还是大根堆
         *
         * @param index
         * @param heapSize
         */
        private void heapify(int index, int heapSize) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                int largest = leftIndex + 1 < heapSize && (comparator.compare(heap[leftIndex + 1], heap[leftIndex]) < 0)
                        ? leftIndex + 1
                        : leftIndex;
                largest = comparator.compare(heap[largest], heap[index]) < 0 ? largest : index;
                //如果父节点大，停止循环
                if (largest == index) {
                    break;
                }
                swap(largest, index);
                //再往堆下层遍历
                index = largest;
                leftIndex = index * 2 + 1;
            }
        }

        /**
         * 当元素的大小变化时，更新元素的位置
         * @param value
         */
        public void resign(T value){
            int index = indexMap.get(value);
            heapInsert(index);
            heapify(index,heapSize);
        }

        private void swap(int i, int j) {
            T temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;

            indexMap.put(heap[i],i);
            indexMap.put(heap[j],j);
        }
    }

    public static class Student {
        public int classNo;
        public int age;
        public int id;

        public Student(int c, int a, int i) {
            classNo = c;
            age = a;
            id = i;
        }

    }

    /**
     * 对象比较器
     */
    public static class StudentComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student(2, 50, 11111);
        Student s2 = new Student(1, 60, 22222);
        Student s3 = new Student(6, 10, 33333);
        Student s4 = new Student(3, 20, 44444);
        Student s5 = new Student(7, 72, 55555);
        Student s6 = new Student(1, 14, 66666);

        StudentComparator comparator = new StudentComparator();


        PriorityQueue<Student> heap = new PriorityQueue<>(new StudentComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);

        s2.age = 6;
        s4.age = 12;
        s5.age = 10;
        s6.age = 84;

        while (!heap.isEmpty()) {
            Student cur = heap.poll();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        MyMaxHeap<Student> myHeap = new MyMaxHeap<>(new StudentComparator());

        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);
        myHeap.push(s6);

        s2.age = 6;
        myHeap.resign(s2);
        s4.age = 12;
        myHeap.resign(s4);
        s5.age = 10;
        myHeap.resign(s5);
        s6.age = 84;
        myHeap.resign(s6);

        while (!myHeap.isEmpty()) {
            Student cur = myHeap.pop();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }
    }

}
