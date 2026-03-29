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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class OrderItemImpl implements OrderService {

    private final OrderItemMapper orderItemMapper;
    private final BookMapper bookMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    @Override
    public List<BookResponse> getTopSellingBooks() {

        Pageable top4 = PageRequest.of(0, 5);

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

//        System.out.println("Email: " + email);

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
    @Transactional
    public void confirmDelivered(Long orderId) {
        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Not found order"));
        if(!Objects.equals(order.getShippingStatus(), "SHIPPING")) {
            throw new RuntimeException("");
        }
        order.setShippingStatus("DELIVERED");
    }
    @Transactional
    public void cancelOrder(Long orderId) {


        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));


        if(order.getStatus().equals("CANCELLED")){
            throw new RuntimeException("Đơn đã bị huỷ trước đó");
        }

        if(order.getStatus().equals("EXPIRED")){
            throw new RuntimeException("Đơn đã hết hạn");
        }

        if(order.getStatus().equals("DELIVERED")){
            throw new RuntimeException("Đơn đã giao, không thể huỷ");
        }


        // 3. Lấy danh sách item
        List<OrderItem> items = orderItemRepository.findByOrder(order);

        // 4. Hoàn lại kho
        for(OrderItem item : items){

            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            book.setQuantity(
                    book.getQuantity() + item.getQuantity().intValue()
            );

            bookRepository.save(book);
        }

        // 5. Update trạng thái
        order.setStatus("CANCELLED");
        order.setShippingStatus("CANCELLED");
        orderRepository.save(order);
    }
}
