package com.tony.basis.class09;

import java.util.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/19 11:59
 * Description: 贪心算法实现字典序排序
 * 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果。
 */
public class Code01_lowestDictionaryOrder {

    /**
     * 暴力递归的方式返回字典序中最小的结果
     * @param data
     * @return
     */
    public static String getlowestString(String[] data){
        Set<Integer> index = new HashSet<>();
        List<String> all = new ArrayList<>();
        process(data, index,"",all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if(lowest.compareTo(all.get(i)) > 0){
                lowest = all.get(i);
            }
        }
        return lowest;
    }

    /**
     * 深度递归遍历，获取全序集。
     * 就是一个字符串数组，将所有的排列组合打印出来
     * 例子：["s","k","x"] 输出：skx,sxk,ksx,kxs,xsk,xks
     * @param data
     * @param useIndex
     * @param path
     * @param all
     */
    private static void process(String[] data, Set useIndex,String path,List<String> all) {
        if(useIndex.size() == data.length){
            all.add(path);
        }else {
            for (int i = 0; i < data.length; i++) {
                if (!useIndex.contains(i)) {
                    useIndex.add(i);
                    process(data, useIndex,path+data[i],all);
                    useIndex.remove(i);
                }
            }
        }
    }

    /**
     * 使用贪心算法，获取字典序最小全序集
     * 已证明有传递性
     * a.b <= b.a
     * b.c <= c.b
     * a.c <= c.a
     * 证明过程比较复杂且费时，我们不可能每次都证明。
     * 所以应该先写一个暴力的方法保证正确，再写贪心算法，如果和暴力的结果一致就证明此方法可用。
     * 省去了大量证明的时间
     * @param data
     * @return
     */
    public static String lowestStringUseGreedy(String[] data){
        if(data == null || data.length == 0){
            return "";
        }
        Arrays.sort(data, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        String result = "";
        for (int i = 0; i < data.length; i++) {
            result += data[i];
        }
        return result;
    }

    //对数器测试方法
    public static String generateRandomString(int strLenght){
        char[] chars = new char[(int) (Math.random() * strLenght) + 1];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (97 + (int)Math.random() * 25);
        }
        return String.valueOf(chars);
    }

    public static String[] generateRandomArray(int arryLength,int strLength){
        String[] array = new String[(int) (Math.random() * arryLength) + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateRandomString(strLength);
        }
        return array;
    }

    public static String[] copyArray(String[] array){
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomArray(arrLen,strLen);
            String[] arr2 = copyArray(arr1);
            if(!getlowestString(arr1).equals(lowestStringUseGreedy(arr2))){
                System.out.println("Ooops!");
            }
        }
        System.out.println("finish!");
    }

}
