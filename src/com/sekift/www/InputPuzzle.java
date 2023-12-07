package com.sekift.www;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputPuzzle {

    /**
     * 以行为单位读取文件内容
     *
     * @param fileName
     * @return
     */
    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader = null;
        List<String> list = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            bufferedReader = new BufferedReader(isr);
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                list.add(tempString);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) { /* 消除异常 */
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> res = InputPuzzle.readFileByLines("./day1");
        System.out.println(res);
    }
}
