package com.itjing.design.chain.handler;

import com.itjing.design.chain.common.enums.ErrorCode;
import com.itjing.design.chain.common.response.Result;
import com.itjing.design.chain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description: 价格校验处理器
 * @Author: lijing
 * @CreateTime: 2023-01-05 09:38
 */
@Component
@Slf4j
public class PriceCheckHandler extends AbstractCheckHandler {
    @Override
    public Result handle(ProductVO param) {

        log.info("价格校验 Handler 开始...");

        // 非法价格校验
        boolean illegalPrice = param.getPrice().compareTo(BigDecimal.ZERO) <= 0;
        if (illegalPrice) {
            return Result.failure(ErrorCode.PARAM_PRICE_ILLEGAL_ERROR);
        }
        // 其他校验逻辑...

        log.info("价格校验 Handler 通过...");

        // 执行下一个处理器
        return super.next(param);
    }
}