package com.sekift.www;

import java.util.*;

public class Day10 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day10");

        String[][] resarr = new String[res.size()][res.get(0).length()];
        // 第一次找出S的位置
        int si = -1, sj = -1;
        for (int i = 0; i < res.size(); i++) {
            String[] strs = res.get(i).split("");
            for (int j = 0; j < strs.length; j++) {
                if ("S".equals(strs[j])) {
                    si = i;
                    sj = j;
                }
                resarr[i][j] = strs[j];
            }
        }

        // System.out.println(si + " " + sj);

        int len = res.size() - 1;
        int[][] intarr = new int[res.size()][res.get(0).length()];
        // 从S位置的上下左右去找
        Set<String> allSet = new HashSet<>();
        Queue<String> set = new LinkedList<>();
        // 将S改为数字0
        intarr[si][sj] = 0;

        int sum = 0;
        // 首先单独处理S
        // 左
        if (sj > 0) {
            String newSign = resarr[si][sj - 1];
            if ("-".equals(newSign) || "L".equals(newSign) || "F".equals(newSign)) {
                set.add(si + ":" + (sj - 1));
                intarr[si][sj - 1] = 1;
                sum = 1;
            }
        }
        // 右
        if (sj < len) {
            String newSign = resarr[si][sj + 1];
            if ("-".equals(newSign) || "J".equals(newSign) || "7".equals(newSign)) {
                set.add(si + ":" + (sj + 1));
                intarr[si][sj + 1] = 1;
                sum = 1;
            }
        }
        // 上
        if (si > 0) {
            String newSign = resarr[si - 1][sj];
            if ("|".equals(newSign) || "7".equals(newSign) || "F".equals(newSign)) {
                set.add(si - 1 + ":" + sj);
                intarr[si - 1][sj] = 1;
                sum = 1;
            }
        }

        // 下
        if (si < len) {
            String newSign = resarr[si + 1][sj];
            if ("|".equals(newSign) || "L".equals(newSign) || "J".equals(newSign)) {
                set.add(si + 1 + ":" + sj);
                intarr[si + 1][sj] = 1;
                sum = 1;
            }
        }
        allSet.add(si + ":" + sj);
        // | - L J 7 F . S
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String now = it.next();
            System.out.println(set);
            String[] nows = now.split(":");
            int c = Integer.parseInt(nows[0]);
            int r = Integer.parseInt(nows[1]);
            // 当前符号
            String nowSign = resarr[c][r];
            // 当前数值
            int nowValue = intarr[c][r];

            if (allSet.contains(now)) {
                set.remove(now);
                continue;
            }

            // | - L J 7 F . S
            if ("|".equals(nowSign)) {
                // 如果是 | 考察c-1 r和c+1 r，并且其中有一个已经处理过了
                String s1 = c > 0 ? (c - 1) + ":" + r : "";
                String s2 = c < len ? (c + 1) + ":" + r : "";
                if (!"".equals(s1) && !allSet.contains(s1)) {
                    int newc = c - 1;
                    String newSign = resarr[newc][r];
                    if ("7".equals(newSign) || "F".equals(newSign) || "|".equals(newSign)) {
                        set.add(newc + ":" + r);
                        int newValue = Math.max(intarr[newc][r], nowValue + 1);
                        intarr[newc][r] = newValue;
                        sum = newValue;
                    }
                }
                if (!"".equals(s2) && !allSet.contains(s2)) {
                    int newc = c + 1;
                    String newSign = resarr[newc][r];
                    if ("L".equals(newSign) || "J".equals(newSign) || "|".equals(newSign)) {
                        set.add(newc + ":" + r);
                        int newValue = Math.max(intarr[newc][r], nowValue + 1);
                        intarr[newc][r] = newValue;
                        sum = newValue;
                    }
                }
            } else if ("-".equals(nowSign)) {
                // 如果是 - 考察c r-1和c r+1
                String s1 = r > 0 ? c + ":" + (r - 1) : "";
                String s2 = r < len ? c + ":" + (r + 1) : "";
                if (!"".equals(s1) && !allSet.contains(s1)) {
                    int newr = r - 1;
                    String newSign = resarr[c][newr];
                    if ("L".equals(newSign) || "F".equals(newSign) || "-".equals(newSign)) {
                        set.add(c + ":" + newr);
                        int newValue = Math.max(intarr[c][newr], nowValue + 1);
                        intarr[c][newr] = newValue;
                        sum = newValue;
                    }
                }
                if (!"".equals(s2) && !allSet.contains(s2)) {
                    int newr = r + 1;
                    String newSign = resarr[c][newr];
                    if ("7".equals(newSign) || "J".equals(newSign) || "-".equals(newSign)) {
                        set.add(c + ":" + newr);
                        int newValue = Math.max(intarr[c][newr], nowValue + 1);
                        intarr[c][newr] = newValue;
                        sum = newValue;
                    }
                }
            } else if ("L".equals(nowSign)) {
                // 如果是 L 考察c-1 r和c r+1
                String s1 = c > 0 ? (c - 1) + ":" + r : "";
                String s2 = r < len ? c + ":" + (r + 1) : "";
                if (!"".equals(s1) && !allSet.contains(s1)) {
                    int newc = c - 1;
                    String newSign = resarr[newc][r];
                    if ("7".equals(newSign) || "F".equals(newSign) || "|".equals(newSign)) {
                        set.add(newc + ":" + r);
                        int newValue = Math.max(intarr[newc][r], nowValue + 1);
                        intarr[newc][r] = newValue;
                        sum = newValue;
                    }

                }
                if (!"".equals(s2) && !allSet.contains(s2)) {
                    int newr = r + 1;
                    String newSign = resarr[c][newr];
                    if ("7".equals(newSign) || "J".equals(newSign) || "-".equals(newSign)) {
                        set.add(c + ":" + newr);
                        int newValue = Math.max(intarr[c][newr], nowValue + 1);
                        intarr[c][newr] = newValue;
                        sum = newValue;
                    }
                }
            } else if ("J".equals(nowSign)) {
                // 如果是 J 考察c-1 r和c r-1
                String s1 = c > 0 ? c - 1 + ":" + r : "";
                String s2 = r > 0 ? c + ":" + (r - 1) : "";
                if (!"".equals(s1) && !allSet.contains(s1)) {
                    int newc = c - 1;
                    String newSign = resarr[newc][r];
                    if ("7".equals(newSign) || "F".equals(newSign) || "|".equals(newSign)) {
                        set.add(newc + ":" + r);
                        int newValue = Math.max(intarr[newc][r], nowValue + 1);
                        intarr[newc][r] = newValue;
                        sum = newValue;
                    }
                }
                if (!"".equals(s2) && !allSet.contains(s2)) {
                    int newr = r - 1;
                    String newSign = resarr[c][newr];
                    if ("L".equals(newSign) || "F".equals(newSign) || "-".equals(newSign)) {
                        set.add(c + ":" + newr);
                        int newValue = Math.max(intarr[c][newr], nowValue + 1);
                        intarr[c][newr] = newValue;
                        sum = newValue;
                    }
                }
            } else if ("7".equals(nowSign)) {
                // 如果是 7 考察c+1 r和c r-1
                String s1 = c < len ? (c + 1) + ":" + r : "";
                String s2 = r > 0 ? c + ":" + (r - 1) : "";
                if (!"".equals(s1) && !allSet.contains(s1)) {
                    int newc = c + 1;
                    String newSign = resarr[newc][r];
                    if ("L".equals(newSign) || "J".equals(newSign) || "|".equals(newSign)) {

                        set.add(newc + ":" + r);
                        int newValue = Math.max(intarr[newc][r], nowValue + 1);
                        intarr[newc][r] = newValue;
                        sum = newValue;
                    }
                }
                if (!"".equals(s2) && !allSet.contains(s2)) {
                    int newr = r - 1;
                    String newSign = resarr[c][newr];
                    if ("L".equals(newSign) || "F".equals(newSign) || "-".equals(newSign)) {
                        set.add(c + ":" + newr);
                        System.out.println(set);
                        int newValue = Math.max(intarr[c][newr], nowValue + 1);
                        intarr[c][newr] = newValue;
                        sum = newValue;
                    }
                }
            } else if ("F".equals(nowSign)) {
                // 如果是 F 考察c+1 r和c r+1
                String s1 = c < len ? (c + 1) + ":" + r : "";
                String s2 = r < len ? c + ":" + (r + 1) : "";
                if (!"".equals(s1) && !allSet.contains(s1)) {
                    int newc = c + 1;
                    String newSign = resarr[newc][r];
                    if ("L".equals(newSign) || "J".equals(newSign) || "|".equals(newSign)) {
                        set.add(newc + ":" + r);
                        int newValue = Math.max(intarr[newc][r], nowValue + 1);
                        intarr[newc][r] = newValue;
                        sum = newValue;
                    }
                }
                if (!"".equals(s2) && !allSet.contains(s2)) {
                    int newr = r + 1;
                    String newSign = resarr[c][newr];
                    if ("7".equals(newSign) || "J".equals(newSign) || "-".equals(newSign)) {
                        set.add(c + ":" + newr);
                        int newValue = Math.max(intarr[c][newr], nowValue + 1);
                        intarr[c][newr] = newValue;
                        sum = newValue;
                    }
                }
            }

            // 处理了就剔除和增加
            set.remove(now);
            allSet.add(now);
            it = set.iterator();
        }

        // 再从中获取最大值的数字
        System.out.println("结果: " + sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day10");
    }

    public static void main(String[] args) {
        Day10 day = new Day10();
        day.star1();
        day.star2();
    }
}
