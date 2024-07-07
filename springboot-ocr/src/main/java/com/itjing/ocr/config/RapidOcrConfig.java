package com.itjing.ocr.config;

import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijing
 * @date 2024-05-09
 */
@Configuration
public class RapidOcrConfig {

	@Bean
	public InferenceEngine inferenceEngine() {
		return InferenceEngine.getInstance(Model.ONNX_PPOCR_V3);
	}

}
