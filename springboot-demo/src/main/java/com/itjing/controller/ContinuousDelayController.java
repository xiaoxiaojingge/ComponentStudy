package com.itjing.controller;

import com.itjing.service.ContinuousDelayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: ComponentStudy
 * @author: zhanling.li
 * @create: 2022-06-02 11:09
 */
@RestController
public class ContinuousDelayController {

    @Resource
    private ContinuousDelayService continuousDelayService;

    @PostMapping("/test/continuousDelay")
    public void continuousDelay(){
        continuousDelayService.update();
    }
}
