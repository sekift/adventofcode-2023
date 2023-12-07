package com.sekift.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {

    public void star1() {
        int size = 140;
        String[][] let = new String[size][size];
        List<String> res = InputPuzzle.readFileByLines("./day3");
        int sum = 0;
        int index = 0;
        for (String strs : res) {
            String[] str = strs.split("");
            System.arraycopy(str, 0, let[index], 0, str.length);
            index++;
        }

        // 先定位数字，再判断是否被符号框住
        for (int i = 0; i < size; i++) {
            StringBuilder nowStr = new StringBuilder();
            int startj = 0, endj = 0;
            for (int j = 0; j < size; j++) {
                String now = let[i][j];
                if (isNumber(now)) {
                    nowStr.append(now);
                    if (j == 139) {
                        String str = nowStr.toString();
                        if (str.length() > 0) {
                            sum += judge(let, str, i, startj, 139);

                            nowStr = new StringBuilder();
                            startj = 0;
                            endj = 0;
                        }
                    }
                    if (startj == 0) {
                        startj = j;
                    }
                    endj = j;
                } else {
                    String str = nowStr.toString();
                    if (str.length() > 0) {
                        sum += judge(let, str, i, startj, endj);

                        nowStr = new StringBuilder();
                        startj = 0;
                        endj = 0;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    /**
     * 判断周围是有符合，有返回此整数值，无返回0
     *
     * @param str
     * @param i
     * @param startj
     * @param endj
     * @return
     */
    public int judge(String[][] let, String str, int i, int startj, int endj) {
        int result = Integer.parseInt(str);
        List<String> list = new ArrayList<>();
        // 左右竖点
        list.add((i - 1 < 0 || startj - 1 < 0) ? "." : let[i - 1][startj - 1]);
        list.add((startj <= 1) ? "." : let[i][startj - 1]);
//        System.out.println(i+":"+(startj - 1));
        list.add((i + 1 > 139 || startj - 1 < 0) ? "." : let[i + 1][startj - 1]);
        list.add((i - 1 < 0 || endj + 1 > 139) ? "." : let[i - 1][endj + 1]);
        list.add((endj + 1 > 139) ? "." : let[i][endj + 1]);
        list.add((i + 1 > 139 || endj + 1 > 139) ? "." : let[i + 1][endj + 1]);

        // 找出i,j周围的坐标值
        for (int k = startj; k <= endj; k++) {
            list.add((i - 1 < 0) ? "." : let[i - 1][k]);
            list.add((i + 1 > 139) ? "." : let[i + 1][k]);
        }
        System.out.println(str + " : " + list);
        for (String lis : list) {
            if (!".".equals(lis)) {
                System.out.println(result);
                return result;
            }
        }
        return 0;
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public void star2() {
        int size = 140;
        String[][] let = new String[size][size];
        List<String> res = InputPuzzle.readFileByLines("./day3");
        int sum = 0;
        int index = 0;
        for (String strs : res) {
            String[] str = strs.split("");
            System.arraycopy(str, 0, let[index], 0, str.length);
            index++;
        }

        Map<String, String> map = new HashMap<>(512);
        // 先定位数字，再判断是否被符号框住
        for (int i = 0; i < size; i++) {
            StringBuilder nowStr = new StringBuilder();
            int startj = 0, endj = 0;
            for (int j = 0; j < size; j++) {
                String now = let[i][j];
                if (isNumber(now)) {
                    nowStr.append(now);
                    if (j == 139) {
                        String str = nowStr.toString();
                        if (str.length() > 0) {
                            sum += judge2(map, let, str, i, startj, 139);

                            nowStr = new StringBuilder();
                            startj = 0;
                            endj = 0;
                        }
                    }
                    if (startj == 0) {
                        startj = j;
                    }
                    endj = j;
                } else {
                    String str = nowStr.toString();
                    if (str.length() > 0) {
                        sum += judge2(map, let, str, i, startj, endj);

                        nowStr = new StringBuilder();
                        startj = 0;
                        endj = 0;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    public int judge2(Map<String, String> map, String[][] let, String str, int i, int startj, int endj) {
        int result = Integer.parseInt(str);
        Map<Integer, String> cual = new HashMap<>();
        List<String> list = new ArrayList<>();
        // 左右竖点
        list.add((i - 1 < 0 || startj - 1 < 0) ? "." : let[i - 1][startj - 1]);
        cual.put(1, (i - 1) + "," + (startj - 1));
        list.add((startj <= 1) ? "." : let[i][startj - 1]);
        cual.put(2, (i) + "," + (startj - 1));
//        System.out.println(i+":"+(startj - 1));
        list.add((i + 1 > 139 || startj - 1 < 0) ? "." : let[i + 1][startj - 1]);
        cual.put(3, (i + 1) + "," + (startj - 1));
        list.add((i - 1 < 0 || endj + 1 > 139) ? "." : let[i - 1][endj + 1]);
        cual.put(4, (i - 1) + "," + (endj + 1));
        list.add((endj + 1 > 139) ? "." : let[i][endj + 1]);
        cual.put(5, (i) + "," + (endj + 1));
        list.add((i + 1 > 139 || endj + 1 > 139) ? "." : let[i + 1][endj + 1]);
        cual.put(6, (i + 1) + "," + (endj + 1));

        // 找出i,j周围的坐标值
        int loop = 7;
        for (int k = startj; k <= endj; k++) {
            list.add((i - 1 < 0) ? "." : let[i - 1][k]);
            cual.put(loop, (i - 1) + "," + k);
            loop++;
            list.add((i + 1 > 139) ? "." : let[i + 1][k]);
            cual.put(loop, (i + 1) + "," + k);
            loop++;
        }
        //System.out.println(str+" : "+list);
        int cuind = 1;
        for (String lis : list) {
            if ("*".equals(lis)) {
                //      System.out.println(result);
                String stari = cual.get(cuind);
                if (map.containsKey(stari)) {
                    int a = Integer.parseInt(map.get(stari));
                    int b = Integer.parseInt(str);
                    map.remove(stari);
                    return a * b;
                } else {
                    map.put(stari, str);
                    return 0;
                }
            }
            cuind++;
        }
        return 0;
    }

    public static void main(String[] args) {
        Day3 day = new Day3();
        day.star1();
        day.star2();
    }
}
