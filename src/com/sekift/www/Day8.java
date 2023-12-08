package com.sekift.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day8");

        // 首先获取方向
        String[] rl = res.get(0).split("");
        Map<String, String> mapL = new HashMap<>(1024);
        Map<String, String> mapR = new HashMap<>(1024);
        int index = 0;
        for (String re : res) {
            if (index < 2) {
                index++;
                continue;
            }

            // 拆分RL
            String[] rearr1 = re.split("=");
            String key = rearr1[0].trim();
            String value1 = rearr1[1].trim().replace("(", "").replace(")", "").trim();
            String[] values = value1.split(",");
            String valueL = values[0].trim();
            String valueR = values[1].trim();
            mapL.put(key, valueL);
            mapR.put(key, valueR);
        }

        int sum = 0;
        boolean flag = false;
        String step = "AAA";
        // 一步一步走
        while (!flag) {
            for (String ele : rl) {
                //System.out.print(ele + " + " + step);
                if ("L".equals(ele)) {
                    sum++;
                    step = mapL.get(step);
                } else {
                    sum++;
                    step = mapR.get(step);
                }
                //System.out.print(" = " + step + "\n");
                if ("ZZZ".equals(step)) {
                    flag = true;
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day8");

        // 首先获取方向
        String[] rl = res.get(0).split("");
        Map<String, String> mapL = new HashMap<>(1024);
        Map<String, String> mapR = new HashMap<>(1024);
        int index = 0;
        for (String re : res) {
            if (index < 2) {
                index++;
                continue;
            }

            // 拆分RL
            String[] rearr1 = re.split("=");
            String key = rearr1[0].trim();
            String value1 = rearr1[1].trim().replace("(", "").replace(")", "").trim();
            String[] values = value1.split(",");
            String valueL = values[0].trim();
            String valueR = values[1].trim();
            mapL.put(key, valueL);
            mapR.put(key, valueR);
        }

        // 找出以A结尾的key
        List<String> lasta = new ArrayList<>();
        for (String key : mapL.keySet()) {
            if (key.endsWith("A")) {
                lasta.add(key);
            }
        }
        System.out.println(lasta);
        int len = lasta.size();

        String[] arr = lasta.toArray(new String[0]);
        // 一步一步走
        List<Long> countList = new ArrayList<>();
        for (String s : arr) {
            long sum = 0L;
            boolean flag = false;
            String step = s;
            while (!flag) {
                for (String ele : rl) {
                    if ("L".equals(ele)) {
                        sum++;
                        step = mapL.get(step);
                    } else {
                        sum++;
                        step = mapR.get(step);
                    }
                    if (step.endsWith("Z")) {
                        countList.add(sum);
                        flag = true;
                        break;
                    }
                }
            }
        }

        System.out.println(lcm(countList));
    }

    public static long lcm(List<Long> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("List should have at least two numbers.");
        }
        long result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }
        return result;
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Day8 day = new Day8();
        day.star1();
        day.star2();
    }
}
