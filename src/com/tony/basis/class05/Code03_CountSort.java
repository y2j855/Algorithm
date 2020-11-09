package com.tony.basis.class05;

import java.util.Arrays;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/8 20:38
 * Description: 计数排序（桶排序）
 * 不基于比较的排序，但是要求数据范围
 */
public class Code03_CountSort {

    private static class Student {
        private String name;
        private int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }

    /**
     * 1.统计数组长度：(数组最大值-数组最小值+1)
     * 2.数组最小值作为一个偏移量，用于统计数组的对号入座，也就是下标。
     * 3.插入原数组，用偏移量加下标的方式放入数值
     *
     * @param array
     */
    public static void countSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        //获取数组中最大值和最小值
        int[] arrayMaxMixValue = getMaxMinValue(array);

        //统计数组
        int[] countArray = new int[arrayMaxMixValue[1] - arrayMaxMixValue[0] + 1];

        //给统计数组赋值，用需要排序的数组值减去偏移量得到每个值的位置，如有重复进行累加操作
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - arrayMaxMixValue[0]]++;
        }

        //将统计数组的值还原到被排序的数组中，得到的新值就是排序好的值
        //用偏移量加上统计数组的位置得到对应的值
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i]-- > 0) {
                array[index++] = i + arrayMaxMixValue[0];
            }
        }
    }

    /**
     * 针对引用类型的对象
     * 以学生对象为例，进行排序时如果有相同成绩的要求稳定性
     * @param array
     */
    public static void countSort(Student[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        //获取数组中最大值和最小值
        int[] arrayMaxMixValue = getMaxMinValue(array);
        //初始化统计数组
        int[] countArray = new int[arrayMaxMixValue[1] - arrayMaxMixValue[0] + 1];
        //给统计数组赋值，用需要排序的数组值减去偏移量得到每个值的位置，如有重复进行累加操作
        for (int i = 0; i < array.length; i++) {
            countArray[array[i].grade - arrayMaxMixValue[0]]++;
        }
        //统计数组做变形，后面的元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }
        //倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组中
        Student[] sortedArray = new Student[array.length];
        for (int i = sortedArray.length - 1; i >=0 ; i--) {
            //-1是因为算出来的值是位置，-1是下标
            sortedArray[countArray[array[i].grade - arrayMaxMixValue[0]] -1 ] = array[i];
            //如果有重复--
            countArray[array[i].grade-arrayMaxMixValue[0]]--;
        }
        System.out.println(Arrays.toString(sortedArray));
    }

    private static int[] getMaxMinValue(int[] array) {
        int[] value = new int[2];
        int min = array[0];
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        value[0] = min;
        value[1] = max;
        return value;
    }

    private static int[] getMaxMinValue(Student[] array) {
        int[] value = new int[2];
        int min = array[0].grade;
        int max = array[0].grade;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i].grade) {
                max = array[i].grade;
            }
            if (min > array[i].grade) {
                min = array[i].grade;
            }
        }
        value[0] = min;
        value[1] = max;
        return value;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            countSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        countSort(arr);
        printArray(arr);

        /**
         *  针对学生成绩排序，要求排序要有稳定性。比如：如果张三和李四成绩都是90，如果张三在第二名，李四在第三名。
         *  无论怎么排序他们还在这个位置。
         */
        Student s1 = new Student("小明", 99);
        Student s2 = new Student("张三", 96);
        Student s3 = new Student("李四", 96);
        Student s4 = new Student("小黑", 89);
        Student s5 = new Student("小鹏", 93);
        Student s6 = new Student("大勇", 68);
        Student s7 = new Student("小红", 55);
        Student s8 = new Student("艾希", 75);
        Student s9 = new Student("盖伦", 82);
        Student s10 = new Student("赵四", 69);

        Student[] array = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
        System.out.println(Arrays.toString(array));
        countSort(array);

    }
}
