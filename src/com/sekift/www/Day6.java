package com.sekift.www;

import java.util.List;

public class Day6 {

    public void star1() {
        List<String> res = InputPuzzle.readFileByLines("./day6");
        String first = res.get(0);
        String second = res.get(1);
        String[] time = first.split(":")[1].trim().split("   ");
        String[] distance = second.split(":")[1].trim().split("   ");
        int sum = 1;
        for (int i = 0; i < time.length; i++) {
            int key = Integer.parseInt(time[i].trim());
            int value = Integer.parseInt(distance[i].trim());
            int count = 0;
            for (int j = 1; j < key; j++) {
                int multi = j * (key - j);
                if (multi > value) {
                    count++;
                }
            }
            sum *= count;
        }
        System.out.println(sum);
    }

    public void star2() {
        List<String> res = InputPuzzle.readFileByLines("./day6");
        String first = res.get(0);
        String second = res.get(1);
        String time = first.split(":")[1].trim().replace(" ", "");
        String distance = second.split(":")[1].trim().replace(" ", "");
        int sum = 1;
        long key = Long.parseLong(time.trim());
        long value = Long.parseLong(distance.trim());
        int count = 0;
        for (int j = 1; j < key; j++) {
            long multi = j * (key - j);
            if (multi > value) {
                count++;
            }
        }
        sum *= count;

        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day6 day = new Day6();
        day.star1();
        day.star2();
    }
}
