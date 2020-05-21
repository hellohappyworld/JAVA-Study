package com.gaowj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * created by gaowj.
 * created on 2020-05-21.
 * function: 超时统计
 */
public class TimeoutStatistics {
    public static void main(String[] args) {
        String filePath = "C:\\workStation\\coldbootrecLogs\\2358.1589990280671.log";
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] split = lineTxt.split("--->");
                    String time = split[2].split("#")[0].split(",")[0];
                    if (Integer.parseInt(time.trim()) > 200)
                        list.add(time);
                }
                bufferedReader.close();
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
            System.out.println(list.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
