package com.sekift.www;

import java.util.*;

public class Day16 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day16");
        int sum = 0;
        int clen = res.size();
        int rlen = res.get(0).length();
        // 构造数组
        String[][] arr = new String[clen][rlen];
        for (int i = 0; i < clen; i++) {
            String[] strs = res.get(i).split("");
            System.arraycopy(strs, 0, arr[i], 0, strs.length);
        }

        int len = res.size() - 1;
        // 保存已经跑过的坐标点c+r
        Set<String> allSet = new HashSet<>();
        // 保存需要跑的beam，以及由当前c+r组成的str
        Queue<String> queue = new LinkedList<>();
        String start = "0:0,>";
        queue.add(start);
        // 结构：0=0,>=\ ; 行=列，入射方向=操作路径
        Iterator<String> it = queue.iterator();

        while (it.hasNext()) {
            // 当前
            String now = it.next();

            // 分解
            String[] nows = now.split(",");
            String[] partOnes = nows[0].split(":");
            int c = Integer.parseInt(partOnes[0]);
            int r = Integer.parseInt(partOnes[1]);

            // 前一个符号
            String preSign = nows[1];
            // 当前符号
            String nowSign = arr[c][r];

            if (allSet.contains(now)) {
                queue.remove(now);
                continue;
            }

            /**
             * > c r+1
             * v c+1 r
             * < c r-1
             * ^ c-1 r
             * . 方向不变
             * / 90度翻转，如>-^，v-<，<-v,^->
             * \ 90度翻转，如>-v，v->，<-^,^-<
             * | 分两束，^+v
             * - 分两束，<+>
             */
            if (".".equals(nowSign)) {
                // 方向不变，有四种状态：>，v，<，^
                String news = "";
                if (">".equals(preSign) && r < len) {
                    // 向右走：c r+1
                    news = c + ":" + (r + 1) + ",>";
                } else if ("v".equals(preSign) && c < len) {
                    // 向下走： c+1 r
                    news = (c + 1) + ":" + r + ",v";
                } else if ("<".equals(preSign) && r > 0) {
                    // 向左走：c r-1
                    news = (c) + ":" + (r - 1) + ",<";
                } else if ("^".equals(preSign) && c > 0) {
                    // 向上走：c-1 r
                    news = (c - 1) + ":" + r + ",^";
                }
                if (!"".equals(news) && !allSet.contains(news)) {
                    if (!queue.contains(news)) {
                        queue.add(news);
                    }
                }
            } else if ("/".equals(nowSign)) {
                // 90度翻转，如>-^，v-<，<-v,^->
                String news = "";
                if (">".equals(preSign) && c > 0) {
                    // 向上走：c-1 r
                    news = (c - 1) + ":" + r + ",^";
                } else if ("v".equals(preSign) && r > 0) {
                    // 向左走：c r-1
                    news = (c) + ":" + (r - 1) + ",<";
                } else if ("<".equals(preSign) && c < len) {
                    // 向下走： c+1 r
                    news = (c + 1) + ":" + r + ",v";
                } else if ("^".equals(preSign) && r < len) {
                    // 向右走：c r+1
                    news = c + ":" + (r + 1) + ",>";
                }
                if (!"".equals(news) && !allSet.contains(news)) {
                    if (!queue.contains(news)) {
                        queue.add(news);
                    }
                }
            } else if ("\\".equals(nowSign)) {
                // 90度翻转，如>-v，v->，<-^,^-<
                String news = "";
                if (">".equals(preSign) && c < len) {
                    // 向下走： c+1 r
                    news = (c + 1) + ":" + r + ",v";
                } else if ("v".equals(preSign) && r < len) {
                    // 向右走：c r+1
                    news = c + ":" + (r + 1) + ",>";
                } else if ("<".equals(preSign) && c > 0) {
                    // 向上走：c-1 r
                    news = (c - 1) + ":" + r + ",^";
                } else if ("^".equals(preSign) && r > 0) {
                    // 向左走：c r-1
                    news = (c) + ":" + (r - 1) + ",<";
                }
                if (!"".equals(news) && !allSet.contains(news)) {
                    if (!queue.contains(news)) {
                        queue.add(news);
                    }
                }
            } else if ("|".equals(nowSign)) {
                // 尖头遇到则穿过他继续前进；如果打平遇到则分两束，^+v
                String news = "", news1 = "", news2 = "";
                if (">".equals(preSign) || "<".equals(preSign)) {
                    if (c < len) {
                        // 向下走： c+1 r
                        news1 = (c + 1) + ":" + r + ",v";
                    }
                    if (c > 0) {
                        // 向上走：c-1 r
                        news2 = (c - 1) + ":" + r + ",^";
                    }
                } else if ("v".equals(preSign) && c < len) {
                    // 向下走： c+1 r
                    news = (c + 1) + ":" + r + ",v";
                } else if ("^".equals(preSign) && c > 0) {
                    // 向上走：c-1 r
                    news = (c - 1) + ":" + r + ",^";
                }
                if (!"".equals(news) && !allSet.contains(news)) {
                    if (!queue.contains(news)) {
                        queue.add(news);
                    }
                }
                if (!"".equals(news1) && !allSet.contains(news1)) {
                    if (!queue.contains(news1)) {
                        queue.add(news1);
                    }
                }
                if (!"".equals(news2) && !allSet.contains(news2)) {
                    if (!queue.contains(news2)) {
                        queue.add(news2);
                    }
                }
            } else if ("-".equals(nowSign)) {
                // 尖头遇到则穿过他继续前进；如果打平遇到则分两束，<+>
                String news = "", news1 = "", news2 = "";
                if ("v".equals(preSign) || "^".equals(preSign)) {
                    if (r > 0) {
                        // 向左走：c r-1
                        news1 = (c) + ":" + (r - 1) + ",<";
                    }
                    if (r < len) {
                        // 向右走：c r+1
                        news2 = c + ":" + (r + 1) + ",>";
                    }
                } else if (">".equals(preSign) && r < len) {
                    // 向右走：c r+1
                    news = c + ":" + (r + 1) + ",>";
                } else if ("<".equals(preSign) && r > 0) {
                    // 向左走：c r-1
                    news = (c) + ":" + (r - 1) + ",<";
                }
                if (!"".equals(news) && !allSet.contains(news)) {
                    if (!queue.contains(news)) {
                        queue.add(news);
                    }
                }
                if (!"".equals(news1) && !allSet.contains(news1)) {
                    if (!queue.contains(news1)) {
                        queue.add(news1);
                    }
                }
                if (!"".equals(news2) && !allSet.contains(news2)) {
                    if (!queue.contains(news2)) {
                        queue.add(news2);
                    }
                }
            }

            // 处理了就剔除和增加
            queue.remove(now);
            allSet.add(now);
            it = queue.iterator();
        }

        Set<String> reset = new HashSet<>();
        for (String str : allSet) {
            String s = str.split(",")[0];
            reset.add(s);
        }
        System.out.println(reset.size());
    }

    /**
     * . 方向不变
     * / 90度翻转，如>-^，v-<，<-v,^->
     * \ 90度翻转，如>-v，v->，<-^,^-<
     * | 分两束，^+v
     * - 分两束，<+>
     */
    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day16");
        int sum = 0;
        int clen = res.size();
        int rlen = res.get(0).length();
        // 构造数组
        String[][] arr = new String[clen][rlen];
        for (int i = 0; i < clen; i++) {
            String[] strs = res.get(i).split("");
            System.arraycopy(strs, 0, arr[i], 0, strs.length);
        }

        int len = res.size() - 1;

        // 拼接初始化
        List<String> starts = new ArrayList<>();
        for (int i = 0; i < clen; i++) {
            String str = i + ":" + "0,>";
            starts.add(str);
            str = i + ":" + len + ",<";
            starts.add(str);
            str = "0:" + i + ",v";
            starts.add(str);
            str = len + ":" + i + ",^";
            starts.add(str);
        }

        for (String start : starts) {
            // 保存已经跑过的坐标点c+r
            Set<String> allSet = new HashSet<>();
            // 保存需要跑的beam，以及由当前c+r组成的str
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            // 结构：0=0,>=\ ; 行=列，入射方向=操作路径
            Iterator<String> it = queue.iterator();

            while (it.hasNext()) {
                // 当前
                String now = it.next();

                // 分解
                String[] nows = now.split(",");
                String[] partOnes = nows[0].split(":");
                int c = Integer.parseInt(partOnes[0]);
                int r = Integer.parseInt(partOnes[1]);

                // 前一个符号
                String preSign = nows[1];
                // 当前符号
                String nowSign = arr[c][r];

                if (allSet.contains(now)) {
                    queue.remove(now);
                    continue;
                }

                /**
                 * > c r+1
                 * v c+1 r
                 * < c r-1
                 * ^ c-1 r
                 * . 方向不变
                 * / 90度翻转，如>-^，v-<，<-v,^->
                 * \ 90度翻转，如>-v，v->，<-^,^-<
                 * | 分两束，^+v
                 * - 分两束，<+>
                 */
                if (".".equals(nowSign)) {
                    // 方向不变，有四种状态：>，v，<，^
                    String news = "";
                    if (">".equals(preSign) && r < len) {
                        // 向右走：c r+1
                        news = c + ":" + (r + 1) + ",>";
                    } else if ("v".equals(preSign) && c < len) {
                        // 向下走： c+1 r
                        news = (c + 1) + ":" + r + ",v";
                    } else if ("<".equals(preSign) && r > 0) {
                        // 向左走：c r-1
                        news = (c) + ":" + (r - 1) + ",<";
                    } else if ("^".equals(preSign) && c > 0) {
                        // 向上走：c-1 r
                        news = (c - 1) + ":" + r + ",^";
                    }
                    if (!"".equals(news) && !allSet.contains(news)) {
                        if (!queue.contains(news)) {
                            queue.add(news);
                        }
                    }
                } else if ("/".equals(nowSign)) {
                    // 90度翻转，如>-^，v-<，<-v,^->
                    String news = "";
                    if (">".equals(preSign) && c > 0) {
                        // 向上走：c-1 r
                        news = (c - 1) + ":" + r + ",^";
                    } else if ("v".equals(preSign) && r > 0) {
                        // 向左走：c r-1
                        news = (c) + ":" + (r - 1) + ",<";
                    } else if ("<".equals(preSign) && c < len) {
                        // 向下走： c+1 r
                        news = (c + 1) + ":" + r + ",v";
                    } else if ("^".equals(preSign) && r < len) {
                        // 向右走：c r+1
                        news = c + ":" + (r + 1) + ",>";
                    }
                    if (!"".equals(news) && !allSet.contains(news)) {
                        if (!queue.contains(news)) {
                            queue.add(news);
                        }
                    }
                } else if ("\\".equals(nowSign)) {
                    // 90度翻转，如>-v，v->，<-^,^-<
                    String news = "";
                    if (">".equals(preSign) && c < len) {
                        // 向下走： c+1 r
                        news = (c + 1) + ":" + r + ",v";
                    } else if ("v".equals(preSign) && r < len) {
                        // 向右走：c r+1
                        news = c + ":" + (r + 1) + ",>";
                    } else if ("<".equals(preSign) && c > 0) {
                        // 向上走：c-1 r
                        news = (c - 1) + ":" + r + ",^";
                    } else if ("^".equals(preSign) && r > 0) {
                        // 向左走：c r-1
                        news = (c) + ":" + (r - 1) + ",<";
                    }
                    if (!"".equals(news) && !allSet.contains(news)) {
                        if (!queue.contains(news)) {
                            queue.add(news);
                        }
                    }
                } else if ("|".equals(nowSign)) {
                    // 尖头遇到则穿过他继续前进；如果打平遇到则分两束，^+v
                    String news = "", news1 = "", news2 = "";
                    if (">".equals(preSign) || "<".equals(preSign)) {
                        if (c < len) {
                            // 向下走： c+1 r
                            news1 = (c + 1) + ":" + r + ",v";
                        }
                        if (c > 0) {
                            // 向上走：c-1 r
                            news2 = (c - 1) + ":" + r + ",^";
                        }
                    } else if ("v".equals(preSign) && c < len) {
                        // 向下走： c+1 r
                        news = (c + 1) + ":" + r + ",v";
                    } else if ("^".equals(preSign) && c > 0) {
                        // 向上走：c-1 r
                        news = (c - 1) + ":" + r + ",^";
                    }
                    if (!"".equals(news) && !allSet.contains(news)) {
                        if (!queue.contains(news)) {
                            queue.add(news);
                        }
                    }
                    if (!"".equals(news1) && !allSet.contains(news1)) {
                        if (!queue.contains(news1)) {
                            queue.add(news1);
                        }
                    }
                    if (!"".equals(news2) && !allSet.contains(news2)) {
                        if (!queue.contains(news2)) {
                            queue.add(news2);
                        }
                    }
                } else if ("-".equals(nowSign)) {
                    // 尖头遇到则穿过他继续前进；如果打平遇到则分两束，<+>
                    String news = "", news1 = "", news2 = "";
                    if ("v".equals(preSign) || "^".equals(preSign)) {
                        if (r > 0) {
                            // 向左走：c r-1
                            news1 = (c) + ":" + (r - 1) + ",<";
                        }
                        if (r < len) {
                            // 向右走：c r+1
                            news2 = c + ":" + (r + 1) + ",>";
                        }
                    } else if (">".equals(preSign) && r < len) {
                        // 向右走：c r+1
                        news = c + ":" + (r + 1) + ",>";
                    } else if ("<".equals(preSign) && r > 0) {
                        // 向左走：c r-1
                        news = (c) + ":" + (r - 1) + ",<";
                    }
                    if (!"".equals(news) && !allSet.contains(news)) {
                        if (!queue.contains(news)) {
                            queue.add(news);
                        }
                    }
                    if (!"".equals(news1) && !allSet.contains(news1)) {
                        if (!queue.contains(news1)) {
                            queue.add(news1);
                        }
                    }
                    if (!"".equals(news2) && !allSet.contains(news2)) {
                        if (!queue.contains(news2)) {
                            queue.add(news2);
                        }
                    }
                }

                // 处理了就剔除和增加
                queue.remove(now);
                allSet.add(now);
                it = queue.iterator();
            }

            Set<String> reset = new HashSet<>();
            for (String str : allSet) {
                String s = str.split(",")[0];
                reset.add(s);
            }
            sum = Math.max(sum, reset.size());
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day16 day = new Day16();
        day.star1();
        day.star2();
    }
}
