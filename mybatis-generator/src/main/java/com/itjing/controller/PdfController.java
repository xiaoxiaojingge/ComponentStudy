package com.itjing.controller;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itjing.utils.PdfUtil;
import com.itjing.utils.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijing
 * @date 2021年11月15日 17:15
 * @description pdf相关操作
 */
@RestController
@RequestMapping(value = "/pdf")
public class PdfController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PdfController.class);

	/**
	 * 导出pdf报表到Web
	 * @param request
	 * @param response
	 */
	@GetMapping("/exportPdf")
	public void exportPdfToWeb(HttpServletRequest request, HttpServletResponse response) {

		// 定义一个formBean，自己根据pdf表单定义
		Object formBean = null;

		// 模板文件父路径
		String templateFilePath = "";

		// 获取输出流
		OutputStream out = null;
		ByteArrayOutputStream pdfOutputStream = null;
		try {
			// 获取formBean生成的Map
			Map<String, Object> tableMap = PdfUtil.bean2Map(formBean);
			// 图片Map
			Map<String, Object> imageMap = null;

			Map<String, Map<String, Object>> fieldValueMap = new HashMap<>();

			fieldValueMap.put("tableMap", tableMap);
			fieldValueMap.put("imageMap", imageMap);

			String templateFile = templateFilePath + "/" + "Template.pdf";

			// 获取渲染数据后pdf字节数组数据
			byte[] pdfByteArray = PdfUtil.fillParam(fieldValueMap,
					FileUtils.readFileToByteArray(new File(templateFile)));
			// 设置响应contenType
			response.setContentType("application/pdf");
			// 设置响应文件名称
			String fileName = new String(("不动产抵押登记申报表" + UUIDUtil.get() + ".pdf").getBytes("UTF-8"), "iso-8859-1");
			// 设置文件名称
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			// 字节输出流
			pdfOutputStream = new ByteArrayOutputStream();
			// 获取响应输出流
			out = response.getOutputStream();
			// 将字节数据数组写入到字节输出流
			pdfOutputStream.write(pdfByteArray);
			// 写入到响应流
			pdfOutputStream.writeTo(out);
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pdfOutputStream != null) {
					pdfOutputStream.close();
				}
				if (out != null) {
					out.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导出pdf报表到本地
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void exportPdfToLocal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 正式pdf保存路径
		String desFilePath = "E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\resources\\download\\BdcMortgageApply.pdf";
		// 合同模版的路径
		String templateFilePath = "D:\\学习重要文件\\工作\\睿希信息科技\\济南不动产\\不动产抵押申报表.pdf";

		Map<String, Map<String, Object>> map = new HashMap<>();
		Map<String, Object> tableMap = new HashMap<>();
		tableMap.put("mortType", "111111111111111111111");
		Map<String, Object> imgMap = new HashMap<>();
		map.put("tableMap", tableMap);
		map.put("imgMap", imgMap);

		File templateFile = new File(templateFilePath);
		PdfUtil.fillParam(map, FileUtils.readFileToByteArray(templateFile), desFilePath);
	}

	/**
	 * 生成pdf，百度方案
	 */
	private void exportPdfByBaidu() {
		// 正式pdf保存路径
		String desFile = "E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\resources\\download\\export.pdf";
		// 合同模版的路径
		String templateFile = "D:\\学习重要文件\\工作\\睿希信息科技\\济南不动产\\不动产抵押申报表.pdf";
		PdfReader reader = null;
		PdfStamper stamper;
		ByteArrayOutputStream bos = null;
		FileOutputStream fos = null;
		Map<String, String> paramMap = new HashMap<>();
		// 填写需要填充的表单的key和value
		paramMap.put("", "");
		try {
			// 防止中文乱码
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			reader = new PdfReader(templateFile);
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);

			AcroFields form = stamper.getAcroFields();
			form.addSubstitutionFont(bf);

			// 1、替换表单元素中的变量为具体的值
			for (String name : paramMap.keySet()) {
				form.setField(name, paramMap.get(name));
				form.setFieldProperty(name, "textfont", bf, null);
			}
			// 这两步必须有,否则pdf生成失败
			stamper.setFormFlattening(true);
			stamper.close();

			// 生成填充完成的PDF合同文件
			fos = new FileOutputStream(desFile);
			fos.write(bos.toByteArray());

		}
		catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException, srcFile==" + templateFile, e);
		}
		catch (Exception e) {
			LOGGER.error("Exception, srcFile==" + templateFile, e);
		}
		finally {
			if (null != reader) {
				reader.close();
			}
			try {
				if (null != bos) {
					bos.close();
				}
			}
			catch (IOException e) {
				LOGGER.info("关闭ByteArrayOutputStream失败", e);
			}
			try {
				if (null != fos) {
					fos.close();
				}
			}
			catch (IOException e) {
				LOGGER.info("关闭FileOutputStream失败", e);
			}
		}
	}

}
