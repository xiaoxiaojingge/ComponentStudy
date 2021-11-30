package com.itjing.utils;

import com.itjing.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        parseJD("spring").forEach(System.out::println);
    }

    public static List<Content> parseJD(String keyword) throws IOException {
        // 获取请求 https://search.jd.com/Search?keyword=java
        // 前提是需要联网， ajax 不能获取到！
        String url = "https://search.jd.com/Search?keyword=" + keyword + "&enc=utf-8";
        // 解析网页 Jsoup 返回的 Document 就是浏览器 Document 对象
        Document document = Jsoup.parse(new URL(url), 30000);
        // 所有你在js中可以使用的方法，这里都能用！
        Element element = document.getElementById("J_goodsList");
//        System.out.println(element.html());
        // 获取所有li元素
        Elements elements = element.getElementsByTag("li");

        List<Content> goodList = new ArrayList<>();

        //获取元素中的内容，这里el就是每一个li标签
        for (Element el : elements) {
            if (el.attr("class").equalsIgnoreCase("gl-item")) {
                // 关于这种图片特别多的网站，所有图片都是延迟加载的！
                String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String title = el.getElementsByClass("p-name").eq(0).text();

//                System.out.println("-----------------------------");
                /*System.out.println(img);
                System.out.println(price);
                System.out.println(title);*/
                Content content = new Content();
                content.setImg(img);
                content.setPrice(price);
                content.setTitle(title);
                goodList.add(content);
            }
        }
        return goodList;
    }
}
