package com.itjing.design.chain.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 商品对象VO
 * @Author: lijing
 * @CreateTime: 2023-01-05 09:18
 */
@Data
@Builder
public class ProductVO {

    /**
     * 商品SKU，唯一
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品图片路径
     */
    private String path;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

}
