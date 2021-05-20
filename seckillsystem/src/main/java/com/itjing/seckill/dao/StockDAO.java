package com.itjing.seckill.dao;

import com.itjing.seckill.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StockDAO {

    //根据商品id查询库存信息的方法
    Stock checkStock(Integer id);

    //根据商品id扣除库存
    int updateSale(Stock stock);
}
