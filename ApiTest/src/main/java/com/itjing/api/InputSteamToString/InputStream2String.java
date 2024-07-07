package com.itjing.api.InputSteamToString;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.stream.Collectors;

/**
 * @author: lijing
 * @Date: 2021年06月02日 17:44
 * @Description: InputSteam转String
 */
public class InputStream2String {

	public static void main(String[] args) {
		// inputStream2StringOne();
		// inputStream2StringTwo();
		// inputStream2StringThree();
		// inputStream2StringFour();
		// inputStream2StringFive();
		// inputStream2StringSix();
		// inputStream2StringSeven();
		inputStream2StringEight();
	}

	/**
	 * 1、使用 InputStreamReader 和 StringBuilder (JDK)
	 */
	public static void inputStream2StringOne() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			// 根据需要的数组大小进行自定义
			char[] buffer = new char[1024];
			StringBuilder out = new StringBuilder();
			Reader in = new InputStreamReader(inputStream, "UTF-8");
			for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0;) {
				out.append(buffer, 0, numRead);
			}
			String myString = out.toString();

			System.out.println("myString = " + myString);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 2、使用 inputStream.read() and StringBuilder
	 */
	public static void inputStream2StringTwo() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			StringBuilder sb = new StringBuilder();
			for (int ch; (ch = inputStream.read()) != -1;) {
				sb.append((char) ch);
			}
			String myString = sb.toString();
			System.out.println("myString = " + myString);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 3、使用 ByteArrayOutputStream and inputStream.read
	 */
	public static void inputStream2StringThree() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			for (int length; (length = inputStream.read(buffer)) != -1;) {
				result.write(buffer, 0, length);
			}
			String myString = result.toString("UTF-8");

			System.out.println("myString = " + myString);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 4、使用 BufferedInputStream 和 ByteArrayOutputStream
	 */
	public static void inputStream2StringFour() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			for (int result = bis.read(); result != -1; result = bis.read()) {
				buf.write((byte) result);
			}
			String myString = buf.toString("UTF-8");
			System.out.println("myString = " + myString);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 5、使用 BufferedReader
	 */
	public static void inputStream2StringFive() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			String newLine = System.getProperty("line.separator");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder result = new StringBuilder();
			for (String line; (line = reader.readLine()) != null;) {
				if (result.length() > 0) {
					result.append(newLine);
				}
				result.append(line);
			}
			String myString = result.toString();

			System.out.println("myString = " + myString);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 6、使用 Stream API 或 parallel Stream API
	 */
	public static void inputStream2StringSix() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream1 = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			String myString1 = new BufferedReader(new InputStreamReader(inputStream1)).lines()
				.collect(Collectors.joining("\n"));

			InputStream inputStream2 = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			String myString2 = new BufferedReader(new InputStreamReader(inputStream2)).lines()
				.parallel()
				.collect(Collectors.joining("\n"));

			System.out.println("myString1 = " + myString1);
			System.out.println("myString2 = " + myString2);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 7、使用 StringWriter 和IOUtils.copy (Apache Commons)
	 */
	public static void inputStream2StringSeven() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, "UTF-8");
			System.out.println("myString = " + writer.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 8、使用CharStreams (Google Guava)
	 */
	public static void inputStream2StringEight() {
		try {
			// 路径修改为本地文件所在的位置
			InputStream inputStream = new FileInputStream(
					"E:\\workspace_idea\\ComponentStudy\\ApiTest\\src\\main\\resources\\testFile.txt");
			String result = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
			System.out.println("myString = " + result);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
