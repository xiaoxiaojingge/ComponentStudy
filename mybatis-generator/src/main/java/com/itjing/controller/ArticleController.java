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
    @GetMapping("/taskExecutor")
    public void taskExecutor(){
        taskExector.submit(new ArticleRunnable());
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
    public RestResult<?> testMapStruct(){
        Article article = articleService.selectByPrimaryKey(11);
        ArticleVO articleVO = ArticleConverter.INSTANCE.domain2VO(article);
        return RestResultUtils.success(articleVO);
    }
}
