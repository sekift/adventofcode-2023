package com.sekift.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day19");
        // 两个list，用""分割
        Map<String, String> map = new HashMap<>(128);
        List<Map<String, Integer>> list = new ArrayList<>();

        int index = 1;
        for (String re : res) {
            if (index == 1) {
                if ("".equals(re)) {
                    index++;
                    continue;
                }
                re = re.replace("}", "");
                String[] strs = re.split("\\{");
                map.put(strs[0], strs[1]);
            } else {
                Map<String, Integer> maptemp = new HashMap<>(128);
                re = re.replace("{", "").replace("}", "");
                String[] strs = re.split(",");
                for (String str : strs) {
                    String[] sts = str.split("=");
                    maptemp.put(sts[0], Integer.parseInt(sts[1]));
                }
                list.add(maptemp);
            }
        }
        System.out.println(map);
        System.out.println(list);

        int sum = 0;
        // 循环取list的Map，再从Map的in对比起，一直判断到R/A，A的amsx累加起来到sum
        for (Map<String, Integer> temp : list) {
            String key = "in";
            while (!"R".equals(key) && !"A".equals(key)) {
                String[] value = map.get(key).split(",");
                int len = value.length;
                for (String val : value) {
                    if (!val.contains(":")) {
                        key = val;
                    } else {
                        // 再split
                        String[] vals = val.split(":");
                        String nowkey = vals[1];
                        String mapkey = vals[0].substring(0, 1);
                        String judge = vals[0].substring(1, 2);
                        int number = Integer.parseInt(vals[0].substring(2));
                        int leftnum = temp.get(mapkey);
                        boolean flag = false;
                        if ("<".equals(judge)) {
                            if (leftnum < number) {
                                flag = true;
                            }
                        } else if (">".equals(judge)) {
                            if (leftnum > number) {
                                flag = true;
                            }
                        }
                        if (flag) {
                            key = nowkey;
                            break;
                        }
                    }
                }
            }
            if ("A".equals(key)) {
                sum += (temp.get("a") + temp.get("m") + temp.get("s") + temp.get("x"));
            }
        }
        System.out.println(sum);

    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day19");
    }

    public static void main(String[] args) {
        Day19 day = new Day19();
        day.star1();
        day.star2();
    }
}
