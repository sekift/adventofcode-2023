package com.sekift.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day12");

        // 先把符号分解为list，再按数量拼接#
        Map<Integer, List<String>> sign = new HashMap<>();
        Map<Integer, List<Integer>> signAmou = new HashMap<>();
        Map<Integer, List<Integer>> amou = new HashMap<>();
        int index = 1;
        for (String re : res) {
            String[] strs = re.split(" ");
            // part
            String[] sign1 = strs[0].split("\\.");
            String[] amou1 = strs[1].split(",");

            List<String> part1 = new ArrayList<>();
            List<Integer> part1Amou = new ArrayList<>();
            List<Integer> part2 = new ArrayList<>();

            for (String si : sign1) {
                if (!"".equals(si)) {
                    part1.add(si);
                    part1Amou.add(si.length());
                }
            }
            for (String ai : amou1) {
                int now = Integer.parseInt(ai);
                part2.add(now);
            }

            sign.put(index, part1);
            signAmou.put(index, part1Amou);
            amou.put(index, part2);

            index++;
        }
        for (int k = 1; k <= sign.size(); k++) {
            int cou1 = 0, cou2 = 0;
            for (Integer s1 : signAmou.get(k)) {
                cou1 += s1;
            }
            for (Integer s2 : amou.get(k)) {
                cou2 += s2;
            }
            System.out.println(sign.get(k) + " ; " + signAmou.get(k) + " : " + amou.get(k) + " ; " + cou1 + " : " + cou2);
        }

    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day12");
    }

    public static void main(String[] args) {
        Day12 day = new Day12();
        day.star1();
        day.star2();
    }
}
