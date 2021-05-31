package com.itjing.api.mapstruct.dto;

import com.itjing.api.mapstruct.param.OrderQueryParam;
import com.itjing.api.mapstruct.po.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderQueryParam entity2queryParam(Order order);
}