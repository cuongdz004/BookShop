package com.example.demo.Mapper;

import com.example.demo.Dto.Response.OrderResponse;
import com.example.demo.Entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
}
