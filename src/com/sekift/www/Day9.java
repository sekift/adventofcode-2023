package com.sekift.www;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day9");
        long sum = 0;
        for (String re : res) {
            List<List<Integer>> list = new ArrayList<>();
            String[] strs = re.split(" ");
            List<Integer> list1 = new ArrayList<>();
            for (String str : strs) {
                list1.add(Integer.parseInt(str));
            }
            list.add(list1);
            // 减
            Set<Integer> set = new HashSet<>();
            List<Integer> list2 = new ArrayList<>();
            for (int k = 0; k < 20; k++) {
                for (int i = 0; i < list1.size() - 1; i++) {
                    int diff = list1.get(i + 1) - list1.get(i);
                    list2.add(diff);
                    set.add(diff);
                }
                if (set.size() == 1) {
                    list.add(list2);
                    break;
                }
                set.clear();
                list.add(list2);
                list1 = list2;
                list2 = new ArrayList<>();

            }

            // 加
            List<Integer> last = list.get(list.size() - 1);
            int cou = last.get(0);
            for (int m = list.size() - 1; m > 0; m--) {
                List<Integer> last2 = list.get(m - 1);
                cou += last2.get(last2.size() - 1);
            }
            sum += cou;
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day9");
        long sum = 0;
        for (String re : res) {
            List<List<Integer>> list = new ArrayList<>();
            String[] strs = re.split(" ");
            List<Integer> list1 = new ArrayList<>();
            for (String str : strs) {
                list1.add(Integer.parseInt(str));
            }
            list.add(list1);
            // 减
            Set<Integer> set = new HashSet<>();
            List<Integer> list2 = new ArrayList<>();
            for (int k = 0; k < 20; k++) {
                for (int i = 0; i < list1.size() - 1; i++) {
                    int diff = list1.get(i + 1) - list1.get(i);
                    list2.add(diff);
                    set.add(diff);
                }
                if (set.size() == 1) {
                    list.add(list2);
                    break;
                }
                set.clear();
                list.add(list2);
                list1 = list2;
                list2 = new ArrayList<>();

            }

            // 加
            List<Integer> last = list.get(list.size() - 1);
            int cou = last.get(0);
            for (int m = list.size() - 1; m > 0; m--) {
                List<Integer> last2 = list.get(m - 1);
                cou = last2.get(0) - cou;
            }
            sum += cou;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day9 day = new Day9();
        day.star1();
        day.star2();
    }
}
