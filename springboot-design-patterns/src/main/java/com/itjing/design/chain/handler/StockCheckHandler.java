package com.itjing.design.chain.handler;

import com.itjing.design.chain.common.enums.ErrorCode;
import com.itjing.design.chain.common.response.Result;
import com.itjing.design.chain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 库存校验处理器
 * @Author: lijing
 * @CreateTime: 2023-01-05 09:41
 */
@Component
@Slf4j
public class StockCheckHandler extends AbstractCheckHandler{
    @Override
    public Result handle(ProductVO param) {

        log.info("库存校验 Handler 开始...");

        // 非法库存校验
        boolean illegalStock = param.getStock() < 0;
        if (illegalStock) {
            return Result.failure(ErrorCode.PARAM_STOCK_ILLEGAL_ERROR);
        }

        // 其他校验逻辑..

        log.info("库存校验 Handler 通过...");

        // 执行下一个处理器
        return super.next(param);
    }
}