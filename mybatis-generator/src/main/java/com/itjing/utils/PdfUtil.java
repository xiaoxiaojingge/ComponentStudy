package com.itjing.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author lijing
 * @date 2021年11月16日 9:47
 * @description pdf工具类
 */
public class PdfUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PdfUtil.class);

	/**
	 * 使用map中的参数填充pdf，map中的key和pdf表单中的field对应，导出到Web浏览器
	 * @param fieldValueMap
	 * @param file
	 * @return
	 */
	public static byte[] fillParam(Map<String, Map<String, Object>> fieldValueMap, byte[] file) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			PdfReader reader = null;
			PdfStamper stamper = null;
			BaseFont base = null;
			try {
				reader = new PdfReader(file);
				stamper = new PdfStamper(reader, baos);
				stamper.setFormFlattening(true);
				base = getDefaultBaseFont();
				AcroFields acroFields = stamper.getAcroFields();
				for (String key : acroFields.getFields().keySet()) {
					acroFields.setFieldProperty(key, "textfont", base, null);
					acroFields.setFieldProperty(key, "textsize", new Float(10), null);
				}
				if (fieldValueMap != null) {
					// 表单处理
					Map<String, Object> tablemap = fieldValueMap.get("tableMap");
					if (tablemap != null) {
						for (String fieldName : tablemap.keySet()) {
							if (tablemap.get(fieldName) != null) {
								acroFields.setField(fieldName, tablemap.get(fieldName).toString(), true);
							}
						}
					}

					// 二维码处理
					Map<String, Object> qrcodeFields = fieldValueMap.get("qrcodeFields");
					if (qrcodeFields != null) {
						// 遍历二维码字段
						for (Map.Entry<String, Object> entry : qrcodeFields.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							// 获取属性的类型
							if (value != null && acroFields.getField(key) != null) {
								// 获取位置(左上右下)
								AcroFields.FieldPosition fieldPosition = acroFields.getFieldPositions(key).get(0);
								// 绘制二维码
								float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
								BarcodeQRCode pdf417 = new BarcodeQRCode(value.toString(), (int) width, (int) width,
										null);
								// 生成二维码图像
								Image image128 = pdf417.getImage();
								// 绘制在第一页
								PdfContentByte cb = stamper.getOverContent(1);
								// 左边距(居中处理)
								float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft()
										- image128.getWidth()) / 2;
								// 二维码位置
								image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft,
										fieldPosition.position.getBottom());
								// 加入二维码
								cb.addImage(image128);
							}
						}
					}

					// 条形码处理
					Map<String, Object> barcodeFields = fieldValueMap.get("barcodeFields");
					if (barcodeFields != null) {
						// 遍历条码字段
						for (Map.Entry<String, Object> entry : barcodeFields.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							// 获取属性的类型
							if (value != null && acroFields.getField(key) != null) {
								// 获取位置(左上右下)
								AcroFields.FieldPosition fieldPosition = acroFields.getFieldPositions(key).get(0);
								// 绘制条码
								Barcode128 barcode128 = new Barcode128();
								// 设置font为null即可
								// 不要显示文字或者数字
								barcode128.setFont(null);
								// 字号
								barcode128.setSize(10);
								// 条码高度
								barcode128.setBarHeight(35);
								// 条码与数字间距
								barcode128.setBaseline(10);
								// 条码值
								barcode128.setCode(value.toString());
								barcode128.setStartStopText(false);
								barcode128.setExtended(true);
								// 绘制在第一页
								PdfContentByte cb = stamper.getOverContent(1);
								// 生成条码图片
								Image image128 = barcode128.createImageWithBarcode(cb, null, null);
								// 左边距(居中处理)
								float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft()
										- image128.getWidth()) / 2;
								// 条码位置
								image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft,
										fieldPosition.position.getBottom());
								// 加入条码
								cb.addImage(image128);
							}
						}
					}

					// 图片处理
					Map<String, Object> imgmap = fieldValueMap.get("imageMap");
					if (imgmap != null) {
						for (String key : imgmap.keySet()) {
							String imgpath = imgmap.get(key).toString();
							int pageNo = acroFields.getFieldPositions(key).get(0).page;
							Rectangle signRect = acroFields.getFieldPositions(key).get(0).position;
							float x = signRect.getLeft();
							float y = signRect.getBottom();
							// 根据路径读取图片
							Image image = Image.getInstance(imgpath);
							// 获取图片页面
							PdfContentByte under = stamper.getOverContent(pageNo);
							// 图片大小自适应
							image.scaleToFit(signRect.getWidth(), signRect.getHeight());
							// 添加图片
							image.setAbsolutePosition(x, y);
							under.addImage(image);
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (stamper != null) {
					try {
						stamper.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (reader != null) {
					reader.close();
				}
			}

		}
		catch (Exception e) {
			System.out.println("填充参数异常");
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(baos);
		}
		return baos.toByteArray();
	}

	/**
	 * 使用map中的参数填充pdf，map中的key和pdf表单中的field对应，生成到本地
	 * @param fieldValueMap
	 * @param file
	 * @param contractFileName
	 */
	public static void fillParam(Map<String, Map<String, Object>> fieldValueMap, byte[] file, String contractFileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(contractFileName);
			PdfReader reader = null;
			PdfStamper stamper = null;
			BaseFont base = null;
			try {
				reader = new PdfReader(file);
				stamper = new PdfStamper(reader, fos);
				stamper.setFormFlattening(true);
				base = getDefaultBaseFont();
				AcroFields acroFields = stamper.getAcroFields();
				for (String key : acroFields.getFields().keySet()) {
					acroFields.setFieldProperty(key, "textfont", base, null);
					acroFields.setFieldProperty(key, "textsize", new Float(9), null);
				}
				if (fieldValueMap != null) {
					// 表单处理
					Map<String, Object> tablemap = fieldValueMap.get("tableMap");
					if (tablemap != null) {
						for (String fieldName : tablemap.keySet()) {
							if (tablemap.get(fieldName) != null) {
								acroFields.setField(fieldName, tablemap.get(fieldName).toString(), true);
							}
						}
					}

					// 二维码处理
					Map<String, Object> qrcodeFields = fieldValueMap.get("qrcodeFields");
					if (qrcodeFields != null) {
						// 遍历二维码字段
						for (Map.Entry<String, Object> entry : qrcodeFields.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							// 获取属性的类型
							if (value != null && acroFields.getField(key) != null) {
								// 获取位置(左上右下)
								AcroFields.FieldPosition fieldPosition = acroFields.getFieldPositions(key).get(0);
								// 绘制二维码
								float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
								BarcodeQRCode pdf417 = new BarcodeQRCode(value.toString(), (int) width, (int) width,
										null);
								// 生成二维码图像
								Image image128 = pdf417.getImage();
								// 绘制在第一页
								PdfContentByte cb = stamper.getOverContent(1);
								// 左边距(居中处理)
								float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft()
										- image128.getWidth()) / 2;
								// 二维码位置
								image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft,
										fieldPosition.position.getBottom());
								// 加入二维码
								cb.addImage(image128);
							}
						}
					}

					// 条形码处理
					Map<String, Object> barcodeFields = fieldValueMap.get("barcodeFields");
					if (barcodeFields != null) {
						// 遍历条码字段
						for (Map.Entry<String, Object> entry : barcodeFields.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							// 获取属性的类型
							if (value != null && acroFields.getField(key) != null) {
								// 获取位置(左上右下)
								AcroFields.FieldPosition fieldPosition = acroFields.getFieldPositions(key).get(0);
								// 绘制条码
								Barcode128 barcode128 = new Barcode128();
								// 设置font为null即可
								// 不要显示文字或者数字
								barcode128.setFont(null);
								// 字号
								barcode128.setSize(10);
								// 条码高度
								barcode128.setBarHeight(35);
								// 条码与数字间距
								barcode128.setBaseline(10);
								// 条码值
								barcode128.setCode(value.toString());
								barcode128.setStartStopText(false);
								barcode128.setExtended(true);
								// 绘制在第一页
								PdfContentByte cb = stamper.getOverContent(1);
								// 生成条码图片
								Image image128 = barcode128.createImageWithBarcode(cb, null, null);
								// 左边距(居中处理)
								float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft()
										- image128.getWidth()) / 2;
								// 条码位置
								image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft,
										fieldPosition.position.getBottom());
								// 加入条码
								cb.addImage(image128);
							}
						}
					}

					// 图片处理
					Map<String, Object> imgmap = fieldValueMap.get("imageMap");
					if (imgmap != null) {
						for (String key : imgmap.keySet()) {
							String imgpath = imgmap.get(key).toString();
							int pageNo = acroFields.getFieldPositions(key).get(0).page;
							Rectangle signRect = acroFields.getFieldPositions(key).get(0).position;
							float x = signRect.getLeft();
							float y = signRect.getBottom();
							// 根据路径读取图片
							Image image = Image.getInstance(imgpath);
							// 获取图片页面
							PdfContentByte under = stamper.getOverContent(pageNo);
							// 图片大小自适应
							image.scaleToFit(signRect.getWidth(), signRect.getHeight());
							// 添加图片
							image.setAbsolutePosition(x, y);
							under.addImage(image);
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (stamper != null) {
					try {
						stamper.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (reader != null) {
					reader.close();
				}
			}

		}
		catch (Exception e) {
			System.out.println("填充参数异常");
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(fos);
		}
	}

	/**
	 * 默认字体
	 * @return
	 */
	private static BaseFont getDefaultBaseFont() throws IOException, DocumentException {
		return BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	}

	/**
	 * 微软宋体字体，设定字体
	 * @return
	 */
	private static BaseFont getMsyhBaseFont() throws IOException, DocumentException {
		try {
			// windows里的字体资源路径
			String path = "C:/WINDOWS/Fonts/msyh.ttf";
			return BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		}
		catch (DocumentException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return getDefaultBaseFont();
	}

	/**
	 * 获取pdf表单中的fieldNames
	 * @param pdfFileName
	 * @return
	 */
	public static Set<String> getTemplateFileFieldNames(String pdfFileName) {
		Set<String> fieldNames = new TreeSet<String>();
		PdfReader reader = null;
		try {
			reader = new PdfReader(pdfFileName);
			Set<String> keys = reader.getAcroFields().getFields().keySet();
			for (String key : keys) {
				int lastIndexOf = key.lastIndexOf(".");
				int lastIndexOf2 = key.lastIndexOf("[");
				fieldNames.add(key.substring(lastIndexOf != -1 ? lastIndexOf + 1 : 0,
						lastIndexOf2 != -1 ? lastIndexOf2 : key.length()));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				reader.close();
			}
		}

		return fieldNames;
	}

	/**
	 * JavaBean对象转化成Map对象
	 * @param javaBean
	 * @return
	 */
	public static Map<String, Object> bean2Map(Object javaBean) {
		Map<String, Object> map = new HashMap();
		try {
			// 获取javaBean属性
			BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());

			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null && propertyDescriptors.length > 0) {
				// javaBean属性名
				String propertyName = null;
				// javaBean属性值
				Object propertyValue = null;
				for (PropertyDescriptor pd : propertyDescriptors) {
					propertyName = pd.getName();
					if (!propertyName.equals("class")) {
						Method readMethod = pd.getReadMethod();
						propertyValue = readMethod.invoke(javaBean, new Object[0]);
						map.put(propertyName, propertyValue);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
