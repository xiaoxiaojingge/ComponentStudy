package com.itjing.design.chain.controller;

import com.itjing.design.chain.common.response.Result;
import com.itjing.design.chain.service.ProductService;
import com.itjing.design.chain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Description: 责任链测试控制器
 * @Author: lijing
 * @CreateTime: 2023-01-05 10:09
 */
@RestController
@Slf4j
@RequestMapping("/chain")
public class ChainController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public Result createProduct() {
        // 创建商品参数
        ProductVO param = ProductVO.builder()
                .skuId(123L)
                .skuName("华为手机")
                .path("https://...")
                .price(new BigDecimal(1))
                .stock(1)
                .build();

        return productService.createProduct(param);
    }

}
