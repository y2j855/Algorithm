package com.tony.basis.class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/22 11:52
 * Description:并查集面试题
 * 一个数组，装了很多的学生，如果学生身份证相等或账号相等或github相等，视为一个学生
 * 请问数组中有多少个学生
 * 逻辑思路
 * 1.将数组中所有学生放到并查集里
 * 2.建立三个Map，分别存学生的三个属性
 * 3.当某个属性相等时，将并查集里的对象合并，最后并查集的大小就是学生个数
 */
public class Code07_MergeStudent {
    private static class Student{
        private String cardId;
        private String accountId;
        private String githubId;

        public Student(String cardId, String accountId, String githubId) {
            this.cardId = cardId;
            this.accountId = accountId;
            this.githubId = githubId;
        }
    }

    public static int studentNumber(List<Student> students){
        if(students == null || students.size()==0){
            return 0;
        }

        Map<String,Student> cardIdMap = new HashMap<>();
        Map<String,Student> accountIdMap = new HashMap<>();
        Map<String,Student> githubIdMap = new HashMap<>();

        UnionSet<Student> set = new UnionSet<>(students);

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if(!cardIdMap.containsKey(student.cardId)){
                cardIdMap.put(student.cardId,student);
            }else{
                set.union(cardIdMap.get(student.cardId),student);
            }
            if(!accountIdMap.containsKey(student.accountId)){
                accountIdMap.put(student.accountId,student);
            }else{
                set.union(accountIdMap.get(student.accountId),student);
            }
            if(!githubIdMap.containsKey(student.githubId)){
                githubIdMap.put(student.githubId,student);
            }else{
                set.union(githubIdMap.get(student.githubId),student);
            }
        }
        return set.getHeadSize();
    }

    public static void main(String[] args) {
        Student s1 = new Student("1","1","1");
        Student s2 = new Student("2","1","2");
        Student s3 = new Student("3","3","2");
        Student s4 = new Student("4","4","4");
        Student s5 = new Student("5","5","5");
        Student s6 = new Student("6","6","4");

        List<Student> array = new ArrayList<>();
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);
        array.add(s5);
        array.add(s6);

        System.out.println(studentNumber(array));

    }
}
