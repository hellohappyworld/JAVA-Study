package com.gaowj.run;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gaowj.utils.HttpUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by gaowj.
 * created on 2020-11-12.
 * function: 数据仓库account任务，生成账号维表，获取category.json日志
 * origin ->
 */
public class GetCategory {
    public static void main(String[] args) {
        String categoryJson = HttpUtil.doGet("http://local.fhhapi.ifeng.com/category/list?__token=mep0332k&type=article");
        JSONObject jsonObject = JSON.parseObject(categoryJson);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        int size = jsonArray.size();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dt = simpleDateFormat.format(new Date());
        System.out.println("当前时间：" + dt);
        PrintWriter printWriter = null;
        try {
            File file = new File("/data/prod/account/" + dt);
            file.mkdirs();
            printWriter = new PrintWriter(new FileWriter(file + "/part-0000", true));
            for (int i = 0; i < size; i++) {
                String str = jsonArray.getString(i);
                System.out.println(str);
                printWriter.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
