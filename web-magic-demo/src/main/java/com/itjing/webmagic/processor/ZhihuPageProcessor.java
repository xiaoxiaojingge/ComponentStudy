package com.itjing.webmagic.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author lijing
 * @date 2022年05月26日 15:48
 * @description 知乎页面内容处理类
 */
@Component
public class ZhihuPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.zhihu\\.com/question/\\d+").all());
        page.putField("title", page.getHtml().xpath("//h1[@class='QuestionHeader-title']/text()").toString());
        // 此处有问题，需要修改
        page.putField("answer", page.getHtml().xpath("//div[@class='QuestionAnswer-content']/tidyText()").toString());
        if (page.getResultItems().get("title") == null) {
            // 如果是列表页，跳过此页，pipeline不进行后续处理
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}