package com.itjing.dataStructureStudy.sparsearray;

import java.io.*;

/**
 * @author: lijing
 * @Date: 2021年09月17日 10:41
 * @Description: 稀疏数组
 */
public class SparseArray {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// 创建一个原始的二维数组 11*11
		// 0：表示没有棋子，1表示黑子，2表示蓝子
		int[][] chessArr = new int[11][11];
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		// 输出原始二维数组
		System.out.println("原始的二维数组为：");
		for (int[] row : chessArr) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}

		// 将二维数组转为稀疏数组
		// 1、先遍历二维数组得到非0数据的个数
		int sum = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println("一共有 " + sum + " 个非0数据！\n");

		// 2、创建稀疏数组
		int[][] sparseArray = new int[sum + 1][3];
		// 第一行记录数组一共有 几行几列 ，有多少个不为0的值
		sparseArray[0][0] = 11;
		sparseArray[0][1] = 11;
		sparseArray[0][2] = sum;

		// 遍历二维数组，将非0值存放到稀疏数组中
		int count = 1; // 给稀疏数组当计数器，用于记录是第几个非0数据
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr[i][j] != 0) {
					sparseArray[count][0] = i;
					sparseArray[count][1] = j;
					sparseArray[count][2] = chessArr[i][j];
					count++;
				}
			}
		}

		// 输出稀疏数组
		System.out.println("原始的二维数组对应的稀疏数组为：");
		for (int i = 0; i < sparseArray.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
		}

		// 将稀疏数组回复成原始的二维数组
		System.out.println("\n将稀疏数组回复成原始的二维数组：");
		// 1. 先读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
		int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
		// 2. 在读稀疏数组后几行的数据(从第二行开始,所以i=1),并赋值给原始的二维数组即可
		for (int i = 1; i < sparseArray.length; i++) {
			chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
		// 输出恢复后的二维数组
		for (int[] row : chessArr2) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}

		// 将稀疏数组保存到磁盘上
		String filePath = "E:\\workspace_idea\\ComponentStudy\\AlgorithmPractice\\src\\main\\java\\com\\itjing\\dataStructureStudy\\sparsearray\\sparsearray.data";
		FileOutputStream fos = new FileOutputStream(new File(filePath));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sparseArray.length; i++) {
			sb.append(sparseArray[i][0] + "\t" + sparseArray[i][1] + "\t" + sparseArray[i][2] + "\n");
		}
		fos.write(sb.toString().getBytes());

		// 从磁盘读取数据恢复稀疏数组
		System.out.println("\n使用节点流（FileInputStream和FileInputStream）-------");
		FileInputStream fis = new FileInputStream(filePath);
		byte[] buff = new byte[1024];
		int len;
		StringBuilder result = new StringBuilder();
		while ((len = fis.read(buff)) != -1) {
			result.append(new String(buff, 0, len));
		}

		String[] split = result.toString().split("\\s");
		sum = Integer.valueOf(split[2]); // 获取非0个数
		int[][] sparseArray2 = new int[sum + 1][3]; // 创建稀疏数组

		int row; // 行
		int col; // 列
		for (int i = 0; i < split.length; i++) {
			row = i / 3;
			col = i % 3;
			sparseArray2[row][col] = Integer.valueOf(split[i]);
		}

		// 输出数组
		for (int i = 0; i < sparseArray2.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArray2[i][0], sparseArray2[i][1], sparseArray2[i][2]);
		}
		fis.close();
		fos.close();

		// 或者使用对象流
		System.out.println("\n使用对象流（ObjectOutputStream和ObjectInputStream）-------");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
		oos.writeObject(sparseArray);

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
		int[][] sparseArray3 = (int[][]) ois.readObject();

		for (int i = 0; i < sparseArray3.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArray3[i][0], sparseArray3[i][1], sparseArray3[i][2]);
		}
		oos.close();
	}

}
