package com.itjing.tess4j.controller;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * tess4j前端控制器
 *
 * @author lijing
 * @date 2024-05-09
 */
@Slf4j
@RestController
@RequestMapping("/tess4j")
public class Tess4jController {

    @Resource
    private Tesseract tesseract;

    @PostMapping("/ocrRecognize")
    public String ocrRecognize(List<MultipartFile> fileList) {
        // 提取图片中的文本信息
        StringBuilder result = new StringBuilder();
        fileList.forEach(file -> {
            try {
                // 转换
                InputStream bis = new ByteArrayInputStream(file.getBytes());
                BufferedImage bufferedImage = ImageIO.read(bis);
                // 对图片进行文字识别
                String ocrResult = tesseract.doOCR(bufferedImage);
                result.append(ocrResult);
                result.append("\n");
            } catch (IOException e) {
                log.error("发生IO异常，{}", e.getMessage());
            } catch (TesseractException e) {
                log.error("OCR识别出现异常，{}", e.getMessage());
            }
        });
        return result.toString();
    }
}
