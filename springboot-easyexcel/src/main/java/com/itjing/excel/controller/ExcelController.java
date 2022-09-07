package com.itjing.excel.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.itjing.excel.entity.User;
import com.itjing.excel.listener.UserListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description: Excel相关控制器
 * @Author: lijing
 * @CreateTime: 2022-09-07 15:06
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @PostMapping("/import")
    public String importByExcel(MultipartFile file) throws IOException {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcelFactory.read(file.getInputStream(), User.class, new UserListener())
                .sheet()
                // 这里可以设置行头，如果行头不止一行，可以设置最后一行行头所在位置。
                // 不写 .headRowNumber(位置) 也可以，因为默认会根据 DemoData 来解析，他没有指定头，也就是默认行头是第1行
//                .headRowNumber(1)
                .doRead();
        return "success";
    }
}
