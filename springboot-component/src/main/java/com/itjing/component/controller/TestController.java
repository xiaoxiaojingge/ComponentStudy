package com.itjing.component.controller;

import com.itjing.component.annotation.NoRepeatSubmit;
import com.itjing.component.common.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试控制器
 * @Author: lijing
 * @CreateTime: 2023-01-03 15:47
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping("/repeatSubmit")
    @NoRepeatSubmit(interval = 1000)
    public ApiResult repeatSubmit() {
        return ApiResult.ok();
    }
}
