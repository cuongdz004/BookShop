package com.example.demo.Mapper;

import com.example.demo.Dto.Response.OrderItemResponse;
import com.example.demo.Entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
