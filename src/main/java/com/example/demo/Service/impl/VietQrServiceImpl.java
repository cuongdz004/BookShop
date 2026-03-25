package com.example.demo.Service.impl;

import com.example.demo.Dto.Request.OrderRequest;
import com.example.demo.Dto.Request.SepayWebhookRequest;
import com.example.demo.Dto.Request.VietQrRequest;
import com.example.demo.Dto.Response.VietQrResponse;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.VietQrService;
import com.example.demo.util.VietQrGenerator;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VietQrServiceImpl implements VietQrService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private VietQrGenerator vietQrGenerator;

    @Override
    @Transactional
    public VietQrResponse createPayment(VietQrRequest request) {

//        String orderCode = UUID.randomUUID().toString();
        String orderCode = UUID.randomUUID().toString().replace("-", "");
        Order order = new Order();
        order.setOrderCode(orderCode);
        order.setEmail(request.getEmail());
        order.setTotalAmount(request.getTotal());
        order.setStatus("PENDING");

        Order savedOrder = orderRepository.save(order);

        String qrUrl = vietQrGenerator.generateQR(orderCode, request.getTotal());

        VietQrResponse res = new VietQrResponse();
        res.setOrderCode(orderCode);
        res.setQrUrl(qrUrl);
        res.setAmount(request.getTotal());

        for (OrderRequest item : request.getItems()) {

            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            if (book.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(item.getBookId());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(savedOrder);

            orderItemRepository.save(orderItem);
        }

        return res;
    }

    @Override
    @Transactional
    public void handleSepayWebhook(SepayWebhookRequest request) {

//        String orderCode = request.getContent();

        String orderCode = request.getContent().split("-")[0];
        Optional<Order> optional = orderRepository.findByOrderCode(orderCode);
        System.out.println("Order Code: "+ orderCode);
        System.out.println("Order found: " + optional.isPresent());

        if(optional.isPresent()){
            System.out.println("Order: " + optional.get());
        }
        if (optional.isEmpty()) {
            return;
        }
        Order order = optional.get();
        System.out.println("Order status DB: " + order.getStatus());
        System.out.println("Webhook amount: " + request.getAmount());
        System.out.println("Order amount DB: " + order.getTotalAmount());
        if (!order.getStatus().equals("PENDING")) {
            return;
        }
        if (order.getTotalAmount().compareTo(request.getAmount()) != 0) {
            return;
        }

        order.setStatus("PAID");

        orderRepository.save(order);

        List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
        for (OrderItem item : orderItems) {

            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            if (book.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock");
            }

            book.setQuantity(book.getQuantity() - item.getQuantity());

            bookRepository.save(book);
        }
    }
}