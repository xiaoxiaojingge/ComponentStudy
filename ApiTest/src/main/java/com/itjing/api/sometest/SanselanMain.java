package com.itjing.api.sometest;

import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SanselanMain {

	/**
	 * 用于解决图片为打印格式问题 ymak
	 * @throws IOException
	 * @throws ImageReadException
	 */
	@Test
	public void testimage() throws IOException, ImageReadException {
		File imageFile = new File("C:\\Users\\091795960\\Pictures/1 - 副本.jpg");
		// 判断文件是否存在
		System.out.println(Sanselan.hasImageFileExtension(imageFile));
		System.out.println("=======================================");
		// 获得图片结构描述
		System.out.println(Sanselan.dumpImageFile(imageFile));
		System.out.println("=======================================");
		// 获得图片信息
		ImageInfo imageInfo = Sanselan.getImageInfo(imageFile);
		System.out.println(imageInfo.getColorTypeDescription());
		System.out.println(imageInfo.getFormatName());
		System.out.println(imageInfo.getMimeType());
		System.out.println("=======================================");
		// 获得图片尺寸
		System.out.println(Sanselan.getImageSize(imageFile));
		System.out.println("=======================================");
		System.out.println(Sanselan.guessFormat(imageFile));
	}

}
