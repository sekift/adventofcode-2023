package com.sekift.www;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day13");
        List<List<String>> list = new ArrayList<>();

        List<String> listTemp = new ArrayList<>();
        for (String re : res) {
            if ("".equals(re)) {
                list.add(listTemp);
                listTemp = new ArrayList<>();
                continue;
            }
            listTemp.add(re);
        }

        List<List<String>> list11 = new ArrayList<>();
        List<List<String>> list22 = new ArrayList<>();
        for (List<String> li2 : list) {
            int c = li2.size();
            int r = li2.get(0).length();
            String[][] strs = new String[c][r];
            for (int i = 0; i < c; i++) {
                String nows = li2.get(i);
                for (int j = 0; j < r; j++) {
                    char now = nows.charAt(j);
                    strs[i][j] = now + "";
                }
            }

            List<String> liint11 = new ArrayList<>();
            for (int m = 0; m < c; m++) {
                StringBuilder str = new StringBuilder();
                for (int n = 0; n < r; n++) {
                    String now = strs[m][n];
                    str.append(now);
                }
                liint11.add(str.toString());
            }
            list11.add(liint11);

            List<String> liint22 = new ArrayList<>();
            for (int m = 0; m < r; m++) {
                StringBuilder str = new StringBuilder();
                for (int n = 0; n < c; n++) {
                    String now = strs[n][m];
                    str.append(now);
                }
                liint22.add(str.toString());
            }
            list22.add(liint22);
        }

        int sum = 0;
        for (int p = 0; p < list11.size(); p++) {
            int r1 = this.reflect(list11.get(p));
            int r2 = this.reflect(list22.get(p));
            sum += (100 * r1 + r2);
        }
        System.out.println(sum);
    }

    public int reflect(List<String> list) {
        int res = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                int j = 1, count = 0;
                while (i - j >= 0 && i + 1 + j <= list.size() - 1) {
                    if (list.get(i - j).equals(list.get(i + 1 + j))) {
                        count++;
                    }
                    j++;
                }
                if (i - count == 0 || i + count + 1 == list.size() - 1) {
                    res = (i + 1);
                }
            }
        }
        return res;
    }


    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day13");
    }

    public static void main(String[] args) {
        Day13 day = new Day13();
        day.star1();
        day.star2();
    }
}
