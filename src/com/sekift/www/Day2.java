package com.sekift.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    public void star1() {
        String[] letters = {"red", "green", "blue"};
        int[] digits = {12, 13, 14};

        List<String> res = InputPuzzle.readFileByLines("./day2");
        int sum = 0;

        for (String ele : res) {
            // 划分game与color
            String[] str1 = ele.split(":");
            String gameIdStr = str1[0].split(" ")[1];

            // bags
            String[] bags = str1[1].split(";");
            boolean flag = true;
            for (String bag : bags) {
                // subbags
                String[] subbags = bag.split(",");
                for (String subbag : subbags) {
                    subbag = subbag.trim();
                    int i = 0;
                    for (String lett : letters) {
                        if (subbag.endsWith(lett)) {
                            int num = Integer.parseInt(subbag.split(" ")[0]);
                            if (num > digits[i]) {
                                flag = false;
                                break;
                            }
                        }
                        i++;
                    }
                    if (!flag) {
                        break;
                    }
                }
            }
            if (flag) {
                sum += Integer.parseInt(gameIdStr);
            }
        }
        System.out.println(sum);
    }

    public void star2() {

        List<String> res = InputPuzzle.readFileByLines("./day2");
        int sum = 0;
        for (String ele : res) {
            Map<String, Integer> map = new HashMap<>(4);
            // 划分game与color
            String str1 = ele.split(":")[1];
            // bags
            str1 = str1.replace(";", ",");
            String[] bags = str1.split(",");
            for (String bag : bags) {
                bag = bag.trim();
                String[] subbag = bag.split(" ");
                int sub0 = Integer.parseInt(subbag[0]);
                String sub1 = subbag[1];
                if (map.containsKey(sub1)) {
                    int value = map.get(sub1);
                    if (sub0 > value) {
                        map.put(sub1, sub0);
                    }
                } else {
                    map.put(sub1, sub0);
                }
            }

            int multi = 1;
            for (int num : map.values()) {
                multi *= num;
            }

            sum += multi;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day2 day = new Day2();
        day.star1();
        day.star2();
    }
}
