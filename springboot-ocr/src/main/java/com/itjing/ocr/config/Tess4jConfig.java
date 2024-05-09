package com.itjing.ocr.config;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TesseractOcr配置类
 *
 * @author lijing
 * @date 2024-05-09
 */
@Configuration
public class Tess4jConfig {

    @Value("${tess4j.train-data.path}")
    private String dataPath;

    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        // 设置训练数据文件夹路径
        tesseract.setDatapath(dataPath);
        // 设置为中文简体
        tesseract.setLanguage("chi_sim");
        // 设置OCR引擎模式
        tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_LSTM_ONLY);
        // 设置页面分隔模式（使用 OSD 进行自动页面分割以进行图像处理）
        tesseract.setPageSegMode(ITessAPI.TessPageSegMode.PSM_AUTO_OSD);
        return tesseract;
    }
}