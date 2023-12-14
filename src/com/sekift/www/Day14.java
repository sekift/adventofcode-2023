package com.sekift.www;

import java.util.List;

public class Day14 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day14");
        int sum = 0;
        int c = res.size();
        int r = res.get(0).length();
        // 构造数组
        String[][] arr = new String[c][r];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                char now = res.get(i).charAt(j);
                arr[i][j] = now + "";
            }
        }

        // 先北走
        for (int m = 0; m < r; m++) {
            for (int n = 0; n < c; n++) {
                String now = arr[n][m];
                if ("O".equals(now)) {
                    // 往回找#后面的第一个.
                    int stp = -1;
                    for (int i = n; i >= 0; i--) {
                        String jud = arr[i][m];
                        if (".".equals(jud)) {
                            stp = i;
                        } else if ("#".equals(jud)) {
                            break;
                        }
                    }
                    // 交换
                    if (stp > -1) {
                        arr[stp][m] = now;
                        arr[n][m] = ".";
                    }
                }
            }
        }

        // 统计
        for (int m = 0; m < c; m++) {
            int count = 0;
            for (int n = 0; n < r; n++) {
                if ("O".equals(arr[m][n])) {
                    count++;
                }
            }
            sum += (count * (c - m));
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day14");


        int c = res.size();
        int r = res.get(0).length();
        // 构造数组
        String[][] arr = new String[c][r];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                char now = res.get(i).charAt(j);
                arr[i][j] = now + "";
            }
        }

        for (int loop = 0; loop < 200; loop++) {
            int sum = 0;
            // 先北走
            for (int m = 0; m < r; m++) {
                for (int n = 0; n < c; n++) {
                    String now = arr[n][m];
                    if ("O".equals(now)) {
                        // 往回找#后面的第一个.
                        int stp = -1;
                        for (int i = n; i >= 0; i--) {
                            String jud = arr[i][m];
                            if (".".equals(jud)) {
                                stp = i;
                            } else if ("#".equals(jud)) {
                                break;
                            }
                        }
                        // 交换
                        if (stp > -1) {
                            arr[stp][m] = now;
                            arr[n][m] = ".";
                        }
                    }
                }
            }

            // 向西走
            for (int m = 0; m < c; m++) {
                for (int n = 0; n < r; n++) {
                    String now = arr[m][n];
                    if ("O".equals(now)) {
                        // 往回找#后面的第一个.
                        int stp = -1;
                        for (int i = n; i >= 0; i--) {
                            String jud = arr[m][i];
                            if (".".equals(jud)) {
                                stp = i;
                            } else if ("#".equals(jud)) {
                                break;
                            }
                        }
                        // 交换
                        if (stp > -1) {
                            arr[m][stp] = now;
                            arr[m][n] = ".";
                        }
                    }
                }
            }

            // 向南走
            for (int m = 0; m < r; m++) {
                for (int n = c - 1; n >= 0; n--) {
                    String now = arr[n][m];
                    if ("O".equals(now)) {
                        // 往回找#后面的第一个.
                        int stp = -1;
                        for (int i = n; i <= c - 1; i++) {
                            String jud = arr[i][m];
                            if (".".equals(jud)) {
                                stp = i;
                            } else if ("#".equals(jud)) {
                                break;
                            }
                        }
                        // 交换
                        if (stp > -1) {
                            arr[stp][m] = now;
                            arr[n][m] = ".";
                        }
                    }
                }
            }

            // 向东走
            for (int m = 0; m < r; m++) {
                for (int n = c - 1; n >= 0; n--) {
                    String now = arr[m][n];
                    if ("O".equals(now)) {
                        // 往回找#后面的第一个.
                        int stp = -1;
                        for (int i = n; i <= c - 1; i++) {
                            String jud = arr[m][i];
                            if (".".equals(jud)) {
                                stp = i;
                            } else if ("#".equals(jud)) {
                                break;
                            }
                        }
                        // 交换
                        if (stp > -1) {
                            arr[m][stp] = now;
                            arr[m][n] = ".";
                        }
                    }
                }
            }

            // 统计
            for (int m = 0; m < c; m++) {
                int count = 0;
                for (int n = 0; n < r; n++) {
                    if ("O".equals(arr[m][n])) {
                        count++;
                    }
                }
                sum += (count * (c - m));
            }
            System.out.println(loop+" : "+sum);
        }

        int[] ar = {102505, 102500,102498, 102507,102504,102497,102507,102512,102509};
        int amount = 1000000000 - 101;
        System.out.println(ar[amount%ar.length]);
    }

    public static void main(String[] args) {
        Day14 day = new Day14();
        day.star1();
        day.star2();
    }
}
