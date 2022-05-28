package com.itjing.controller;

import com.alibaba.fastjson.JSON;
import com.itjing.annotation.InterfaceType;
import com.itjing.entity.Article;
import com.itjing.response.RestResult;
import com.itjing.response.RestResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijing
 * @date 2022年05月26日 17:09
 * @description
 */
@RestController
@RequestMapping("/interfaceType")
@Slf4j
public class InterfaceTypeController {

    @PostMapping("/test")
    public RestResult<?> testInterfaceType(@InterfaceType Article article) {
        log.info(JSON.toJSONString(article));
        return RestResultUtils.success(article);
    }
}
