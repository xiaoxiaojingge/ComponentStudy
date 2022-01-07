package com.itjing.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.itjing.entity.Article;
import com.itjing.listener.ArticleListener;
import com.itjing.response.RestResult;
import com.itjing.response.RestResultUtils;
import com.itjing.service.ArticleService;
import com.itjing.utils.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author lijing
 * @date 2021年11月14日 12:51
 * @description
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ArticleService aricleService;

    /**
     * exportExcel by xyhj
     * 原生写法
     * @param request
     * @param response
     * @throws IOException
     */
    /*@GetMapping("/exportExcelOne")
    public void exportExcelOne(HttpServletRequest request, HttpServletResponse response) throws IOException {


        List<Article> articles = aricleService.selectAll(null);
        List<Article> dataList1 = articles;
        List<Article> dataList2 = articles;


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // 设置列头样式
        CellStyle topStyle = ExcelExportUtil.getColumnTopStyle(workbook);
        // 设置列头背景色
        topStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
        topStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 设置结果的样式
        CellStyle bodyStyle = ExcelExportUtil.getBodyStyle(workbook);
        List<Sheet> sheetList = new ArrayList<>();
        // 设置要导出的sheet的名字
        String[] sheets = {"按标题统计", "按作者统计"};

        String[] headers = null;
        // 根据不同的sheet，设置的列头也不一样
        for (int j = 0; j < sheets.length; j++) {
            String sheetName = sheets[j];
            Sheet sheet = workbook.createSheet(sheetName);
            if (sheets[0].equals(sheetName)) {
                headers = new String[]{"标题", "内容"};
            } else if (sheets[1].equals(sheetName)) {
                headers = new String[]{"标题", "作者", "内容"};
            }
            Row row = sheet.createRow(0);
            // 在excel表中添加表头
            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellStyle(topStyle);
                cell.setCellValue(text);
            }
            sheetList.add(sheet);
        }

        // 开始导出数据
        int firstRowNum = 1;
        int secondRowNum = 1;

        // 目前只有两页，所以直接写死
        Sheet firstSheet = sheetList.get(0);
        Sheet secondSheet = sheetList.get(1);

        // 在表中存放查询到的数据放入对应的列---第一个sheet
        for (Article article : dataList1) {
            Row row = firstSheet.createRow(firstRowNum);
            Cell cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(article.getTitle());
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(bodyStyle);
            cell1.setCellValue(article.getContent());
            firstRowNum++;
        }

        // 在表中存放查询到的数据放入对应的列---第二个sheet
        for (Article article : dataList2) {
            Row row = secondSheet.createRow(secondRowNum);
            Cell cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(article.getTitle());
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(bodyStyle);
            cell1.setCellValue(article.getAuthor());
            Cell cell2 = row.createCell(2);
            cell2.setCellStyle(bodyStyle);
            cell2.setCellValue(article.getContent());
            secondRowNum++;
        }

        //设置列宽
        for (Sheet sheet : sheetList) {
            //根据列头设置列宽，这边最多3列，直接写死
            ExcelExportUtil.setSizeColumn(sheet, 3, 12000);
        }

        //输出到客户端（下载）
        DownloadUtil downUtil = new DownloadUtil();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        baos.close();
        downUtil.download(baos, request, response, "exportExcel.xlsx");
    }*/


    /**
     * exportExcel by rx
     *
     * @param request
     * @param response
     */
/*
    @GetMapping("/exportExcelTwo")
    public void exportExcelTwo(HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap<>();
        List<Article> articles = aricleService.selectAll(null);
        map.clear();
        map.put("data", articles);
        // 生成的导出文件
        String fileName = "exportExcel" + DateUtils.getShortDateStr();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File destFile = File.createTempFile(fileName, ".xlsx");
            XLSTransformer xlsTransformer = new XLSTransformer();
            InputStream is = new BufferedInputStream(
                    new FileInputStream("E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\resources\\download\\templateExcel.xlsx") // 模板 excel
            );
            // 此方法第一个参数是传入读取的模板输入流，第二个为hashMap
            // hashMap的 key 为你在excel 表格中输入的 key 值，value 为你要传入参数的值
            //
//               在 模板 excel 中添加以下模板
//                         A                    B                 C
//                 1      标题	             作者	           内容
//                 2  <jx:forEach items="${data}" var="article">
//                 3  ${article.title}	${article.author}   ${article.content}
//                 4  </jx:forEach>
//
            Workbook workbook = xlsTransformer.transformXLS(is, map);
            Sheet sheet = workbook.getSheet("Sheet1");
            Row row = sheet.getRow(0);
            workbook.createCellStyle().setWrapText(true);
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                sheet.getRow(r).setHeight((short) 800);
            }
            this.setResponseHeader(response, destFile.getName());
            OutputStream oss = response.getOutputStream();
            workbook.write(oss);
            oss.flush();
            oss.close();
            LOGGER.info("导出完成" + destFile.getName());
        } catch (Exception e) {
            LOGGER.info("导出失败：{}", e.getMessage());
            e.printStackTrace();
        } finally {
            // 使用完成后关闭流
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                LOGGER.info("导出失败：{}", e.getMessage());
                e.printStackTrace();
            }
        }
    }
*/

    /**
     * 发送响应流方法
     *
     * @param response
     * @param fileName
     */
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * easyexcel by alibaba
     * 目前感觉这个挺好用，之前两个也还行
     * 文档： https://www.yuque.com/easyexcel/doc/easyexcel
     *
     * @param request
     * @param response
     */
    @GetMapping("/exportExcelThree")
    public void exportExcelThree(HttpServletRequest request, HttpServletResponse response) {

        // 表示要导出的数据
        List<Article> articles = aricleService.selectAll(null);

        // 定义输出文件名称
        String excelName = "信息导出-" + DateUtils.formatDate(new Date(), "yyyy-MM-dd") + ".xlsx";

        // 导出本地文件
       /* File file = File.c("E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\resources\\download\\" + excelName);
        // 写入本地文件中
        EasyExcel.write(file, Article.class)
                .sheet("文章信息")
                .doWrite(articles);
        */

        // web页面导出下载
        ServletOutputStream out = null;
        try {
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName, "utf-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 写入到浏览器
        EasyExcelFactory.write(out, Article.class)
                .sheet("文章导出")
                .doWrite(articles);

    }

    @GetMapping("/import")
    public RestResult<?> importByExcel() {
        String filePath = "E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\resources\\import\\文章信息.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcelFactory.read(filePath, Article.class, new ArticleListener(aricleService))
                .sheet()
                // 这里可以设置行头，如果行头不止一行，可以设置最后一行行头所在位置。
                // 不写 .headRowNumber(位置) 也可以，因为默认会根据 DemoData 来解析，他没有指定头，也就是默认行头是第1行
                .headRowNumber(5)
                .doRead();
        return RestResultUtils.success();
    }

    @GetMapping("/importByInputStream")
    public RestResult<?> importByExcel(MultipartFile file) throws IOException {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcelFactory.read(file.getInputStream(), Article.class, new ArticleListener(aricleService))
                .sheet()
                // 这里可以设置行头，如果行头不止一行，可以设置最后一行行头所在位置。
                // 不写 .headRowNumber(位置) 也可以，因为默认会根据 DemoData 来解析，他没有指定头，也就是默认行头是第1行
                .headRowNumber(1)
                .doRead();
        return RestResultUtils.success();
    }

    /**
     * 解析html文件中table
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/analysisHtml")
    public RestResult<?> analysisHtml(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader htmlReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = htmlReader.readLine()) != null) {
            sb.append(line);
        }
        Document doc = Jsoup.parse(sb.toString());
        Elements rows = doc.select("table").get(0).select("tr");
        if (rows.size() == 1) {
            System.out.println("没有结果");
        } else {
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                System.out.println("title1:" + row.select("td").get(0).text());
                System.out.println("title2:" + row.select("td").get(1).text());
                /*...*/
                System.out.println("-----------------------------------------------------------------");
            }
        }
        return RestResultUtils.success();
    }
}
