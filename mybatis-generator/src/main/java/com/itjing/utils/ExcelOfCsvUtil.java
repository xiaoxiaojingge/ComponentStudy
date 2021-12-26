package com.itjing.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author lijing
 * @date 2021年12月23日 16:06
 * @description csv格式文件读取
 */
@Slf4j
public class ExcelOfCsvUtil {

    public static void main(String[] args) throws FileNotFoundException {
        // csv文件地址
        ArrayList<String> strs = readCsv("");
        strs.stream().forEach(str -> {
            // 一般是逗号分隔的
            String[] field = str.split(",");
            Stream.of(field).forEach(data -> {
                System.out.print(data + "\t");
            });
            System.out.println();
        });
    }

    public static ArrayList<String> readCsv(String filepath) {
        ArrayList<String> allString = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(new File(filepath)));
             // 这里如果csv文件编码格式是utf-8,改成utf-8即可
             BufferedReader br = new BufferedReader(new InputStreamReader(in, "GBK"))) {

            String line = "";
            String everyLine = "";

            // 读取到的内容给line变量
            while ((line = br.readLine()) != null) {
                everyLine = line;
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
    }
}
