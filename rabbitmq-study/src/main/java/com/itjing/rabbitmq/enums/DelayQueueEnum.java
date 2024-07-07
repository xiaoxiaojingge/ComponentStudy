package com.itjing.rabbitmq.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijing
 * @date 2022年06月20日 10:20
 * @description 延迟队列枚举
 */
@Getter
public enum DelayQueueEnum {

	DEFAULT_DELAY_QUEUE("default_delay_queue", true, 5000L, ExchangeEnum.DEFAULT_DEAD_EXCHANGE,
			QueueEnum.DEFAULT_DEAD_QUEUE, "默认延迟队列"),
	SERVICE_NOTIFY_DELAY_QUEUE_15000("service_notify_delay_queue_15000", true, 15000L,
			ExchangeEnum.DEFAULT_DEAD_EXCHANGE, QueueEnum.SERVICE_NOTIFY_DEAD_QUEUE, "业务回调延迟队列"),
	SERVICE_NOTIFY_DELAY_QUEUE_30000("service_notify_delay_queue_30000", true, 30000L,
			ExchangeEnum.DEFAULT_DEAD_EXCHANGE, QueueEnum.SERVICE_NOTIFY_DEAD_QUEUE, "业务回调延迟队列");

	// 队列名称
	private String name;

	// 持久化
	private Boolean durable;

	// 延迟时间ms
	private Long ttl;

	// 监听交换机
	private ExchangeEnum deadExchangeEnum;

	// 监听队列
	private QueueEnum deadQueueEnum;

	// 队列描述
	private String desc;

	DelayQueueEnum(String name, Boolean durable, Long ttl, ExchangeEnum deadExchangeEnum, QueueEnum deadQueueEnum,
			String desc) {
		this.name = name;
		this.durable = durable;
		this.ttl = ttl;
		this.deadExchangeEnum = deadExchangeEnum;
		this.deadQueueEnum = deadQueueEnum;
		this.desc = desc;
	}

	/**
	 * 延迟队列 绑定死信 消息配置
	 * @param deadExchange 延迟交换机
	 * @param deadQueue 延迟队列
	 * @param ttl 延迟时间
	 * @return
	 */
	public static Map<String, Object> params(String deadExchange, String deadQueue, Long ttl) {
		// reply_to 队列
		Map<String, Object> map = new HashMap<>();
		// 设置消息的过期时间，单位毫秒
		map.put("x-message-ttl", ttl);
		// 设置附带的死信交换机
		map.put("x-dead-letter-exchange", deadExchange);
		// 指定重定向的路由键，消息作废之后可以决定需不需要更改他的路由键，如果需要，就在这里指定
		map.put("x-dead-letter-routing-key", deadQueue);
		return map;
	}

}
