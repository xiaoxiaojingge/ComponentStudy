package com.itjing.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.itjing.entity.Article;
import com.itjing.service.ArticleService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijing
 * @date 2021年12月17日 15:10
 * @description 数据监听器，用于 alibaba easyexcel
 */
// 有个很重要的点Listener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class ArticleListener extends AnalysisEventListener<Article> {

	/**
	 * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
	 */
	private static final int BATCH_COUNT = 100;

	List<Article> cachedDataList = new ArrayList<>();

	/**
	 * 根据业务需求，可以是一个DAO，也可以是一个service。当然如果不用存储这个对象没用。
	 */
	private ArticleService articleService;

	/*
	 * public ArticleListener() { // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数 }
	 */

	/**
	 * 如果要存取数据库，service从监听器里面拿取list就需要有参构造，否则直接使用会报空指针异常
	 */
	public ArticleListener(ArticleService articleService) {
		this.articleService = articleService;
	}

	/**
	 * 这里写自己的业务，没有可以不要
	 */
	/*
	 * private void calculateMealTimes(Article data){ //这里写自己的业务，没有可以不要 doSomething(); }
	 */

	/**
	 * 这个每一条数据解析都会来调用
	 * @param data
	 * @param context
	 */
	@Override
	public void invoke(Article data, AnalysisContext context) {
		// calculateMealTimes(data);
		log.info("解析到一条数据:{}", JSON.toJSONString(data));
		cachedDataList.add(data);
		// 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
		if (cachedDataList.size() >= BATCH_COUNT) {
			saveData();
			// 存储完成清理 list
			// cachedDataList.clear();
			// 存储完成清理 list
			cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
		}
	}

	/**
	 * 所有数据解析完成了 都会来调用
	 * @param context
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		// 这里也要保存数据，确保最后遗留的数据也存储到数据库
		saveData();
		log.info("所有数据解析完成！");
	}

	/**
	 * 存储数据库
	 */
	private void saveData() {
		log.info("{}条数据，开始存储数据库！", cachedDataList.size());
		articleService.batchInsert(cachedDataList);
		log.info("存储数据库成功！");
	}

}
