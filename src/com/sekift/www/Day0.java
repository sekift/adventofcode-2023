package com.sekift.www;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Day0 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day1");
    }

    public void star2() throws InterruptedException {
        List<String> res = InputPuzzle.readFileByLines("./day5");
        long a1 = 2406566863L, a2 = 2407566863L;
        int a3 = 10000000;
        //221434439	245522464	24088025 v 811357076
        //1116624626	1119017930 //2393304 v 2324727144
        //1263068588	1307505291 //44436703 v 2369814041
        //1368516778	1438235925 //69719147 v 1153910022
        //2946842531	3049618234 //102775703 v 199602917
        //3326254382	3427348520 //101094138 v 1483815008-1610043402
        //2098781025	2227032996 //128251971 v 3440755130-496566552
        //3713929839	3868188702 //154258863 v 2899305373-2658844131
        //2361566863	2623672988 //262106125 v
        //1576631370	1934042862 //357411492 v

        long[] arr1 = {3326254382L, 2098781025L, 3713929839L, 2361566863L, 1576631370L};
        long[] arr2 = {3427348520L, 2227032996L, 3868188702L, 2623672988L, 1934042862L};

        List<Long> list1 = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            long a = arr1[i];
            long b = arr2[i];
            int j = 0;
            while (a + j * a3 < b) {
                list1.add(a + j * a3);
                j++;
            }
            list1.add(b);
        }
        System.out.println(list1);

//        int solv = 1000000;
//        List<Long> list = new ArrayList<>();
//        final int size = a3 / solv + 1;
//        final CountDownLatch latch = new CountDownLatch(size);
//        for (int i = 1; i < size + 1; i++) {
//            final int j = i;
//            new Thread(() -> {
//                // 线程执行任务
//                System.out.println(Thread.currentThread().getName() + " 执行任务...");
//                long start = a1 + (j - 1) * solv;
//                long end = a1 + j * solv;
//                long result = this.calcu(start, end > a2 ? a2 : end, res);
//                list.add(result);
//                // 任务执行完毕，计数器减1
//                latch.countDown();
//            }).start();
//        }
//        // 等待所有任务执行完毕
//        latch.await();
//
//        long min = Collections.min(list);
//        System.out.println("min= " + min);
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
        Day0 day = new Day0();
        day.star1();
        try {
            day.star2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
