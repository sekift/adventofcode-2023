package com.sekift.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day7 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day7");
        Map<String, Integer> fiveKind = new TreeMap<>();
        Map<String, Integer> fourKind = new TreeMap<>();
        Map<String, Integer> fullHouse = new TreeMap<>();
        Map<String, Integer> threeKind = new TreeMap<>();
        Map<String, Integer> twoPair = new TreeMap<>();
        Map<String, Integer> ownPair = new TreeMap<>();
        Map<String, Integer> highCard = new TreeMap<>();

        // 分配好位置
        for (String re : res) {
            String[] r = re.split(" ");
            String card = r[0].replace("A", "F")
                    .replace("K", "E")
                    .replace("Q", "D")
                    .replace("J", "C")
                    .replace("T", "B");
            Integer value = Integer.parseInt(r[1]);
            byte[] bys = card.getBytes();
            Map<Byte, Integer> map = new HashMap<>(8);
            for (byte b : bys) {
                int value1 = map.getOrDefault(b, 0);
                map.put(b, ++value1);
            }
            if (map.size() == 5) {
                highCard.put(card, value);
            } else if (map.size() == 4) {
                ownPair.put(card, value);
            } else if (map.size() == 2) {
                // 分布：4+1，3+2
                int multi = 1;
                for (int in : map.values()) {
                    multi *= in;
                }
                if (multi == 4) {
                    fourKind.put(card, value);
                } else {
                    fullHouse.put(card, value);
                }
            } else if (map.size() == 3) {
                // 分布：1+1+3，1+2+2
                int multi = 1;
                for (int in : map.values()) {
                    multi *= in;
                }
                if (multi == 4) {
                    twoPair.put(card, value);
                } else {
                    threeKind.put(card, value);
                }
            } else {
                fiveKind.put(card, value);
            }
        }

        int sum = 0;
        int index = 1;
        for (int value : highCard.values()) {
            sum += value * index;
            index++;
        }
        for (int value : ownPair.values()) {
            sum += value * index;
            index++;
        }
        for (int value : twoPair.values()) {
            sum += value * index;
            index++;
        }
        for (int value : threeKind.values()) {
            sum += value * index;
            index++;
        }
        for (int value : fullHouse.values()) {
            sum += value * index;
            index++;
        }
        for (int value : fourKind.values()) {
            sum += value * index;
            index++;
        }
        for (int value : fiveKind.values()) {
            sum += value * index;
            index++;
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day7");
        Map<String, Integer> fiveKind = new TreeMap<>();
        Map<String, Integer> fourKind = new TreeMap<>();
        Map<String, Integer> fullHouse = new TreeMap<>();
        Map<String, Integer> threeKind = new TreeMap<>();
        Map<String, Integer> twoPair = new TreeMap<>();
        Map<String, Integer> ownPair = new TreeMap<>();
        Map<String, Integer> highCard = new TreeMap<>();

        // 分配好位置
        for (String re : res) {
            String[] r = re.split(" ");
            String card = r[0].replace("A", "F")
                    .replace("K", "E")
                    .replace("Q", "D")
                    .replace("J", "1")
                    .replace("T", "B");
            Integer value = Integer.parseInt(r[1]);
            byte[] bys = card.getBytes();
            Map<Byte, Integer> map = new HashMap<>(8);
            for (byte b : bys) {
                int value1 = map.getOrDefault(b, 0);
                map.put(b, ++value1);
            }
            // 再平衡1
            // 取出除1最大的一个
            int max = 0;
            byte key = '0';
            int b1 = 0;
            for (byte bb : map.keySet()) {
                if (bb != '1') {
                    if (map.get(bb) > max) {
                        max = map.get(bb);
                        key = bb;
                    }
                } else {
                    b1 = map.get(bb);
                }
            }
            if (map.size()>1) {
                map.remove((byte) '1');
                map.put(key, map.get(key) + b1);
            }
            if (map.size() == 5) {
                highCard.put(card, value);
            } else if (map.size() == 4) {
                ownPair.put(card, value);
            } else if (map.size() == 2) {
                // 分布：4+1，3+2
                int multi = 1;
                for (int in : map.values()) {
                    multi *= in;
                }
                if (multi == 4) {
                    fourKind.put(card, value);
                } else {
                    fullHouse.put(card, value);
                }
            } else if (map.size() == 3) {
                // 分布：1+1+3，1+2+2
                int multi = 1;
                for (int in : map.values()) {
                    multi *= in;
                }
                if (multi == 4) {
                    twoPair.put(card, value);
                } else {
                    threeKind.put(card, value);
                }
            } else {
                fiveKind.put(card, value);
            }
        }

        int sum = 0;
        int index = 1;
        for (int value : highCard.values()) {
            sum += value * index;
            index++;
        }
        for (int value : ownPair.values()) {
            sum += value * index;
            index++;
        }
        for (int value : twoPair.values()) {
            sum += value * index;
            index++;
        }
        for (int value : threeKind.values()) {
            sum += value * index;
            index++;
        }
        for (int value : fullHouse.values()) {
            sum += value * index;
            index++;
        }
        for (int value : fourKind.values()) {
            sum += value * index;
            index++;
        }
        for (int value : fiveKind.values()) {
            sum += value * index;
            index++;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day7 day = new Day7();
        day.star1();
        day.star2();
    }
}
