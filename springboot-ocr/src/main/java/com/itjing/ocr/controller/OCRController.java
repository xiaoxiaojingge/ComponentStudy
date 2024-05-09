package com.itjing.ocr.controller;

import com.benjaminwan.ocrlibrary.OcrResult;
import io.github.mymonstercat.ocr.InferenceEngine;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

/**
 * tess4j前端控制器
 *
 * @author lijing
 * @date 2024-05-09
 */
@Slf4j
@RestController
@RequestMapping("/ocr")
public class OCRController {

    @Resource
    private Tesseract tesseract;

    @Resource
    private InferenceEngine inferenceEngine;

    /**
     * 使用tess4j实现图片ocr识别
     *
     * @param fileList
     * @return {@link String}
     */
    @PostMapping("/tess4j")
    public String tess4j(List<MultipartFile> fileList) {
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

    /**
     * 使用rapidocr实现ocr图片识别
     *
     * @param fileList
     * @return {@link String}
     */
    @PostMapping("/rapidocr")
    public String rapidocr(List<MultipartFile> fileList) {
        // 提取图片中的文本信息
        StringBuilder result = new StringBuilder();
        fileList.forEach(file -> {
            try {
                // 将 MultipartFile 转换为临时文件
                File tempFile = convertMultipartFileToFile(file);
                OcrResult ocrResult = inferenceEngine.runOcr(tempFile.getAbsolutePath());
                result.append(ocrResult.getStrRes().trim());
                result.append("\n");
                // 处理完后删除临时文件
                tempFile.delete();
            } catch (IOException e) {
                // 处理异常
            }
        });
        return result.toString();
    }

    /**
     * 将MultipartFile转换为临时文件
     *
     * @param file
     * @return {@link File}
     * @throws IOException
     */
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        File tempFile = File.createTempFile(StringUtils.stripFilenameExtension(originalFileName), fileExtension);
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return tempFile;
    }

}
