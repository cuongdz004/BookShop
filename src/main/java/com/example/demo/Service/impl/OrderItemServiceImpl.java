package com.example.demo.Service.impl;

import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.OrderItemResponse;
import com.example.demo.Dto.Response.OrderResponse;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponse getInfoBookByOrderItem(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setPhone(order.getPhone());
        response.setEmail(order.getEmail());
        response.setAddress(order.getAddress());
        response.setShippingStatus(order.getShippingStatus());

        List<OrderItem> orderItemResponses = orderItemRepository.findOrderItemByOrderId(orderId);
        List<BookResponse> bookResponses = new ArrayList<>();
        for (OrderItem item : orderItemResponses) {
            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book bot fond"));
            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(book.getId());
            bookResponse.setName(book.getName());
            bookResponse.setImageUrl(book.getImageUrl());
            bookResponse.setDescription(book.getDescription());
            bookResponse.setQuantity(item.getQuantity());
            bookResponse.setPrice(item.getPrice());

            bookResponses.add(bookResponse);
        }
        response.setBooks(bookResponses);
        return response;
    }
}
