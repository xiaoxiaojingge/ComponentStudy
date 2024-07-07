package com.itjing.springboot.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description
 * @Author lijing
 * @Date 2023-07-08 21:36
 */
@Slf4j
public class Pdf2DomTest {

	public static void main(String[] args) {
		String pdfFilePath = "D:\\文档\\WeChat Files\\wxid_3d65kmsk2si721\\FileStorage\\File\\2023-07\\职业分类大典2022版.pdf";
		String htmlFilePath = "D:\\文档\\WeChat Files\\wxid_3d65kmsk2si721\\FileStorage\\File\\2023-07\\职业分类大典2022版.html";

		try {
			PDDocument document = PDDocument.load(new File(pdfFilePath));
			PDFRenderer renderer = new PDFRenderer(document);

			int numPages = document.getNumberOfPages();
			StringBuilder htmlBuilder = new StringBuilder();

			for (int i = 0; i < numPages; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 300); // DPI参数可以根据需要调整

				String imagePath = "D:\\文档\\WeChat Files\\wxid_3d65kmsk2si721\\FileStorage\\File\\2023-07\\images\\image_"
						+ i + ".png";
				ImageIO.write(image, "png", new File(imagePath));

				htmlBuilder.append("<img src=\"").append(imagePath).append("\"/><br>");
			}

			Document htmlDocument = Jsoup.parse(htmlBuilder.toString());
			Element body = htmlDocument.body();

			// 在这里你可以进一步提取并处理HTML内容，根据你的需求提取标题等信息

			String htmlString = body.html();

			// 将HTML内容保存到文件
			FileWriter fileWriter = new FileWriter(htmlFilePath);
			fileWriter.write(htmlString);
			fileWriter.close();

			document.close();

			System.out.println("转换完成！");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
