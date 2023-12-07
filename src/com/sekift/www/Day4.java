package com.sekift.www;

import java.util.*;

public class Day4 {

    public void star1() {
        int sum = 0;
        List<String> res = InputPuzzle.readFileByLines("./day4");
        for (String line : res) {
            // 分割:
            String[] feige = line.split(":");
            String[] feige1 = feige[1].split("\\|");
            String[] winnings = feige1[0].trim().split(" ");
            String[] owns = feige1[1].trim().split(" ");
            int n = 0;
            Set<String> set = new HashSet<>();
            for (String win : winnings) {
                if (!"".equals(win)) {
                    set.add(win.trim());
                }
            }

            for (String str : owns) {
                if (set.contains(str)) {
                    n++;
                }
            }
//            System.out.println(n);
            sum += 2 << (n - 1);
        }
        System.out.println(sum / 2);
    }

    public void star2() {
        long sum = 0L;
        List<String> res = InputPuzzle.readFileByLines("./day4");
        Map<Integer, List<Integer>> map = new HashMap<>(256);

        Map<Integer, Integer> map1 = new HashMap<>(256);
        int index = 1;
        for (String line : res) {
            // 分割:
            String[] feige = line.split(":");
            String[] feige1 = feige[1].split("\\|");
            String[] winnings = feige1[0].trim().split(" ");
            String[] owns = feige1[1].trim().split(" ");
            int n = 0;
            Set<String> set = new HashSet<>();
            for (String win : winnings) {
                if (!"".equals(win)) {
                    set.add(win.trim());
                }
            }

            for (String str : owns) {
                if (set.contains(str)) {
                    n++;

                }
            }
            map1.put(index, n);
            index++;
        }
        //System.out.println(map1);

        // 再分配
        for (Integer key : map1.keySet()) {
            int value = map1.get(key);
            for (int i = 1; i <= value; i++) {
                List<Integer> list = map.getOrDefault(key + i, new ArrayList<>());
                list.add(key);
                map.put(key + i, list);
            }
        }
        //System.out.println(map);
        // 计算
        int[] arr = new int[res.size() + 1];
        Arrays.fill(arr, 1);
        for (Integer key : map.keySet()) {
            List<Integer> list = map.get(key);
            int sum1 = 1;
            for (Integer ele : list) {
                sum1 += arr[ele];
            }
            //System.out.println(key+";"+sum1);
            arr[key] = sum1;
        }
        for (int e : arr) {
            sum += e;
        }
        System.out.println(sum - 1);
    }

    public static void main(String[] args) {
        Day4 day = new Day4();
        day.star1();
        day.star2();
    }
}
