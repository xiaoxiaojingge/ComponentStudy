package com.itjing.design.chain.handler;

import com.itjing.design.chain.common.response.Result;
import com.itjing.design.chain.config.ProductCheckHandlerConfig;
import com.itjing.design.chain.vo.ProductVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description: 抽象类处理器
 * @Author: lijing
 * @CreateTime: 2023-01-05 09:19
 */
@Component
public abstract class AbstractCheckHandler {

    /**
     * 当前处理器持有下一个处理器的引用
     */
    @Getter
    @Setter
    protected AbstractCheckHandler nextHandler;


    /**
     * 处理器配置
     */
    @Setter
    @Getter
    protected ProductCheckHandlerConfig config;

    /**
     * 处理器执行方法
     *
     * @param param
     * @return
     */
    public abstract Result handle(ProductVO param);

    /**
     * 链路传递
     *
     * @param param
     * @return
     */
    protected Result next(ProductVO param) {
        // 下一个链路没有处理器了，直接返回
        if (Objects.isNull(nextHandler)) {
            return Result.success();
        }

        // 执行下一个处理器
        return nextHandler.handle(param);
    }

}
