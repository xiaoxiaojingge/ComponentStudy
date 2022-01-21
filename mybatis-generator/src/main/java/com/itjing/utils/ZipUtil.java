package com.itjing.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author lijing
 * @date 2022年01月20日 17:00
 * @description zip工具类
 */
@Slf4j
public class ZipUtil {

    public static final String EXCEL_XLSX = ".xlsx";
    public static final String EXCEL_XLS = ".xls";

    public static InputStream readZipOne(InputStream ios) throws IOException {
        // 获取文件输入流
        InputStream inputStream = new BufferedInputStream(ios);
        // 获取ZIP输入流（一定要指定字符集Charset.forName("GBK") 否则会报java.lang.IllegalArgumentException: MALFORMED)
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream), Charset.forName("GBK"));
        ZipEntry ze = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream resultInputStream = null;
        while ((ze = zipInputStream.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                // 如果是目录，不处理
                continue;
            }
            try {
                String zipFileName = ze.getName();
                if (zipFileName != null && zipFileName.indexOf(".") != -1 && (zipFileName.endsWith(EXCEL_XLSX) || zipFileName.endsWith(EXCEL_XLS))) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zipInputStream.read(buffer)) > -1) {
                        baos.write(buffer, 0, len);
                    }
                    baos.flush();
                    // excel流
                    resultInputStream = new ByteArrayInputStream(baos.toByteArray());
                    baos.reset();
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        zipInputStream.closeEntry();
        return resultInputStream;
    }


    public static void main(String[] args) throws IOException {
        // 获取zip中的excel流
        String zipFilePath = "";
        FileInputStream inputStream1 = new FileInputStream(zipFilePath);
        InputStream resultStream = readZipOne(inputStream1);
        // 将流转为文件，此处是测试是否成功获取流，真正使用时不需要加上
        inputStreamToFile(resultStream, "1.xls");


        // 获取zip中嵌套7z中的excel流
        String zip7zFilePath = "";
        FileInputStream inputStream2 = new FileInputStream(zip7zFilePath);
        InputStream csvInputStream = getCsvInputStream(inputStream2);
        // 将流转为文件，此处是测试是否成功获取流，真正使用时不需要加上
        inputStreamToFile(csvInputStream, "1.csv");
    }


    public static InputStream read7z(InputStream ios) throws IOException {
        // 获取文件输入流
        InputStream inputStream = new BufferedInputStream(ios);
        // 获取ZIP输入流（一定要指定字符集Charset.forName("GBK") 否则会报java.lang.IllegalArgumentException: MALFORMED)
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream), Charset.forName("GBK"));
        ZipEntry ze = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream resultInputStream = null;
        while ((ze = zipInputStream.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                // 如果是目录，不处理
                continue;
            }
            try {
                String zipFileName = ze.getName();
                if (zipFileName != null && zipFileName.indexOf(".") != -1 && (zipFileName.endsWith(".7z"))) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zipInputStream.read(buffer)) > -1) {
                        baos.write(buffer, 0, len);
                    }
                    baos.flush();
                    resultInputStream = new ByteArrayInputStream(baos.toByteArray());
                    baos.reset();
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        zipInputStream.closeEntry();
        return resultInputStream;
    }

    /**
     * 7z中文件转为输入流
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static InputStream SevenZFileToInputStream(String fileName) throws IOException {
        final List<String> out = new ArrayList<>();
        final File input7z = new File(fileName);
        InputStream resultInputStream = null;
        try (final SevenZFile sevenZFile = new SevenZFile(input7z)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            while (entry != null) {
                String name = entry.getName();
                out.add(name);
                if (name != null && name.indexOf(".") != -1 && (name.endsWith(".csv"))) {
                    byte[] content = new byte[(int) entry.getSize()];
                    sevenZFile.read(content, 0, (int) entry.getSize());
                    resultInputStream = new ByteArrayInputStream(content);
                }
                entry = sevenZFile.getNextEntry();
            }
        } catch (final IOException ioe) {
            final String error = "Error when reading entry " + fileName;
            throw ioe;
        } finally {

        }
        return resultInputStream;
    }

    /**
     * 流转换成文件
     *
     * @param inputStream
     */
    public static String inputStreamToFile(InputStream inputStream, String tempName) {
        // 新建临时文件
        File file = new File(tempName);
        try {
            if (file.exists()) {
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024 * 1024];
            // 先读后写
            while ((read = inputStream.read(bytes)) > 0) {
                byte[] wBytes = new byte[read];
                System.arraycopy(bytes, 0, wBytes, 0, read);
                os.write(wBytes);
            }
            os.flush();
            os.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    /**
     * 获取7z中csv流
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static InputStream getCsvInputStream(InputStream inputStream) throws IOException {
        InputStream resultStream = null;
        InputStream sevenZStream = null;
        InputStream csvStream = null;
        ByteArrayOutputStream baos = null;
        try {
            sevenZStream = read7z(inputStream);
            String csvName = inputStreamToFile(sevenZStream, "temp.csv");
            csvStream = SevenZFileToInputStream(csvName);
            baos = cloneInputStream(csvStream);
            resultStream = new ByteArrayInputStream(baos.toByteArray());
//        String resultPath = inputStreamToCsv(csvStream, "result.csv");
            File file1 = new File(csvName);
//        File file2 = new File(resultPath);
            if (file1.delete()) {
                log.info("temp.csv has been deleted!");
            }
       /* if (file2.delete()) {
            LOGGER.info("result.csv has been deleted!");
        }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                baos.close();
            }
            if (csvStream != null) {
                csvStream.close();
            }
            if (sevenZStream != null) {
                sevenZStream.close();
            }
        }
        return resultStream;
    }

    /**
     * 拷贝流
     *
     * @param input
     * @return
     */
    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
