package com.sekift.www;

import java.util.List;

public class Day1 {

    public void star1() {
        int size = 1000;
        int[] firstDigit = new int[size];
        int[] lastDigit = new int[size];

        List<String> res = InputPuzzle.readFileByLines("./day1");
        int eleIndex = 0;
        for (String ele : res) {
            byte[] strs = ele.getBytes();

            // 正向
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] >= '1' && strs[i] <= '9') {
                    firstDigit[eleIndex] = strs[i] - 48;
                    break;
                }
            }

            // 反向
            for (int j = strs.length - 1; j >= 0; j--) {
                if (strs[j] >= '1' && strs[j] <= '9') {
                    lastDigit[eleIndex] = strs[j] - 48;
                    break;
                }
            }

            // 自增
            eleIndex++;
        }

        int sum = 0;
        for (int k = 0; k < size; k++) {
            sum += firstDigit[k] * 10 + lastDigit[k];
        }
        System.out.println(sum);
    }

    public void star2() {
        String[] letters = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int size = 1000;
        int[] firstDigit = new int[size];
        int[] lastDigit = new int[size];

        List<String> res = InputPuzzle.readFileByLines("./day1");

        // 替换字母为数字
        int eleInd = 0;
        for (String ele : res) {
            // 正向：从前往后匹配，遇到数字即停止
            int eleLen = ele.length();
            byte[] strs = ele.getBytes();
            for (int i = 0; i < eleLen; i++) {
                if (firstDigit[eleInd] > 0) {
                    break;
                }
                if (strs[i] >= '1' && strs[i] <= '9') {
                    firstDigit[eleInd] = strs[i] - 48;
                    break;
                }
                String substr = ele.substring(i, eleLen);
                int k = 0;
                for (String lett : letters) {
                    if (substr.startsWith(lett)) {
                        firstDigit[eleInd] = digits[k];
                        break;
                    }
                    k++;
                }
            }

            // 反向：从后往前匹配，遇到数字即停止
            for (int j = eleLen - 1; j >= 0; j--) {
                if (lastDigit[eleInd] > 0) {
                    break;
                }
                if (strs[j] >= '1' && strs[j] <= '9') {
                    lastDigit[eleInd] = strs[j] - 48;
                    break;
                }
                String substr = ele.substring(0, j + 1);
//                System.out.println(substr);
                int k = 0;
                for (String lett : letters) {
                    if (substr.endsWith(lett)) {
                        lastDigit[eleInd] = digits[k];
                        break;
                    }
                    k++;
                }
            }

            // 自增
            eleInd++;
        }

        int sum = 0;
        for (int k = 0; k < size; k++) {
//            System.out.println(firstDigit[k] + " + " + lastDigit[k]);
            sum += firstDigit[k] * 10 + lastDigit[k];
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day1 day = new Day1();
        day.star1();
        day.star2();
    }
}
