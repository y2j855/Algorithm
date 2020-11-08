package com.tony.basis.class05;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/7 19:41
 * Description:
 * Trie(前缀树)
 */
public class Code02_TrieTree {

    /**
     * 前缀树对象，用数组存放子节点，只包含26个小写英文字母
     */
    public static class TrieByArray {
        private TrieNode root;

        public TrieByArray() {
            this.root = new TrieNode();
        }

        //创建结点对象
        private class TrieNode {
            private int pass = 0;
            private int end = 0;
            private TrieNode[] child;

            public TrieNode() {
                //定义26个小写字母，每个字母对应固定下标
                this.child = new TrieNode[26];
            }
        }

        //前缀树添加字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';  //判断字符应该在数组中的位置，对应成应该走哪条路
                if (node.child[path] == null) {
                    node.child[path] = new TrieNode();
                }
                node = node.child[path];
                node.pass++;
            }
            node.end++;
        }

        //前缀树查询字符串，返回这个字符串被加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.child[path] == null) {
                    return 0;
                }
                node = node.child[path];
            }
            return node.end;
        }

        /**
         * 前缀树删除字符串
         *
         * @param word 问题：如果删除的字符串下边有包含子节点怎么办？
         *             分成2种情况
         *             1.如果包含一个子节点，比如{"ab","abcde"}，删除"ab"。直接将从c开始剩下的子节点设置为空
         *             2.如果包含多个子节点，比如{"ab","abc","abd"},删除ab。因为b上的pass值为3，所以直接pass--就可以了，不用做特殊处理。
         */
        public void delete(String word) {
            //先判断要删除的字符串是否存在
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < chars.length; i++) {
                    path = chars[i] - 'a';
                    //如果子节点pass--为零,证明剩下的子节点已经无需存在，将值设置为空。
                    if (--node.child[path].pass == 0) {
                        node.child[path] = null;
                        return;
                    }
                    node = node.child[path];
                }
                node.end--;
            }
        }

        /**
         * 所有加入的字符串，有几个是以word为前缀开头的
         *
         * @param prefix
         * @return
         */
        public int prefixNumber(String prefix) {
            if (prefix == null) {
                return 0;
            }
            char[] chars = prefix.toCharArray();
            int path = 0;
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.child[path] == null) {
                    return 0;
                }
                node = node.child[path];
            }
            return node.pass;
        }


    }

    /**
     * 前缀树对象，使用Map存放子节点，这样解决了数组固定大小的问题
     */
    public static class TrieByMap {

        private TrieNode root;

        public TrieByMap() {
            root = new TrieNode();
        }

        private class TrieNode {

            private int pass;
            private int end;
            private Map<Integer, TrieNode> child;

            public TrieNode() {
                child = new HashMap<>();
            }

        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            int path = 0;
            TrieNode node = root;
            node.pass++;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i];
                if (!node.child.containsKey(path)) {
                    node.child.put(path, new TrieNode());
                }
                node = node.child.get(path);
                node.pass++;
            }
            node.end++;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i];
                if (!node.child.containsKey(path)) {
                    return 0;
                }
                node = node.child.get(path);
            }
            return node.end;
        }

        public int prefixNumber(String prefix) {
            if (prefix == null) {
                return 0;
            }
            char[] chars = prefix.toCharArray();
            TrieNode node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i];
                //必须这样写，否则计数器会报错
                if (!node.child.containsKey(path)) {
                    return 0;
                }
                node = node.child.get(path);
            }
            return node.pass;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < chars.length; i++) {
                    path = chars[i];
                    if (--node.child.get(path).pass == 0) {
                        node.child.remove(path);
                        return;
                    }
                    node = node.child.get(path);
                }
                node.end--;
            }
        }

    }

    /**
     * 对数器使用的仿照前缀树功能对象
     */
    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieByArray trie1 = new TrieByArray();
            TrieByMap trie2 = new TrieByMap();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}

