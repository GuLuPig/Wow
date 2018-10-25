package com.shop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.entity.SeckillUser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Demoo {

    private static void createUser(int count) throws Exception {
        List<SeckillUser> users = new ArrayList<SeckillUser>(count);
        // 生成用户

    }

    public static void WriteStringToFile(Integer count) {

        try {
            File file = new File("D:/data.txt");
            PrintStream ps = new PrintStream(new FileOutputStream(file));
//            ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
            System.out.println("create user");
            String urlString = "http://localhost:8080/user/do_login";

            for (int i=0;i<count;i++) {

                ps.println(i);// 往文件里写入字符串


            }
            System.out.println("over");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
//        createUser(5000);
        WriteStringToFile(5000);
    }

}
