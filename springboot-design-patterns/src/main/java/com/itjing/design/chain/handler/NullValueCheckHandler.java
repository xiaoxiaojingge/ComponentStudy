package com.itjing.design.chain.handler;

import com.itjing.design.chain.common.enums.ErrorCode;
import com.itjing.design.chain.common.response.Result;
import com.itjing.design.chain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description: 空值校验处理器
 * @Author: lijing
 * @CreateTime: 2023-01-05 09:33
 */
@Component
@Slf4j
public class NullValueCheckHandler extends AbstractCheckHandler {

    @Override
    public Result handle(ProductVO param) {
        log.info("空值校验 Handler 开始...");

        // 降级：如果配置了降级，则跳过此处理器，执行下一个处理器
        if (super.getConfig().getDown()) {
            log.info("空值校验 Handler 已降级，跳过空值校验 Handler...");
            return super.next(param);
        }

        // 参数必填校验
        if (Objects.isNull(param)) {
            return Result.failure(ErrorCode.PARAM_NULL_ERROR);
        }
        // SkuId商品主键参数必填校验
        if (Objects.isNull(param.getSkuId())) {
            return Result.failure(ErrorCode.PARAM_SKU_NULL_ERROR);
        }
        // Price价格参数必填校验
        if (Objects.isNull(param.getPrice())) {
            return Result.failure(ErrorCode.PARAM_PRICE_NULL_ERROR);
        }
        // Stock库存参数必填校验
        if (Objects.isNull(param.getStock())) {
            return Result.failure(ErrorCode.PARAM_STOCK_NULL_ERROR);
        }

        log.info("空值校验 Handler 通过...");

        // 执行下一个处理器
        return super.next(param);
    }
}