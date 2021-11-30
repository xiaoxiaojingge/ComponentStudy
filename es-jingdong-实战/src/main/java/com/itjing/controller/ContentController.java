package com.itjing.controller;

import com.itjing.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// 请求编写
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keywords) throws IOException {
        return contentService.parseContent(keywords);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,
                                            @PathVariable("pageNo") Integer pageNo,
                                            @PathVariable("pageSize") Integer pageSize) throws IOException {
//        return contentService.searchPage(keyword, pageNo, pageSize);
        return contentService.searchPageAndHighlight(keyword, pageNo, pageSize);
    }
}
