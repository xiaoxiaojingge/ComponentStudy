package com.itjing.design.chain.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: 处理器配置类
 * @Author: lijing
 * @CreateTime: 2023-01-05 09:29
 */
@AllArgsConstructor
@Data
public class ProductCheckHandlerConfig {

    /**
     * 处理器Bean名称
     */
    private String handler;

    /**
     * 下一个处理器
     */
    private ProductCheckHandlerConfig next;

    /**
     * 是否降级
     */
    private Boolean down = Boolean.FALSE;

}
