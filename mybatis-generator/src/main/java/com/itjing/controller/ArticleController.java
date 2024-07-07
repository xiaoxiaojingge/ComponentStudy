package com.itjing.controller;

import com.itjing.entity.Article;
import com.itjing.mapstruct.ArticleConverter;
import com.itjing.response.RestResult;
import com.itjing.response.RestResultUtils;
import com.itjing.service.ArticleService;
import com.itjing.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @date 2021年11月13日 9:27
 * @description
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Resource
	private ThreadPoolTaskExecutor taskExector;

	/**
	 * 异步提交
	 */
	@GetMapping("/testTaskExecutor")
	public void testTaskExecutor() {
		taskExector.submit(new ArticleRunnable());
	}

	@GetMapping("/testCompletableFuture")
	public void testCompletableFuture() throws ExecutionException, InterruptedException {

		// 异步编排
		CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("future1...");
		}, taskExector.getThreadPoolExecutor());

		TimeUnit.SECONDS.sleep(1);
		System.out.println("main process....");

		CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("future2...");
		}, taskExector.getThreadPoolExecutor());

		CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("future3...");
		}, taskExector.getThreadPoolExecutor());

		CompletableFuture<String> future4 = CompletableFuture
			.supplyAsync(() -> "返回值", taskExector.getThreadPoolExecutor())
			.whenComplete((str, throwable) -> {
				System.out.println("结果：" + str);
			});
		String s = future4.get();
		System.out.println(s);
		// 等待执行完成
		CompletableFuture.allOf(future1, future2, future3, future4).get();
	}

	public class ArticleRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println("running.....");
		}

	}

	/**
	 * 测试 MapStruct
	 */
	@GetMapping("/testMapStruct")
	public RestResult<?> testMapStruct() {
		Article article = articleService.selectByPrimaryKey(11);
		ArticleVO articleVO = ArticleConverter.INSTANCE.domain2VO(article);
		return RestResultUtils.success(articleVO);
	}

}
