package com.example.demo.Service.impl;

import com.example.demo.Dto.Request.CheckOutRequest;
import com.example.demo.Dto.Request.OrderRequest;
import com.example.demo.Dto.Response.BookResponse;
import com.example.demo.Dto.Response.OrderResponse;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Mapper.OrderItemMapper;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderItemImpl implements OrderService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<BookResponse> getTopSellingBooks() {

        Pageable top4 = PageRequest.of(0, 4);

        List<Long> topBookIds = orderItemRepository.findTopSellingBookIds(top4);

        List<Book> books = bookRepository.findByIdIn(topBookIds);

        Map<Long, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));

        List<Book> sortedBooks = topBookIds.stream()
                .map(bookMap::get)
                .filter(Objects::nonNull)
                .toList();

        return bookMapper.EntityMapperToResponse(sortedBooks);
    }
    @Transactional
    public void checkOut(CheckOutRequest checkOutRequest) {

        // create order
        Order order = new Order();
        order.setEmail(checkOutRequest.getEmail());
        order.setTotalAmount(checkOutRequest.getTotal());

        Order saveOrder = orderRepository.save(order);

        for (OrderRequest item : checkOutRequest.getItems()) {

            // lấy book từ DB
            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            // kiểm tra đủ hàng không
            if(book.getQuantity() < item.getQuantity()){
                throw new RuntimeException("Not enough stock");
            }

            // trừ số lượng
            book.setQuantity(book.getQuantity() - item.getQuantity().intValue());

            bookRepository.save(book);

            // tạo order item
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(item.getBookId());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(saveOrder);

            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public int getSumOrder() {
        return 0;
    }

    @Override
    public List<OrderResponse> getOrderByEmail(String email) {

        System.out.println("Email: " + email);

        List<Order> orders = orderRepository.findByEmail(email);

        return orders.stream().map(order -> {

            OrderResponse response = new OrderResponse();

            response.setId(order.getId());
            response.setTotalAmount(order.getTotalAmount());
            response.setStatus(order.getStatus());
            response.setCreatedAt(order.getCreatedAt());
            response.setEmail(order.getEmail());

            return response;

        }).toList();
    }

}
