package com.itjing.controller;

import com.itjing.response.JsonResult;
import com.itjing.utils.RedisUtil;
import com.wf.captcha.SpecCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @date 2022年05月11日 11:22
 * @description SpringBoot项目创建图形验证码
 * 前后端分离项目中建议不要存储在session中；
 * 而使用分布式session，存储在redis中，redis存储需要一个key，key一同返回给前端用于验证输入。
 */
@RestController
@Slf4j
@RequestMapping("/captcha")
public class CaptchaController {

    @RequestMapping("/generate")
    public JsonResult captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        RedisUtil.StringOps.setEx(key, verCode, 30, TimeUnit.MINUTES);
        // 将key和base64返回给前端
        return JsonResult.success().put("key", key).put("image", specCaptcha.toBase64());
    }

    @ResponseBody
    @PostMapping("/vaild")
    public JsonResult login(String userName, String password, String verCode, String verKey) {
        // 获取redis中的验证码
        String redisCode = RedisUtil.StringOps.get(verKey);
        // 判断验证码
        if (verCode == null || !redisCode.equals(verCode.trim().toLowerCase())) {
            return JsonResult.fail("验证码不正确");
        }
        // 判断用户名和密码
        // ...
        // 这里的逻辑需要自己去实现
        return JsonResult.success();
    }
}
