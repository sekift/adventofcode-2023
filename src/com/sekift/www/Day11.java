package com.sekift.www;

import java.util.ArrayList;
import java.util.List;

public class Day11 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day11");
        List<Integer> clist = new ArrayList<>();
        String[][] resarr = new String[res.size()][res.get(0).length()];
        for (int i = 0; i < res.size(); i++) {
            if (!res.get(i).contains("#")) {
                clist.add(i);
            }
            String[] strs = res.get(i).split("");
            System.arraycopy(strs, 0, resarr[i], 0, strs.length);
        }

        List<Integer> rlist = new ArrayList<>();
        List<String> dlist = new ArrayList<>();

        for (int j = 0; j < resarr[0].length; j++) {
            StringBuilder str = new StringBuilder();
            for (int k = 0; k < resarr.length; k++) {
                String now = resarr[k][j];
                str.append(now);
                if ("#".equals(now)) {
                    dlist.add(k + ":" + j);
                }
            }
            if (!str.toString().contains("#")) {
                rlist.add(j);
            }
        }

        // 膨胀： c=行，r=列
        List<String> list = new ArrayList<>();
        for (String str : dlist) {
            String[] strs = str.split(":");
            int c = Integer.parseInt(strs[0]);
            int r = Integer.parseInt(strs[1]);

            int i = 0, j = 0;
            for (int cl : clist) {
                if (c > cl) {
                    i++;
                }
            }
            for (int rl : rlist) {
                if (r > rl) {
                    j++;
                }
            }

            list.add((c + i) + ":" + (r + j));
        }

        // 两两距离
        int sum = 0;
        for (int m = 0; m < list.size() - 1; m++) {
            String[] mstr = list.get(m).split(":");
            int mc = Integer.parseInt(mstr[0]);
            int mr = Integer.parseInt(mstr[1]);
            for (int n = m + 1; n < list.size(); n++) {
                String[] nstr = list.get(n).split(":");
                int nc = Integer.parseInt(nstr[0]);
                int nr = Integer.parseInt(nstr[1]);

                // 计算
                int count = Math.abs(nc - mc) + Math.abs(nr - mr);
                sum += count;
            }
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day11");
        List<Integer> clist = new ArrayList<>();
        String[][] resarr = new String[res.size()][res.get(0).length()];
        for (int i = 0; i < res.size(); i++) {
            if (!res.get(i).contains("#")) {
                clist.add(i);
            }
            String[] strs = res.get(i).split("");
            System.arraycopy(strs, 0, resarr[i], 0, strs.length);
        }

        List<Integer> rlist = new ArrayList<>();
        List<String> dlist = new ArrayList<>();

        for (int j = 0; j < resarr[0].length; j++) {
            StringBuilder str = new StringBuilder();
            for (int k = 0; k < resarr.length; k++) {
                String now = resarr[k][j];
                str.append(now);
                if ("#".equals(now)) {
                    dlist.add(k + ":" + j);
                }
            }
            if (!str.toString().contains("#")) {
                rlist.add(j);
            }
        }

        // 膨胀： c=行，r=列
        List<String> list = new ArrayList<>();
        // 放大的倍数
        int multi = 1000000-1;
        for (String str : dlist) {
            String[] strs = str.split(":");
            int c = Integer.parseInt(strs[0]);
            int r = Integer.parseInt(strs[1]);

            int i = 0, j = 0;
            for (int cl : clist) {
                if (c > cl) {
                    i += multi;
                }
            }
            for (int rl : rlist) {
                if (r > rl) {
                    j += multi;
                }
            }

            list.add((c + i) + ":" + (r + j));
        }

        // 两两距离
        long sum = 0;
        for (int m = 0; m < list.size() - 1; m++) {
            String[] mstr = list.get(m).split(":");
            int mc = Integer.parseInt(mstr[0]);
            int mr = Integer.parseInt(mstr[1]);
            for (int n = m + 1; n < list.size(); n++) {
                String[] nstr = list.get(n).split(":");
                int nc = Integer.parseInt(nstr[0]);
                int nr = Integer.parseInt(nstr[1]);

                // 计算
                int count = Math.abs(nc - mc) + Math.abs(nr - mr);
                sum += count;
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day11 day = new Day11();
        day.star1();
        day.star2();
    }
}
