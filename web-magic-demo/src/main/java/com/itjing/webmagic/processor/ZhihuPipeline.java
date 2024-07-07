package com.itjing.webmagic.processor;

import com.itjing.webmagic.entity.CmsContentPO;
import com.itjing.webmagic.mapper.CrawlerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.UUID;

/**
 * @author lijing
 * @date 2022年05月26日 15:49
 * @description 知乎数据处理类
 */
@Component
public class ZhihuPipeline implements Pipeline {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZhihuPipeline.class);

	@Autowired
	private CrawlerMapper crawlerMapper;

	public void process(ResultItems resultItems, Task task) {
		String title = resultItems.get("title");
		String answer = resultItems.get("answer");

		CmsContentPO contentPO = new CmsContentPO();
		contentPO.setContentId(UUID.randomUUID().toString());
		contentPO.setTitle(title);
		contentPO.setReleaseDate(new Date());
		contentPO.setContent(answer);

		try {
			boolean success = crawlerMapper.addCmsContent(contentPO) > 0;
			LOGGER.info("保存知乎文章成功：{}", title);
		}
		catch (Exception ex) {
			LOGGER.error("保存知乎文章失败", ex);
		}
	}

}