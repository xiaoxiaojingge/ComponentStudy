package com.itjing.utilstudy;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class UtilstudyApplicationTests {

	@Autowired
	private Tika tika;

	@Test
	void apacheTikaTest() throws TikaException, IOException {
		Path path = Paths
			.get("D:\\workspace_weixin_1050Ti\\学习重要文件\\B站学习\\B站动力节点\\02-数据库阶段\\MySQL数据库视频教程老杜最新MySQL教程\\MySQL.docx");
		File file = path.toFile();
		String result = tika.parseToString(file);
		System.out.println(result);
	}

}
