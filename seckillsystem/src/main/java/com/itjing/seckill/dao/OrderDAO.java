package com.itjing.seckill.dao;

import com.itjing.seckill.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDAO {

	// 创建订单方法
	void createOrder(Order order);

}
