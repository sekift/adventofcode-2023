package com.sekift.www;

import java.util.*;

public class Day15 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day15");
        // 只有一行
        String row = res.get(0);
        // 按,划分
        String[] arr = row.split(",");
        int sum = 0;
        for (String str : arr) {
            int count = 0;
            char[] chs = str.toCharArray();
            for (char ch : chs) {
                int now = (count + ch) * 17;
                count = now % 256;
            }
            sum += count;
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day15");
        // 只有一行
        String row = res.get(0);
        // 按,划分
        String[] arr = row.split(",");

        Map<Integer, List<Map<String, Integer>>> map = new HashMap<>(256);
        int sum = 0;
        for (String str : arr) {
            // 区分=还是-做不同的操作
            char[] chs = {};
            String key = "";
            int value = -1;
            if (str.contains("=")) {
                key = str.split("=")[0];
                value = Integer.parseInt(str.split("=")[1]);
                chs = str.split("=")[0].toCharArray();
            } else {
                chs = str.split("-")[0].toCharArray();
                key = str.split("-")[0];
            }

            int hash = 0;
            for (char ch : chs) {
                int now = (hash + ch) * 17;
                hash = now % 256;
            }
            // count就是第n个箱子
            List<Map<String, Integer>> nowList = map.getOrDefault(hash, new ArrayList<>());

            // value大于0表示增加或更改，小于0表示删除key
            if (value < 0) {
                // 删除
                Iterator<Map<String, Integer>> it = nowList.listIterator();
                while (it.hasNext()) {
                    Map<String, Integer> nowMap = it.next();
                    if (nowMap.containsKey(key)) {
                        nowList.remove(nowMap);
                        break;
                    }
                }
                map.put(hash, nowList);
            } else {
                // 增加或更换
                if (nowList.size() == 0) {
                    Map<String, Integer> nowMap = new HashMap<>(2);
                    nowMap.put(key, value);
                    nowList.add(nowMap);
                    map.put(hash, nowList);
                } else {
                    List<Map<String, Integer>> newList = new ArrayList<>();
                    boolean flag = true;
                    for (Map<String, Integer> nowMap : nowList) {
                        if (nowMap.containsKey(key)) {
                            nowMap.put(key, value);
                            newList.add(nowMap);
                            flag = false;
                        } else {
                            newList.add(nowMap);
                        }
                    }
                    if (flag) {
                        Map<String, Integer> nowMap = new HashMap<>(2);
                        nowMap.put(key, value);
                        newList.add(nowMap);
                    }
                    map.put(hash, newList);
                }
            }
        }

        System.out.println(map);
        // 计算结果
        for (int key : map.keySet()) {
            int box = key + 1;
            List<Map<String, Integer>> list = map.get(key);
            int slot = 1;
            for (Map<String, Integer> map1 : list) {
                for (String key1 : map1.keySet()) {
                    int value = map1.get(key1);
                    sum += (box * slot * value);
                }
                slot++;
            }

        }
    }

    public static void main(String[] args) {
        Day15 day = new Day15();
        day.star1();
        day.star2();
    }
}
