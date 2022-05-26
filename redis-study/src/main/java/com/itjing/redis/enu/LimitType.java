package com.itjing.redis.enu;

/**
 * @author lijing
 * @date 2022年05月26日 9:30
 * @description 限流使用枚举
 */
public enum LimitType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP

}
