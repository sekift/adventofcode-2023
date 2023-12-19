package com.sekift.www;

import java.util.*;

public class Day18 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day18");
        // 统计c和r
        // U D L R
        int maxc = 0, maxr = 0, minc = 1000, minr = 1000;
        int c = 1, r = 1;
        for (String re : res) {
            String[] strs = re.split(" ");
            String dire = strs[0];
            int meters = Integer.parseInt(strs[1]);
            if ("U".equals(dire)) {
                c -= meters;
            } else if ("D".equals(dire)) {
                c += meters;
            } else if ("L".equals(dire)) {
                r -= meters;
            } else if ("R".equals(dire)) {
                r += meters;
            }
            maxc = Math.max(maxc, c);
            maxr = Math.max(maxr, r);
            minc = Math.min(minc, c);
            minr = Math.min(minr, r);
        }
        System.out.println(maxc + " " + maxr + " " + minc + " " + minr);

        int sum = 0;
        int cl = maxc - minc + 1;
        int rl = maxr + minr - 1;
        String[][] arr = new String[cl][rl];
        for (String[] arrs : arr) {
            Arrays.fill(arrs, ".");
        }

        int nowc = 78, nowr = 0;
//        String[][] arr = new String[maxc][maxr];
//        int nowc = 0, nowr = 0;
        arr[nowc][nowr] = "#";
        for (String re : res) {
            String[] strs = re.split(" ");
            String dire = strs[0];
            int meters = Integer.parseInt(strs[1]);
            if ("U".equals(dire)) {
                // 向上：c-i r
                for (int i = 1; i <= meters; i++) {
                    nowc--;
                    arr[nowc][nowr] = "#";
                }
            } else if ("D".equals(dire)) {
                // 向下：c+i r
                for (int i = 1; i <= meters; i++) {
                    nowc++;
                    arr[nowc][nowr] = "#";
                }
            } else if ("L".equals(dire)) {
                // 向左：c r-i
                for (int i = 1; i <= meters; i++) {
                    nowr--;
                    arr[nowc][nowr] = "#";
                }
            } else if ("R".equals(dire)) {
                // 向右：c r+i
                for (int i = 1; i <= meters; i++) {
                    nowr++;
                    arr[nowc][nowr] = "#";
                }
            }
        }

        // 找到起始点
        String start = "";
        for (int i = 0; i < arr[1].length; i++) {
            String now = arr[1][i];
            if ("#".equals(now)) {
                start = "1:" + (i + 1);
                break;
            }
        }

        // 队列，把四个方向，而且被#包围不为#的放进去
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        // 结构：0=0,>=\ ; 行=列，入射方向=操作路径
        Iterator<String> it = queue.iterator();

        // 从4个方向找，遇到.则填充#号，遇到#号则停
        while (it.hasNext()) {
            String now = it.next();
            String[] now1 = now.split(":");
            int c1 = Integer.parseInt(now1[0]);
            int r1 = Integer.parseInt(now1[1]);
            String dire1 = arr[c1 - 1][r1];
            String dire2 = arr[c1 + 1][r1];
            String dire3 = arr[c1][r1 - 1];
            String dire4 = arr[c1][r1 + 1];
            if (".".equals(dire1) && !queue.contains((c1 - 1) + ":" + r1)) {
                queue.add((c1 - 1) + ":" + r1);
                arr[c1 - 1][r1] = "*";
            }
            if (".".equals(dire2) && !queue.contains((c1 + 1) + ":" + r1)) {
                queue.add((c1 + 1) + ":" + r1);
                arr[c1 + 1][r1] = "*";
            }
            if (".".equals(dire3) && !queue.contains(c1 + ":" + (r1 - 1))) {
                queue.add(c1 + ":" + (r1 - 1));
                arr[c1][r1 - 1] = "*";
            }
            if (".".equals(dire4) && !queue.contains(c1 + ":" + (r1 + 1))) {
                queue.add(c1 + ":" + (r1 + 1));
                arr[c1][r1 + 1] = "*";
            }

            // 处理了就剔除和增加
            queue.remove(now);
            it = queue.iterator();
        }

        // 包含：首次与第二次遇到是包围的，
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if ("#".equals(arr[i][j]) || "*".equals(arr[i][j])) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day18");
    }

    public static void main(String[] args) {
        Day18 day = new Day18();
        day.star1();
        day.star2();
    }
}
