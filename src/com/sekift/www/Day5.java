package com.sekift.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day5");
        // 种子数组
        List<Long> seeds = new ArrayList<>();
        // 第一行是种子
        String se1 = res.get(0).split(":")[1].trim();
        String[] searr = se1.split(" ");
        for (String a : searr) {
            seeds.add(Long.parseLong(a.trim()));
        }

        long min = Long.MAX_VALUE;
        for (long se : seeds) {
            long resmin = this.calcu(se, se + 1, res);
            min = Math.min(resmin, min);
        }
        System.out.println("min= " + min);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day5");
        // 种子map
        Map<Long, Long> map = new HashMap<>(16);

        // 第一行是种子
        String se1 = res.get(0).split(":")[1].trim();
        String[] searr = se1.split(" ");
        for (int i = 0; i < searr.length - 1; i++) {
            map.put(Long.parseLong(searr[i]), Long.parseLong(searr[++i]));
        }

        // 再以1000000分割各数列，然后各自获取对应的值
        int rang = 1000000;
        List<Long> list1 = new ArrayList<>();
        for (Long key : map.keySet()) {
            long a = key;
            long b = key + map.get(key);
            int j = 0;
            while (a + j * rang < b) {
                list1.add(a + j * rang);
                j++;
            }
            list1.add(b);
        }
        // 得到这一批的对应值，很明显如果是上升的段即要抛弃(用二分法更优)
        Map<Long, Long> map1 = new HashMap<>(1024);
        for (long lood1 : list1) {
            map1.put(lood1, this.calcu(lood1, lood1 + 1, res));
        }
        // 选出最小的一个
        long minkey = 0;
        long mi = Long.MAX_VALUE;
        for (long m : map1.keySet()) {
            long v = map1.get(m);
            if (v < mi) {
                minkey = m;
                mi = v;
            }
        }
        long resmin = this.calcu(minkey - rang, minkey, res);
        System.out.println(resmin);
    }

    public long calcu(long start, long end, List<String> res) {
        long min = Long.MAX_VALUE;
        for (long seed = start; seed < end; seed++) {
            long soil = seed;
            int index = 1;
            boolean flag = true;
            for (String re : res) {
                if (index < 3) {
                    index++;
                    continue;
                }
                if ("".equals(re)) {
                    flag = true;
                    continue;
                }
                // 第一个是数字
                byte first = (byte) re.charAt(0);
                if (first >= '0' && first <= '9' && flag) {
                    String[] strs = re.split(" ");
                    long s0 = Long.parseLong(strs[0].trim());
                    long s1 = Long.parseLong(strs[1].trim());
                    long s2 = Long.parseLong(strs[2].trim());
                    if (soil >= s1 && soil <= s1 + s2 - 1) {
                        soil = s0 + (soil - s1);
                        flag = false;
                    }
                }
                index++;
            }
            min = Math.min(soil, min);
        }
        return min;
    }

    public static void main(String[] args) {
        Day5 day = new Day5();
        day.star1();
        day.star2();
    }
}
