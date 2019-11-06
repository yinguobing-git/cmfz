package com.baizhi;

import org.junit.jupiter.api.Test;

import java.util.*;

public class NewTest {
    @Test
    public void test1() {
        for (int i = 0; i < 40; i++) {
            int s = (int) (Math.random() * 9000) + 1000;
            String str = "";
            str = s + "";
            System.out.println(str);
        }
    }

    @Test
    public void test2() {
        String s = "fghfgsdhfdgjfghdjdfghjkghfjkfghjsrtrtygweryge5wqtgre";
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int a = 0; a < chars.length; a++) {
            char c = chars[a];
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        Set<Character> set = map.keySet();
        for (Character d : set) {
            System.out.println(d + ":" + map.get(d));
        }
    }

    @Test
    public void test3() {
        //99乘法表
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + j * i + "     ");
            }
            System.out.println();
        }
    }

    @Test
    public void test4() {
        List<String> num = new ArrayList<String>();
        num.add("4");
        num.add("6");
        num.add("3");
        num.add("1");
        num.add("5");
        num.add("2");

        List<String> list = new ArrayList<>();
        String s = "";
        for (int i = 0; i < num.size(); i++) {
            s += num.get(i);
        }
        char[] chars = s.toCharArray();
        /*System.out.println("-----------------------------");
        java.util.Arrays.sort(chars);
        for (int i=0;i<chars.length;i++){
            System.out.println(chars[i]);
        }*/

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] > chars[i]) {
                    char min = chars[i];
                    chars[i] = chars[j];
                    chars[j] = min;
                }
            }
            System.out.println(chars[i] + "--------------");
        }
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
    }

    @Test
    public void test5() {
        Float a = 3.4f;
        Long d = 3L;
        Double c = 3.14;
    }
}
