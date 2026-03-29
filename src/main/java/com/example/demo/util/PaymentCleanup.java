package com.example.demo.util;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Component
public class PaymentCleanup {

    private final OrderRepository orderRepository;


    private final OrderItemRepository orderItemRepository;


    private final BookRepository bookRepository;

    @Scheduled(fixedRate = 600000) // 10 phút
    @Transactional
    public void expirePendingPayments(){

        LocalDateTime time =
                LocalDateTime.now().minusMinutes(5);

        List<Order> expired =
                orderRepository.findExpiredOrders(time);

        for(Order order : expired){
            if(!order.getStatus().equals("PENDING")){
                continue;
            }
            // 1. Lấy danh sách item của order
            List<OrderItem> items =
                    orderItemRepository.findByOrder(order);

            // 2. Hoàn lại số lượng sách
            for(OrderItem item : items){

                Book book = bookRepository.findById(item.getBookId())
                        .orElseThrow();

                book.setQuantity(
                        book.getQuantity() + item.getQuantity().intValue()
                );

                bookRepository.save(book);
            }

            // 3. Update trạng thái
            order.setStatus("EXPIRED");
            order.setShippingStatus("CANCELLED");
        }

        orderRepository.saveAll(expired);

        System.out.println("Expired payments: " + expired.size());
    }
}