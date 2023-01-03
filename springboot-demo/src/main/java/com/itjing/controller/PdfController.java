package com.itjing.controller;

import com.itextpdf.text.DocumentException;
import com.itjing.pdf.CreatePdfAndTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: pdf控制层
 * @Author: lijing
 * @CreateTime: 2022-12-02 17:53
 */
@RestController
@RequestMapping("/pdf")
public class PdfController {

    @GetMapping("/exportPdf")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        CreatePdfAndTable pdfAndTable = new CreatePdfAndTable();
        pdfAndTable.createPdfFile(response);
    }
}
