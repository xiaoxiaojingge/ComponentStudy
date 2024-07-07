package com.itjing.api.sometest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FileTypeUtils {

	// 缓存文件头信息-文件头信息
	public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

	static {
		// images
		mFileTypes.put("FFD8FF", "jpg");
		mFileTypes.put("89504E47", "png");
		mFileTypes.put("47494638", "gif");
		mFileTypes.put("49492A00", "tif");
		mFileTypes.put("424D", "bmp");
		//
		mFileTypes.put("41433130", "dwg"); // CAD
		mFileTypes.put("38425053", "psd");
		mFileTypes.put("7B5C727466", "rtf"); // 日记本
		mFileTypes.put("3C3F786D6C", "xml");
		mFileTypes.put("68746D6C3E", "html");
		mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
		mFileTypes.put("D0CF11E0", "doc");
		mFileTypes.put("5374616E64617264204A", "mdb");
		mFileTypes.put("252150532D41646F6265", "ps");
		mFileTypes.put("255044462D312E", "pdf");
		mFileTypes.put("504B0304", "docx");
		mFileTypes.put("52617221", "rar");
		mFileTypes.put("57415645", "wav");
		mFileTypes.put("41564920", "avi");
		mFileTypes.put("2E524D46", "rm");
		mFileTypes.put("000001BA", "mpg");
		mFileTypes.put("000001B3", "mpg");
		mFileTypes.put("6D6F6F76", "mov");
		mFileTypes.put("3026B2758E66CF11", "asf");
		mFileTypes.put("4D546864", "mid");
		mFileTypes.put("1F8B08", "gz");
		mFileTypes.put("4D5A9000", "exe/dll");
		mFileTypes.put("75736167", "txt");
	}

	/**
	 * 根据文件路径获取文件头信息
	 * @param filePath 文件路径
	 * @return 文件头信息
	 */
	public static String getFileType(String filePath) {
		System.out.println(getFileHeader(filePath));
		System.out.println(mFileTypes.get(getFileHeader(filePath)));
		return mFileTypes.get(getFileHeader(filePath));
	}

	/**
	 * 根据文件路径获取文件头信息
	 * @param filePath 文件路径
	 * @return 文件头信息
	 */
	public static String getFileHeader(String filePath) {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(filePath);
			byte[] b = new byte[4];
			/*
			 * int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length 个字节的数据读入一个
			 * byte 数组中。 int read(byte[] b, int off, int len) 从此输入流中将最多 len 个字节的数据读入一个
			 * byte 数组中。
			 */
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		}
		catch (Exception e) {
		}
		finally {
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
		}
		return value;
	}

	/**
	 * 将要读取文件头信息的文件的byte数组转换成string类型表示
	 * @param src 要读取文件头信息的文件的byte数组
	 * @return 文件头信息
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

	/**
	 * 获取文本文件编码类型
	 * @param inputStream
	 * @return
	 */
	public static String getFilecharset(InputStream inputStream) {
		// 默认GBK
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try (BufferedInputStream bis = new BufferedInputStream(inputStream)) {
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			// 文件编码为 ANSI
			if (read == -1) {
				return charset;
			}
			// 文件编码为 Unicode
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				return "UTF-16LE";
			}
			// 文件编码为 Unicode big endian
			if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
				return "UTF-16BE";
			}
			// 文件编码为 UTF-8
			if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
				return "UTF-8";
			}
			bis.reset();

			int loc = 0;
			while ((read = bis.read()) != -1) {
				loc++;
				if (read >= 0xF0) {
					break;
				}
				// 单独出现BF以下的，也算是GBK
				if (0x80 <= read && read <= 0xBF) {
					break;
				}
				if (0xC0 <= read && read <= 0xDF) {
					read = bis.read();
					// 双字节 (0xC0 - 0xDF)
					if (0x80 <= read && read <= 0xBF) {
						// (0x80
						// - 0xBF),也可能在GB编码内
						continue;
					}
					break;
				}
				// 也有可能出错，但是几率较小
				if (0xE0 <= read && read <= 0xEF) {
					read = bis.read();
					if (0x80 <= read && read <= 0xBF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							charset = "UTF-8";
						}
					}
					break;
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return charset;
	}

	public static void main(String[] args) throws Exception {
		final String fileType = getFileType("D:\\Ry4S_JAVA.dll");
		System.out.println(fileType);
	}

}
