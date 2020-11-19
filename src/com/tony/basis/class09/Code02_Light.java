package com.tony.basis.class09;

import java.util.HashSet;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/19 20:42
 * Description: 贪心算法
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮，返回如果点亮str中所有需要点亮的位置，至少需要几盏灯。
 */
public class Code02_Light {

    public static int minLight(String road) {
        if (road == null || road.toCharArray().length == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<Integer>());
    }

    // data[index....]位置，自由选择放灯还是不放灯
    // data[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有'.'的方案，并且在这些有效的方案中，返回最少需要几个灯
    private static int process(char[] data, int index, HashSet<Integer> lights) {
        //如果整个字符数组遍历完了
        if (index == data.length) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] != 'X') {
                    //如果当前值为'.'，但它的上一个，下一个，和它本身都不是灯，证明不符合条件要求直接返回
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            int no = process(data, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (data[index] == '.') {
                lights.add(index);
                yes = process(data, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    /**
     * 使用贪心算法
     * 贪心策略
     * 1.如果i位置是'X'，不放灯，i = i+1
     * 2.如果i位置是'.'
     * 如果i+1是’X‘，i位置必须放灯
     * 如果i+1是’.‘,不管i+2是什么，i+1位置放灯
     * @param road
     * @return
     */
    public static int minLightUseGreedy(String road) {
        if (road == null || road.toCharArray().length == 0) {
            return 0;
        }
        char[] chars = road.toCharArray();
        int index = 0;
        int lights = 0;

        while (index < chars.length) {
            if (chars[index] == 'X') {
                index++;
            } else {
                lights++;
                if (index + 1 == chars.length) {
                    break;
                } else {
                    if (chars[index + 1] == 'X') {
                        index = index + 2;
                    } else {
                        index = index + 3;
                    }
                }
            }
        }
        return lights;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight(test);
            int ans2 = minLightUseGreedy(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
