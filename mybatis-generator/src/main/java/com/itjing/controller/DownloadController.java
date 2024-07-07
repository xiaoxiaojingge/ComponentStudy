package com.itjing.controller;

import com.itjing.utils.DownloadUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author lijing
 * @date 2021年11月14日 11:22
 * @description
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DownloadController.class);

	@Value("E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\resources\\download\\download.docx")
	private String wordFilePath;

	@Value("E:\\workspace_idea\\ComponentStudy\\mybatis-generator\\src\\main\\resources\\download\\download.xlsx")
	private String excelFilePath;

	@Autowired
	private DownloadUtil downloadUtil;

	@GetMapping("/downloadFile")
	public void download(HttpServletRequest request, HttpServletResponse response) {
		// downloadUtil.download(wordFilePath, "李晶的word文档.docx", response, false);

		// downloadUtil.download(excelFilePath, "李晶的excel表格.xlsx", response, false);

		/*
		 * ByteArrayOutputStream baos = fileToByteArrayOutputStream(wordFilePath); try {
		 * downloadUtil.download(baos, request, response, "myWord.docx"); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		downloadByPath(wordFilePath, "myWord.docx", response);
	}

	/**
	 * 将文件转为ByteArrayOutputStream
	 * @param filePath
	 * @return
	 */
	private ByteArrayOutputStream fileToByteArrayOutputStream(String filePath) {
		File file = new File(filePath);
		try (FileInputStream in = new FileInputStream(file); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			/*
			 * byte[] buf = new byte[1024]; int readLength = 0; while ((readLength =
			 * in.read(buf)) != -1) {
			 *
			 * out.write(buf, 0, readLength); }
			 */
			// 上面的操作可以直接使用commons-io中工具类
			IOUtils.copy(in, out);
			return out;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载文件
	 * @param path
	 * @param fileName
	 * @param response
	 */
	private void downloadByPath(String path, String fileName, HttpServletResponse response) {
		try (InputStream inputStream = new FileInputStream(new File(path));
				OutputStream outputStream = response.getOutputStream()) {
			// 设置响应类型
			// PDF文件为"application/pdf"
			// WORD文件为："application/msword"
			// EXCEL文件为："application/vnd.ms-excel"
			// 可以去参照 HTTP content-type 对照表
			response.setContentType("application/x-download");
			// attachment作为附件下载；inline客户端机器有安装匹配程序，则直接打开；注意改变配置，清除缓存，否则可能不能看到效果
			// response.setHeader("Content-Disposition", "attachment;filename=" +
			// fileName);
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
