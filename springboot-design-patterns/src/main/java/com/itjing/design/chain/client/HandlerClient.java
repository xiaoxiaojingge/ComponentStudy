package com.itjing.design.chain.client;

import com.itjing.design.chain.common.response.Result;
import com.itjing.design.chain.handler.AbstractCheckHandler;
import com.itjing.design.chain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 客户端：执行处理器链路
 * @Author: lijing
 * @CreateTime: 2023-01-05 09:44
 */
@Slf4j
public class HandlerClient {

    public static Result executeChain(AbstractCheckHandler handler, ProductVO param) {

        // 执行处理器
        Result handlerResult = handler.handle(param);
        if (!handlerResult.isSuccess()) {
            log.info("HandlerClient 责任链执行失败返回：" + handlerResult);
            return handlerResult;
        }
        return Result.success();
    }

}
