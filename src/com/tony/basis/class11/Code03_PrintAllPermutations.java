package com.tony.basis.class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/28 15:12
 * Description:
 * 打印一个字符串的全部排列
 */
public class Code03_PrintAllPermutations {

    /**
     * 打印一个字符串的全部排列
     *
     * @param str
     * @return
     */
    public static List<String> permutations(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        process1(chars, 0, result);
        return result;
    }

    /**
     * 通过另外容器实现字符串全排序
     * @param str
     * @return
     */
    private static List<String> permutationsSimple(String str) {
        char[] chars = str.toCharArray();
        Set<Integer> index = new HashSet<>();
        List<String> all = new ArrayList<>();
        process3(chars, index,"",all);
        return all;
    }

    /**
     * 深度优先遍历，通过set记录数组下标
     * @param data
     * @param useIndex
     * @param path
     * @param all
     */
    private static void process3(char[] data, Set useIndex, String path, List<String> all) {
        if(useIndex.size() == data.length){
            all.add(path);
        }else {
            for (int i = 0; i < data.length; i++) {
                if (!useIndex.contains(i)) {
                    useIndex.add(i);
                    process3(data, useIndex,path+data[i],all);
                    useIndex.remove(i);
                }
            }
        }
    }

    /**
     * @param str
     * @return
     */
    public static List<String> permutationsNoRepeat(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<>();
        process2(chars, 0, result);
        return result;
    }

    /**
     * 深度优先遍历实现字符串的全部排列
     * {a,b,c}
     * 1.定义index，数组下标。来控制数组总交换的可能
     * 2.从数组下标0位置开始交换，0~2,1~2,2~2。一共6中可能性。这样所有的排序可能性都做到了。
     * 3.因为是深度优先遍历，所以交换完成后我们需要再交换回去
     *
     * @param chars
     * @param index
     * @param result
     */
    private static void process1(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(String.valueOf(chars));
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process1(chars, index + 1, result);
            swap(chars, index, i);
        }
    }

    /**
     * 打印一个字符串的全部排列去重
     * 利用分支界限法去重
     * 以前我们是按照将结果都统计好后，然后再进行去重。
     * 按照分支界限法，我们让重复的递归分支直接不进行执行，这样更好的提高性能。
     *
     * 利用boolean数组记录每个字符是否进行过递归分支，如果进过就不在重复进
     * 局限是只支持小写26个字母。可用Map来替代。
     *
     * @param chars
     * @param index
     * @param result
     */
    private static void process2(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(String.valueOf(chars));
        }
        boolean[] visit = new boolean[26];
        for (int i = index; i < chars.length; i++) {
            if (!visit[chars[i] - 'a']) {
                visit[chars[i] - 'a'] = true;
                swap(chars, index, i);
                process2(chars, index + 1, result);
                swap(chars, index, i);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        List<String> s1 = permutations("aaa");
        for (String str : s1) {
            System.out.println(str);
        }
        System.out.println("===============");
        List<String> s2 = permutationsNoRepeat("aaa");
        for (String str : s2) {
            System.out.println(str);
        }
        System.out.println("==============");
        List<String> s3 = permutationsSimple("abc");
        for (String str : s3) {
            System.out.println(str);
        }
    }

}
